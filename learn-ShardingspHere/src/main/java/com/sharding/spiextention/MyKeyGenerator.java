package com.sharding.spiextention;

import org.apache.shardingsphere.spi.keygen.ShardingKeyGenerator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author ChenYP
 * @date 2023/7/19 15:41
 * @Describe
 */
public class MyKeyGenerator  implements ShardingKeyGenerator {


    private Properties properties = new Properties();

    private AtomicLong atomicLong = new AtomicLong(0);


    @Override
    public Comparable<?> generateKey() {


        //读取一个自定义属性
        // String prefix = properties.getProperty("mykey-offset","100");
        LocalDateTime ldt = LocalDateTime.now();
        String timestamp = DateTimeFormatter.ofPattern("HHmmssSSSS").format(ldt);
        return Long.parseLong(""+timestamp+atomicLong.incrementAndGet());
    }

    @Override
    public String getType() {
        return "MYKEY";
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
