package com.seata.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/22 16:34
 * @package: com.seata.fegin
 * @description:
 */
@FeignClient(value = "blank2",fallback = Blank2FeginFallback.class)
public interface BlankFegin {

    @GetMapping("/blank2/transfer")
    String transfer(@RequestParam("amount") Double amount);
}
