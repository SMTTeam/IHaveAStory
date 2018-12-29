package com.smtteam.smt.common.enums;

/**
 * 结果码
 */
public enum ResultCode {
    SUCCESS(200), NOT_ACCESS(204), ERROR(400), NOT_FOUND(404);

    private int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }}
