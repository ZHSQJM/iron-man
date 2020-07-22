package com.seata.service.impl;

import com.seata.entity.AccountInfo;
import com.seata.mapper.AccountInfoDao;
import com.seata.service.IAccountInfoService;
import io.seata.core.context.RootContext;
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

    @Override
    public void save(AccountInfo accountInfo) {
        accountInfo.setAccountName("李四");
        accountInfo.setAccountNo("1");
        accountInfo.setAccountPassword("123");
        accountInfo.setAccountBalance(100D);
        accountInfo.setId(1);
        accountInfoDao.insert(accountInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateAccountBalance(String accountNo, Double amout) {
        accountInfoDao.updateAccountBalance(accountNo,amout);
        int i = 2/0;
        System.out.println("全局事务日志"+RootContext.getXID());
    }
}
