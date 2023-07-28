package com.learn.zookeeper.curator;

/**
 * @author ChenYP
 * @date 2023/7/26 11:08
 * @Describe
 */
public class CuratorClusterBase extends CuratorStandaloneBase{


    private final static String CLUSTER_CONNECT_STR = "192.168.17.128:2181,192.168.17.128:2182,192.168.17.128:2183,192.168.17.128:2184";


    @Override
    public String getConnectStr() {
        return CLUSTER_CONNECT_STR;
    }
}
