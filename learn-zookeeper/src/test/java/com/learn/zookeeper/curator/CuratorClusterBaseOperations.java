package com.learn.zookeeper.curator;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @author ChenYP
 * @date 2023/7/26 10:24
 * @Describe
 */
@Slf4j
public class CuratorClusterBaseOperations extends CuratorClusterBase{


  @Test
    public void testConnectCluster() throws Exception {
      CuratorFramework curatorFramework = getCuratorFramework();
      String path = "/test";
      byte[] bytes = curatorFramework.getData().forPath(path);
      System.out.println(new String(bytes));
      while (true){
          try {
              byte[] bytes1 = curatorFramework.getData().forPath(path);
              System.out.println(new String(bytes1));
              TimeUnit.SECONDS.sleep(5);
          } catch (Exception e) {
              e.printStackTrace();
          }
      }

  }
}
