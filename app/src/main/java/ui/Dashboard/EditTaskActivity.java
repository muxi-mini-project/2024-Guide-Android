package ui.Dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

public class EditTaskActivity extends AppCompatActivity {
    private EditText editTextName;
    private ImageButton finishButton;
    private TextInputEditText levelTextInputEditText;
    private TextInputEditText timetextInputEditText;
    private TextInputEditText styletextInputEditText;
    private TextInputEditText miaoshutextInputEditText;
    private EditText nameTextText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);
        editTextName = findViewById(R.id.nameTextText);
        finishButton = findViewById(R.id.finnishImageButton);
        levelTextInputEditText = findViewById(R.id.leveltextInputEditText);
        timetextInputEditText = findViewById(R.id.timetextInputEditText);
        styletextInputEditText = findViewById(R.id.styletextInputEditText);
        miaoshutextInputEditText = findViewById(R.id.miaoshutextInputEditText);
        nameTextText = findViewById(R.id.nameTextText);

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        // 从EditText中获取用户输入的值
        String level = levelTextInputEditText.getText().toString();
        String time = timetextInputEditText.getText().toString();
        String style = styletextInputEditText.getText().toString();
        String description = miaoshutextInputEditText.getText().toString();
        String name = nameTextText.getText().toString();

        // 在此处将获取到的值用于保存任务的逻辑
        // 例如，你可以创建一个任务对象，并将获取到的值传递给任务对象的属性
        // 然后你可以将任务对象保存到数据库或进行其他操作
        Intent resultIntent = new Intent();
        // 将获取到的值放入Intent中
        resultIntent.putExtra("textname", name);
        resultIntent.putExtra("texttime", time);
        resultIntent.putExtra("textstyle", style);
        resultIntent.putExtra("level", level);

        // 设置结果代码为RESULT_OK，表示成功
        setResult(Activity.RESULT_OK, resultIntent);
        // 结束当前Activity
        finish();
    }
}
