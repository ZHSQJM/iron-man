package com.zhs.controller;

import com.zhs.common.IronResult;
import com.zhs.entity.sys.SysUser;
import com.zhs.service.sys.ISysUserService;
import com.zhs.utils.Contants;
import com.zhs.vo.SmsCodeVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/9 10:07
 * @package: com.zhs.controller
 * @description:
 */
@RestController
@Slf4j
public class SmsController {

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping("/sms")
    public IronResult sms(@RequestParam String telephone, HttpSession session){

        SysUser user = sysUserService.findUserByUserName(telephone);
        if(user!=null){
            SmsCodeVo smsCodeVo = new SmsCodeVo(RandomStringUtils.randomNumeric(4),60,telephone);
            session.setAttribute(Contants.TELEPHONE_SESSION_CODE,smsCodeVo);
            return IronResult.isOk(smsCodeVo.getCode(),"短信发送成功");
        }
         return IronResult.fail("手机号码未注册");
    }
}
