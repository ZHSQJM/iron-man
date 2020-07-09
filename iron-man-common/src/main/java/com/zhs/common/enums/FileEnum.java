package com.zhs.common.enums;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @project: iron-man
 * @author: zhs
 * @date: 2020/7/5 20:29
 * @package: com.zhs.common.enums
 * @description:
 */
@Getter
public enum FileEnum {

    VIDEO("V","视频"),
    PICTURE("C","图片");
    private String code;
    private String desc;

    FileEnum(String code,String desc){
        this.code = code;
        this.desc =desc;
    }

    public static FileEnum getByCode(String code){
        for (FileEnum e:FileEnum.values()){
            if(code.equals(e.getCode())){
                return e;
            }
        }
        return null;
    }
}
