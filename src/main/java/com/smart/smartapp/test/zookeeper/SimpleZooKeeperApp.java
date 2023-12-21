package com.smart.smartapp.test.zookeeper;

import org.apache.zookeeper.*;

/**
 * @description: 配置中心
 * @author: zhizj
 * @date: 2023/12/8
 */
public class SimpleZooKeeperApp {
    private static final String ZOOKEEPER_ADDRESS = "192.168.163.11:2182"; // 指定ZooKeeper服务器地址
    private static final int SESSION_TIMEOUT = 50000; // 指定会话超时时间

    public static void main(String[] args) throws Exception {
        // 创建ZooKeeper客户端
        ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_ADDRESS, SESSION_TIMEOUT, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                // 监听连接状态变化事件
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    System.out.println("Connected to ZooKeeper");
                }
            }
        });

        // 等待连接建立
        while (zooKeeper.getState() != ZooKeeper.States.CONNECTED) {
            Thread.sleep(100);
        }

        // 创建一个持久化节点
        String nodePath = "/exampleNode";
        String nodeData = "Hello, ZooKeeper!";
        zooKeeper.create(nodePath, nodeData.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        System.out.println("Node created: " + nodePath);

        // 获取节点数据
        byte[] data = zooKeeper.getData(nodePath, null, null);
        System.out.println("Node data: " + new String(data));

        // 监听节点数据变化
        zooKeeper.getData(nodePath, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeDataChanged) {
                    System.out.println("Node data changed");
                    try {
                        byte[] newData = zooKeeper.getData(nodePath, null, null);
                        System.out.println("New data: " + new String(newData));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, null);

        // 模拟节点数据变化
        zooKeeper.setData(nodePath, "New data".getBytes(), -1);

        // 关闭ZooKeeper连接
        zooKeeper.close();
    }
}

