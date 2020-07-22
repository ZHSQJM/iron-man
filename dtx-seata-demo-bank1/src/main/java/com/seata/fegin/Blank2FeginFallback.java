package com.seata.fegin;

import org.springframework.stereotype.Component;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/22 16:38
 * @package: com.seata.fegin
 * @description:
 */

@Component
public class Blank2FeginFallback implements BlankFegin {
    @Override
    public String transfer(Double amount) {
        return "fallback";
    }
}
