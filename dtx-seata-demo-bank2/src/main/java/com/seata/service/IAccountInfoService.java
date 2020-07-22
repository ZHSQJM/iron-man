package com.seata.service;

import com.seata.entity.AccountInfo;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/21 15:18
 * @package: com.seata.service
 * @description:
 */
public interface IAccountInfoService {

    void  save(AccountInfo accountInfo);

    void updateAccountBalance(String accountNo,Double amout);
}
