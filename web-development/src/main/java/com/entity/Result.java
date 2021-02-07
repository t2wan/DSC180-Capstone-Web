package com.entity;

import java.util.List;
import java.util.Map;

public class Result {
    private int code;   //响应状态码
    private String message;   //响应信息
    private Map<String,Object> data;  //响应数据

    public Result() {
    }

    public Result(int code, String message, Map<String, Object> data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
