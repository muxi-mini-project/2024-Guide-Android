package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.res.Resources;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.util.DisplayMetrics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.ImageView;
public class Login extends AppCompatActivity {
    private Button mregisterbutton;
    private Button mbuttonLogin;
    private EditText mEtuser;
    private EditText mEtPassword;
    boolean passwordCheck = false;
    boolean firstCheck = true;
    private TextView textHint;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mregisterbutton = findViewById(R.id.register_button);
        mEtuser = findViewById(R.id.email);
        mEtPassword = findViewById(R.id.et_2);
        mregisterbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(Login.this, RegisTer.class);
                startActivity(intent);
            }
        });
        mbuttonLogin = findViewById(R.id.buttonLogin);
        mbuttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strUsername = mEtuser.getText().toString();
                String strPassword = mEtPassword.getText().toString();
                Guide api = Activity.getApi();
                Call<LoginData> LoginCall = api.getLoginDate(strUsername, strPassword);
                LoginCall.enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        Toast.makeText(Login.this, "请求成功", Toast.LENGTH_SHORT).show();
                        LoginData body = response.body();
                        if (body == null) {
                            Toast.makeText(Login.this, "响应体为空", Toast.LENGTH_SHORT).show();
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
                            intent = MainActivity.newIntent(Login.this, strUsername);
                            startActivity(intent);
                        }
                    }


                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Toast.makeText(Login.this, "请求失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        mbuttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = MainActivity.newIntent(Login.this, "0");
                startActivity(intent);
            }
        });
        setContentView(R.layout.login);

        ImageView imageView = findViewById(R.id.deng_ru);

        // 获取屏幕密度
        float density = getResources().getDisplayMetrics().density;

        // 如果是 xxhdpi 设备，加载相应资源
        if (density >= 3.0) {
            imageView.setImageResource(R.drawable.login);
        }
    }

    protected void onCreate() {
    }
}