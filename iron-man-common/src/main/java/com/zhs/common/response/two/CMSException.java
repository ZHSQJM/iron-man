package com.zhs.common.response.two;

import com.zhs.common.response.two.ResultCodeEnum;
import lombok.Data;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/23 14:04
 * @package: com.zhs.common.exception
 * @description:
 */
@Data
public class CMSException extends RuntimeException{
    private Integer code;

    public CMSException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public CMSException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "CMSException{" + "code=" + code + ", message=" + this.getMessage() + '}';
    }

}
