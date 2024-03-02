package com.example.myapplication;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class Activity extends Login {
    private static Guide api;
    public void onCreate(){
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/auth/login")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Guide.class);
    }
    public static Guide getApi(){
        return api;
    }
}
