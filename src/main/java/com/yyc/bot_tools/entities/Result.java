package com.yyc.bot_tools.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    public static final int SUCCESS_CODE = 200;

    private int code;
    private String message;
    private Long count;
    private T data;
    private boolean success;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数(返回后台失败的构造函数)
     *
     * @param code
     * @param message
     */
    public Result(int code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    /**
     * 构造函数（返回后台成功的构造函数，非分页）
     *
     * @param data
     */
    public Result(T data) {
        this.code = SUCCESS_CODE;
        this.data = data;
        this.success = true;
        this.count = null;
    }

    /**
     * 构造函数（返回后台成功的构造函数，非分页）
     *
     * @param
     */
    public Result() {
    }


    /**
     * 构造函数（返回后台成功的构造函数，分页）
     *
     * @param data
     * @param count
     */
    public Result(T data, long count) {
        this.code = SUCCESS_CODE;
        this.data = data;
        this.count = count;
        this.success = true;
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", count=" + count +
                ", data=" + data +
                ", success=" + success +
                '}';
    }
}
