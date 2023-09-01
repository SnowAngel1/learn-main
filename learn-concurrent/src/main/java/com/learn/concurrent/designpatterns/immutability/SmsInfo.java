package com.learn.concurrent.designpatterns.immutability;

/**
 * @author ChenYP
 * 短信服务商信息
 */
public final class SmsInfo {
    /**
     * 短信服务商地址
     */
    private final String url;

    /**
     * 每次发送短信内容最大字节数
     */
    private final Integer maxSizeInBytes;

    private SmsInfo smsInfo;

    public SmsInfo(String url, Integer maxSizeInBytes) {
        this.url = url;
        this.maxSizeInBytes = maxSizeInBytes;
    }

    public SmsInfo updateSmsInfo(String url,Integer maxSizeInBytes){
        return  smsInfo = new SmsInfo(url,maxSizeInBytes);
    }


    public String getUrl() {
        return url;
    }


    public Integer getMaxSizeInBytes() {
        return maxSizeInBytes;
    }


    @Override
    public String toString() {
        return "SmsInfo{" +
                "url='" + url + '\'' +
                ", maxSizeInBytes=" + maxSizeInBytes +
                '}';
    }
}
