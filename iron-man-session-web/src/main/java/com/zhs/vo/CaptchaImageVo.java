package com.zhs.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/8 16:40
 * @package: com.zhs.vo
 * @description:
 */
@Data
public class CaptchaImageVo {

    private String code;

    private LocalDateTime expireTime;

    public CaptchaImageVo(String code,int expireAfterSeconds){

        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
