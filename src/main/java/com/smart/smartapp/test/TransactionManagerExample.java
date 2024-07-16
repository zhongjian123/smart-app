package com.smart.smartapp.test;

import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description: spring事务管理
 * @author: zhizj
 * @date: 2024/7/1
 */
public class TransactionManagerExample {


    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout = 60,readOnly = true,rollbackFor = {RuntimeException.class})
    private void managerTransaction(){
        // 业务处理
        System.out.println("-----");
    }

}
