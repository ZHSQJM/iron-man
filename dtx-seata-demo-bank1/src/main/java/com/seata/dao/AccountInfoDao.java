package com.seata.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seata.entity.AccountInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/21 15:17
 * @package: com.seata.dao
 * @description:
 */
public interface AccountInfoDao extends BaseMapper<AccountInfo> {

    @Update("update account_info set account_balance = account_balance +#{amount} where account_no = #{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo,@Param("amount") Double amout);
}
