package com.zhs.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/9 10:05
 * @package: com.zhs.vo
 * @description:
 */
@Data
public class SmsCodeVo {

    private String code;

    private LocalDateTime expireTime;

    private String telephone;

    public SmsCodeVo(String code,int expireAfterSeconds,String telephone){
        this.telephone =  telephone;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
