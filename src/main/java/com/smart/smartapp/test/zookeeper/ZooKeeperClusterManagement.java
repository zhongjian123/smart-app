package com.smart.smartapp.test.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * @description: 统一集群管理
 * @author: zhizj
 * @date: 2023/12/15
 */
public class ZooKeeperClusterManagement {
    private static final String ZOOKEEPER_ADDRESS = "192.168.163.11:2182";
    private static final int SESSION_TIMEOUT = 100000;
    private static final String CLUSTER_ROOT = "/cluster";

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

        // 创建集群根节点
        createNodeIfNotExists(zooKeeper, CLUSTER_ROOT, "");

        // 模拟节点加入和退出
        joinCluster(zooKeeper, "node1");
        joinCluster(zooKeeper, "node2");

        // 获取集群成员列表
        List<String> clusterMembers = getClusterMembers(zooKeeper);

        System.out.println("Cluster members: " + clusterMembers);

        // 模拟节点退出
        leaveCluster(zooKeeper, "node1");

        // 获取更新后的集群成员列表
        clusterMembers = getClusterMembers(zooKeeper);

        System.out.println("Updated cluster members: " + clusterMembers);

        // 关闭 ZooKeeper 连接
        zooKeeper.close();
    }

    private static void createNodeIfNotExists(ZooKeeper zooKeeper, String path, String data) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.exists(path, false);
        if (stat == null) {
            zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    private static void joinCluster(ZooKeeper zooKeeper, String nodeName) throws KeeperException, InterruptedException {
        String nodePath = CLUSTER_ROOT + "/" + nodeName;
        createNodeIfNotExists(zooKeeper, nodePath, "");
        System.out.println("Node joined cluster: " + nodeName);
    }

    private static void leaveCluster(ZooKeeper zooKeeper, String nodeName) throws KeeperException, InterruptedException {
        String nodePath = CLUSTER_ROOT + "/" + nodeName;
        zooKeeper.delete(nodePath, -1);
        System.out.println("Node left cluster: " + nodeName);
    }

    private static List<String> getClusterMembers(ZooKeeper zooKeeper) throws KeeperException, InterruptedException {
        return zooKeeper.getChildren(CLUSTER_ROOT, false);
    }
}
