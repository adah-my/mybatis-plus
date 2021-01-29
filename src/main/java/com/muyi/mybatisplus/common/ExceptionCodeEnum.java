package com.muyi.mybatisplus.common;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用错误枚举
 */
@Getter
public enum ExceptionCodeEnum{

    /**
     * 通用结果
     */
    ERROR(-1, "网络错误"),
    NO_PERMISSION(0, "无操作权限"),
    SUCCESS(200, "成功"),
    ;

    private final Integer code;
    private final String desc;

    ExceptionCodeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    private static final Map<Integer, ExceptionCodeEnum> cache = new HashMap<>();

    static {
        ExceptionCodeEnum[] values = ExceptionCodeEnum.values();
        for (ExceptionCodeEnum exceptionCodeEnum : values)
        {
            cache.put(exceptionCodeEnum.code, exceptionCodeEnum);
        }
    }

    public static String getDesc(Integer code){
        ExceptionCodeEnum exceptionCodeEnum = cache.get(code);
        if (exceptionCodeEnum != null){
            return exceptionCodeEnum.getDesc();
        }
        return "";
    }
}