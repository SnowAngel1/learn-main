package com.test;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author 江南
 * @date 2023/6/17
 */
public class ShareSmbFileUtil {
    private static String USER_DOMAIN = "192.168.3.72";
    private static String USER_ACCOUNT = "t1";
    private static String USER_PWS = "123";

    /**
     * @Title smbGet
     * @Param shareUrl 共享目录中的文件路径，如smb://192.168.1.158/share/test.txt
     */
    public static void smbGet(String shareUrl) throws Exception {
        long startTime1 = System.currentTimeMillis();

        long startTime = System.currentTimeMillis();
        SmbFile smbFile = new SmbFile(shareUrl);
        System.out.println("远程共享目录访问耗时：【{}】" + (System.currentTimeMillis() - startTime));

        InputStream inputStream = null;
        try {
            //得到文件的大小
            int length = smbFile.getContentLength();
            byte[] buffer = new byte[length];
            //建立smb文件输入流
            long start = System.currentTimeMillis();
            inputStream = new SmbFileInputStream(smbFile);
            long end = System.currentTimeMillis();
            System.out.println("获取文件流时间为：" +( end - start) + "ms");

            long startStr = System.currentTimeMillis();
            while ((inputStream.read(buffer)) != -1)
            {
                System.out.write(buffer);
                System.out.println("\n"+buffer.length);
            }
            System.out.println("获取文字内容时间为：" + (System.currentTimeMillis() - startStr) + "ms");
            System.out.println("获取文件完成时间：" + (System.currentTimeMillis() - startTime1) + "ms");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (inputStream != null){
                inputStream.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String shareDir = "smb://t1:123@192.168.3.72/public/test.txt";
        //读取文件内容
        smbGet(shareDir);
        //获取文件夹下文件
//        listFiles("smb://t1:123@192.168.3.72/public/");
    }

    /**
     * @Title listFiles
     * @Description 遍历指定目录下的文件
     * @date 2019-01-11 09:56
     */
    private static void listFiles(String shareDirectory) throws Exception {
        long startTime = System.currentTimeMillis();
        // 域服务器验证
        NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication(USER_DOMAIN, USER_ACCOUNT,
                USER_PWS);
        SmbFile remoteFile = new SmbFile(shareDirectory, auth);
        System.out.println("远程共享目录访问耗时：【{}】" + (System.currentTimeMillis() - startTime));

        if (remoteFile.exists()) {
            SmbFile[] files = remoteFile.listFiles();
            for (SmbFile f : files) {
                System.out.println(f.getName());
            }
        } else {
            System.out.println("文件不存在");
        }
    }
}
