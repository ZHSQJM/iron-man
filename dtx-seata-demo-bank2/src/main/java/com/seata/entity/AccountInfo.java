package com.seata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/21 15:13
 * @package: com.seata.entity
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountInfo {

    private int id;
    private String accountName;
    private String accountNo;
    private String accountPassword;
    private Double accountBalance;


}
