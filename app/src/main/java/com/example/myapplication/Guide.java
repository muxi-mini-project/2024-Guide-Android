package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Guide {
    @GET("api/user/detail")
    @POST("/LoginRequest")
    Call<LoginData>getLoginDate(@Field("email")String Email,@Field("password")String Password);
    @FormUrlEncoded
    @POST("/ResetPasswordRequest")
    Call<ResetPasswordRequest>getPasswordData(
            @Field("email")String Email,
            @Field("password")String Password,
            @Field("verificationcode")String VerificationCode);
    @POST("/FigureRegistrationRequest")
    Call<RegisterData>getRegisterData(@Field("username")String Username);
}