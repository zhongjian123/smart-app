package com.smart.smartapp;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.Set;

/**
 * @description: activemq读取剩余消息队列中消息的数量
 * 需要修改activemq的配置文件 https://blog.csdn.net/cainiaofeitian/article/details/52065755
 * @author: zhizj
 * @date: 2023/8/8
 */
@SpringBootTest
public class ActiveMQTests {

    @Test
    public void countForAllQueuesTest() {
        String brokerUrl = "tcp://192.168.163.11:61616"; // ActiveMQ Broker 的连接 URL
        String userName = "admin";
        String password = "re99dRLY@hFt";
        try {
            // 创建连接工厂
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(userName, password, brokerUrl);

            // 创建连接和会话
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // 关闭资源
            session.close();
            connection.close();

            // 使用 JMX 连接获取所有队列的剩余消息数量
            getRemainingMessageCountForAllQueues(brokerUrl);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private static void getRemainingMessageCountForAllQueues(String brokerUrl) {
        try {
            // 创建 JMX 连接
            JMXServiceURL serviceUrl = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + brokerUrl + "/jmxrmi");
            JMXConnector jmxConnector = JMXConnectorFactory.connect(serviceUrl, null);
            MBeanServerConnection mBeanServerConnection = jmxConnector.getMBeanServerConnection();

            // 查找所有队列的 MBean
            ObjectName query = new ObjectName("org.apache.activemq:type=Broker,brokerName=localhost,destinationType=Queue,*");
            Set<ObjectName> mbeans = mBeanServerConnection.queryNames(query, null);

            for (ObjectName mbeanName : mbeans) {
                String queueName = mbeanName.getKeyProperty("destinationName");
                BrokerMBean queueProxy = MBeanServerInvocationHandler.newProxyInstance(mBeanServerConnection, mbeanName, BrokerMBean.class, false);

                int remainingMessageCount = queueProxy.getQueueSize();
                System.out.println("Remaining message count for queue '" + queueName + "': " + remainingMessageCount);
            }

            // 关闭 JMX 连接
            jmxConnector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    interface BrokerMBean {
        int getQueueSize();
    }

}
