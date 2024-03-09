package com.example.myapplication;

public class RegisterData <type>{
    private int code;
    private String massage;
    private type data;

    public int getCode() {
        return code;
    }

    public String getMassage() {
        return massage;
    }

    public type getData() {
        return data;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setData(type data) {
        this.data = data;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }
}
