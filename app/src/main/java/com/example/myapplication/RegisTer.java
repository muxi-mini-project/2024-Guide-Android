package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import com.example.myapplication.ApiAbout.Request;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;








public class RegisTer extends AppCompatActivity {
    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private EditText emailEditText;
    private EditText verificationCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis_ter);

        usernameEditText = findViewById(R.id.new_id);
        passwordEditText = findViewById(R.id.id_password);
        confirmPasswordEditText = findViewById(R.id.id_password_again);
        emailEditText = findViewById(R.id.mail);
        verificationCodeEditText = findViewById(R.id.check_work);
        findViewById(R.id.send).setOnClickListener(view -> {
            // 在点击send按钮时调用sendVerificationCode()方法发送验证码
            sendVerificationCode();
        });
    }
    // 执行注册逻辑
    private void register() {
        // 获取输入框中的注册信息

        // 检查输入是否有效
        String username = usernameEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String verificationCode = verificationCodeEditText.getText().toString();
        // 检查输入是否有效
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || email.isEmpty() || verificationCode.isEmpty()) {
            Toast.makeText(RegisTer.this, "请填写完整的注册信息", Toast.LENGTH_SHORT).show();
            return;
        }

        // 检查两次输入的密码是否一致
        if (!password.equals(confirmPassword)) {
            Toast.makeText(RegisTer.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }

        // 创建一个 Request 对象来存储注册信息
        Request request = new Request();
        request.setUsername(username);
        request.setPassword(password);
        request.setConfirmPassword(confirmPassword);
        request.setEmail(email);

        // 调用后端API接口注册用户
        OkHttpClient client = new OkHttpClient();
        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody = RequestBody.create(mediaType, requestToJson(request));

        // 创建HTTP请求
        okhttp3.Request httpRequest = new okhttp3.Request.Builder()
                .url("http://150.158.114.182:8080/register/")
                .post(requestBody)
                .build();

        // 发送注册请求
        client.newCall(httpRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(RegisTer.this, "注册失败，请重试", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    runOnUiThread(() -> Toast.makeText(RegisTer.this, "注册失败，请重试", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(RegisTer.this, "注册成功", Toast.LENGTH_SHORT).show());
                    // TODO: 注册成功后的处理，例如跳转到登录界面
                }
            }
        });
    }

    // 将 Request 对象转换为 JSON 字符串
    private String requestToJson(Request request) {
        Gson gson = new Gson();
        return gson.toJson(request);
    }

    // 发送验证码
    private void sendVerificationCode() {
        // 获取用户输入的邮箱地址
        String email = emailEditText.getText().toString();

        // 检查邮箱地址是否为空
        if (email.isEmpty()) {
            Toast.makeText(this, "请输入邮箱地址", Toast.LENGTH_SHORT).show();
            return;
        }

        // 创建请求体
        RequestBody requestBody = new FormBody.Builder()
                .add("email", email)
                .build();

        // 创建HTTP请求
        OkHttpClient client = new OkHttpClient();
        okhttp3.Request httpRequest = new okhttp3.Request.Builder()
                .url("http://150.158.114.182:8080/auth/send_verification_email/")
                .post(requestBody)
                .build();

        // 发送HTTP请求
        client.newCall(httpRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(() -> Toast.makeText(RegisTer.this, "发送验证码失败，请重试", Toast.LENGTH_SHORT).show());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    runOnUiThread(() -> Toast.makeText(RegisTer.this, "发送验证码失败，请重试", Toast.LENGTH_SHORT).show());
                } else {
                    runOnUiThread(() -> Toast.makeText(RegisTer.this, "验证码已发送至您的邮箱", Toast.LENGTH_SHORT).show());
                }
            }
        });
    }
}