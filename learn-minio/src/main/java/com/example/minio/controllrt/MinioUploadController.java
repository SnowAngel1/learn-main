package com.example.minio.controllrt;

import com.alibaba.fastjson.JSON;
import com.common.utils.R;
import io.minio.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;

/**
 * @author：陈迎鹏
 * @Description TODO
 * @since 1.0.0
 */
@Slf4j
@RestController
@RequestMapping(value = "/minio")
public class MinioUploadController {


    @Autowired
    private MinioClient minioClient;

    @Value("${minio.bucketName}")
    private String bucketName;


    @GetMapping(value = "/list")
    public List<Object> list() throws Exception {

        Iterable<Result<Item>> myObjects = minioClient.listObjects(ListObjectsArgs.builder().bucket(bucketName).build());
        Iterator<Result<Item>> iterator = myObjects.iterator();
        List<Object> items = new ArrayList<>();
        String format = "{'fileName':'%s',fileSize:'%s'}";
        while (iterator.hasNext()){
            Item item = iterator.next().get();
            items.add(JSON.parse(String.format(format,item.objectName(),formatFileSize(item.size()))));
        }
        return items;
    }

    @PostMapping(value = "/upload")
    public R upload(@RequestParam(name = "file",required = false)MultipartFile[] file){
        if (file == null || file.length == 0){
            return R.error("上传文件不能为空");
        }
        List<String> orgFileNameList = new ArrayList<>(file.length);

        for (MultipartFile multipartFile : file) {
            String originalFilename = multipartFile.getOriginalFilename();
            orgFileNameList.add(originalFilename);
            try {
                //上传文件
                InputStream inputStream = multipartFile.getInputStream();
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(originalFilename)
                        .stream(inputStream, multipartFile.getSize(), -1)
                        .contentType(multipartFile.getContentType())
                        .build());
                inputStream.close();
            } catch (Exception e) {
                log.error(e.getMessage());
                return R.error("上传失败");
            }
        }
        Map<String,Object> data = new HashMap<>();
        data.put("bucketName",bucketName);
        data.put("fileName",orgFileNameList);
        return R.ok("上传成功",data);
    }

    @RequestMapping(value = "/download/")
    public void download(HttpServletResponse response,@RequestParam("fileName")String fileName)  {

        InputStream in = null;

        try {
            StatObjectResponse stat = minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucketName)
                    .object(fileName)
                    .build());
            response.setContentType(stat.contentType());
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            in = minioClient.getObject(GetObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                    .build());
            IOUtils.copy(in,response.getOutputStream());
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    log.error(e.getMessage());
                }
            }
        }
    }




    @DeleteMapping("/delete")
    public R deleteFile(@RequestParam("fileName")String fileName){
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                            .bucket(bucketName)
                            .object(fileName)
                            .build());
        } catch (Exception e) {
            log.error(e.getMessage());
            return R.error("删除失败");
        }
        return R.ok("删除成功",null);

    }

    private static String formatFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + " B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + " KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + " MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + " GB";
        }
        return fileSizeString;
    }

}
