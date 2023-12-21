package com.smart.smartapp.test.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

/**
 * @description: 命名服务
 * @author: zhizj
 * @date: 2023/12/13
 */
public class ZooKeeperNamingService {
    private static final String ZOOKEEPER_ADDRESS = "192.168.163.11:2182";
    private static final int SESSION_TIMEOUT = 100000;
    private static final String NAMING_ROOT = "/services";

    public static void main(String[] args) throws Exception {
        // 创建 ZooKeeper 客户端
        ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, event -> {
            if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                System.out.println("Connected to ZooKeeper");
            }
        });

        // 等待连接建立
        while (zooKeeper.getState() != ZooKeeper.States.CONNECTED) {
            Thread.sleep(100);
        }

        // 创建命名服务的根节点
        createNodeIfNotExists(zooKeeper, NAMING_ROOT, "");

        // 注册服务
        registerService(zooKeeper, "service1", "127.0.0.1:8080");
        registerService(zooKeeper, "service2", "127.0.0.1:8081");

        // 查找服务
        String service1Address = discoverService(zooKeeper, "service1");
        String service2Address = discoverService(zooKeeper, "service2");

        System.out.println("Service 1 address: " + service1Address);
        System.out.println("Service 2 address: " + service2Address);

        // 关闭 ZooKeeper 连接
        zooKeeper.close();
    }

    private static void createNodeIfNotExists(ZooKeeper zooKeeper, String path, String data) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, false);
        if (stat == null) {
            zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    private static void registerService(ZooKeeper zooKeeper, String serviceName, String serviceAddress) throws KeeperException, InterruptedException {
        String serviceNode = NAMING_ROOT + "/" + serviceName;
        createNodeIfNotExists(zooKeeper, serviceNode, serviceAddress);
        System.out.println("Service registered: " + serviceNode + " -> " + serviceAddress);
    }

    private static String discoverService(ZooKeeper zooKeeper, String serviceName) throws KeeperException, InterruptedException {
        String serviceNode = NAMING_ROOT + "/" + serviceName;
        byte[] data = zooKeeper.getData(serviceNode, false, null);
        return new String(data);
    }
}
