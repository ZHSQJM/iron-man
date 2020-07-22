package com.seata.mapper;

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


    @Update("UPDATE account_info SET account_balance = account_balance +#{amount} WHERE account_no = #{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo,@Param("amount") Double amount);
}
