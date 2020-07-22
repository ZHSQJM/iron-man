package com.seata.service.impl;

import com.seata.dao.AccountInfoDao;
import com.seata.entity.AccountInfo;
import com.seata.fegin.BlankFegin;
import com.seata.service.IAccountInfoService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/21 15:18
 * @package: com.seata.service.impl
 * @description:
 */
@Service
public class AccountServiceImpl implements IAccountInfoService {

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private BlankFegin blankFegin;

    @Override
    public void save(AccountInfo accountInfo) {
        accountInfo.setAccountName("张三");
        accountInfo.setAccountNo("2");
        accountInfo.setAccountPassword("123");
        accountInfo.setAccountBalance(100D);
        accountInfo.setId(1);
        accountInfoDao.insert(accountInfo);
    }

    @Override
    @GlobalTransactional
    @Transactional(rollbackFor = Exception.class)
    public void updateAccountBalance(String accountNo, Double amount) {

        accountInfoDao.updateAccountBalance(accountNo,amount*-1);
        String transfer = blankFegin.transfer(amount);
         if("fallback".equalsIgnoreCase(transfer)){
             throw new RuntimeException("调用为服务移仓");
         }
    }
}
