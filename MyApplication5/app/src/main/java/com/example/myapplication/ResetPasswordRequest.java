package com.example.myapplication;

public class ResetPasswordRequest {
    private String Email;
    private  String VerificationCode;

    public String getEmail() {
        return Email;
    }

    public String getVerificationCode() {
        return VerificationCode;
    }
    public void setResetPasswordRequest(String resetpasswordrequest) {
        String ResetPasswordRequest = resetpasswordrequest;
    }
}