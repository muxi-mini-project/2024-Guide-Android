package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ui.MainActivity;

import com.example.myapplication.ApiAbout.Guide;
import com.example.myapplication.ApiAbout.LoginData;

public class LoginActivity extends AppCompatActivity {
    private Button mregisterbutton;
    private Button mButtonLogin;
    private EditText mEtUser;
    private EditText mEtPassword;
    private Button mbuttonforget;
    boolean passwordCheck = false;
    boolean firstCheck = true;
    private TextView textHint;

    private Guide api;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("150.158.114.182:8080/auth/register")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(Guide.class);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEtUser = findViewById(R.id.email);
        mbuttonforget = findViewById(R.id.forget_button);
        mEtPassword = findViewById(R.id.et_2);
        mregisterbutton = findViewById(R.id.register_button);
        mregisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent = new Intent(LoginActivity.this, RegisTer.class);
                startActivity(Intent);
            }
        });

        mButtonLogin = findViewById(R.id.buttonLogin);
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = mEtUser.getText().toString();
                String strPassword = mEtPassword.getText().toString();



                Call<LoginData> LoginCall = api.getLoginDate(strUsername, strPassword);
                LoginCall.enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {

                        Toast.makeText(LoginActivity.this, "请求成功", Toast.LENGTH_SHORT).show();

                        LoginData body = response.body();
                        if (body == null) {
                            Toast.makeText(LoginActivity.this, "响应体为空", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (body.getLogin().equals("Yes")) {
                            passwordCheck = true;
                        } else passwordCheck = false;

                        if (!passwordCheck) textHint.setText("用户名或密码错误！");
                        else {
                            SharedPreferences sharedPrefer = getSharedPreferences("User_Details", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPrefer.edit();
                            editor.putString("token", body.getToken());
                            editor.apply();

                            Intent intent;
                            intent = MainActivity.newIntent(LoginActivity.this, strUsername);
                            startActivity(intent);
                        }

                    }


                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    protected void onCreate() {
    }
}