package com.seata.controller;

import com.seata.entity.AccountInfo;
import com.seata.service.IAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/22 13:52
 * @package: com.seata.controller
 * @description:
 */
@RestController
@RequestMapping
public class BlankController {


    @Autowired
    private IAccountInfoService accountInfoService;

    @GetMapping
    public String save(){
       accountInfoService.updateAccountBalance("2",20d);
       return "bank1"+20d;
    }
}
