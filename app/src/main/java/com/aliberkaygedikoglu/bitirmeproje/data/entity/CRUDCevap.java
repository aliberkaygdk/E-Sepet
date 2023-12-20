package com.aliberkaygedikoglu.bitirmeproje.data.entity;

public class CRUDCevap {
    private int success;
    private String message;

    public CRUDCevap() {
    }

    public CRUDCevap(int success, String message) {
        this.success = success;
        this.message = message;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
