package com.zhs.config;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/13 14:00
 * @package: com.zhs.config
 * @description:
 */
@Configuration
public class TransactionManagerConfig {


    @Bean
    public UserTransaction userTransaction()throws SystemException{
        UserTransaction userTransaction = new UserTransactionImp();
        userTransaction.setTransactionTimeout(10000);
        return userTransaction;
    }

    @Bean(name ="atomikosTransactionManager",initMethod = "init",destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() throws Throwable{

        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    @Bean(name = "transactionManager")
    @DependsOn({"userTransaction","atomikosTransactionManager"})
    public PlatformTransactionManager transactionManager() throws Throwable{
        final UserTransaction userTransaction = userTransaction();
        JtaTransactionManager manager = new JtaTransactionManager(userTransaction,atomikosTransactionManager());
        return manager;
    }
}
