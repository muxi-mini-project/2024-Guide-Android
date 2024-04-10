package ui.Home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.myapplication.R;

public class HomeFragment extends Fragment {
    private RelativeLayout nameBtn;
    private TextView nicknameTextView;
    private RelativeLayout settingBtn;
    private RelativeLayout setSexBtn;
    private RelativeLayout yearChoose;
    private TextView selectedAgeTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 在这里指定 HomeFragment 的界面布局
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        yearChoose = view.findViewById(R.id.year_choose);
        selectedAgeTextView = view.findViewById(R.id.age_text_view);

        yearChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAgePickerDialog();
            }
        });


        setSexBtn = view.findViewById(R.id.setsex_btn);
        setSexBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGenderPopup();
            }
        });


        settingBtn = view.findViewById(R.id.setting_btn);
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettingPopup();
            }
        });


        nameBtn = view.findViewById(R.id.name_btn);
        nameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNicknameDialog();
            }
        });
        nicknameTextView = view.findViewById(R.id.nickname_text_view);
        return view;
    }
    private void updateNickname(String newNickname) {
        // 在这里更新显示昵称的 TextView
        nicknameTextView.setText(newNickname);
    }
    private void showNicknameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View customView = getLayoutInflater().inflate(R.layout.custom_layout, null);
        builder.setView(customView);

        final EditText input = customView.findViewById(R.id.editTextNickname);

        // 设置确定按钮
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 获取输入的新昵称
                String newNickname = input.getText().toString();
                // 处理修改昵称的逻辑
                updateNickname(newNickname);
            }
        });

        // 设置取消按钮
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // 显示弹窗
        builder.show();
    }
    private void showSettingPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View customView = getLayoutInflater().inflate(R.layout.popup_layout, null);
        builder.setView(customView);

        // 设置弹窗中的按钮点击事件
        Button alarmPermissionButton = customView.findViewById(R.id.alarm_permission_button);
        alarmPermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理设置闹钟权限的逻辑
                // requestAlarmPermission();
            }
        });

        Button galleryPermissionButton = customView.findViewById(R.id.gallery_permission_button);
        galleryPermissionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理设置相册权限的逻辑
                // requestGalleryPermission();
            }
        });

        Button logoutButton = customView.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理退出登录的逻辑
                // logout();
            }
        });

        // 显示弹窗
        builder.show();
    }
    private void showGenderPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View customView = getLayoutInflater().inflate(R.layout.gender_popup_layout, null);
        builder.setView(customView);

        // 设置弹窗中的按钮点击事件
        Button maleButton = customView.findViewById(R.id.male_button);
        maleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理选择男性的逻辑
               setGender("male");
            }
        });

        Button femaleButton = customView.findViewById(R.id.female_button);
        femaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 处理选择女性的逻辑
               setGender("female");
            }
        });

        // 显示弹窗
        builder.show();
    }

    private void setGender(String gender) {
        SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("gender", gender);
        editor.apply();

        // 更新TextView的文本
        TextView sexTextView = getView().findViewById(R.id.sex);
        if (gender.equals("male")) {
            sexTextView.setText("男");
        } else if (gender.equals("female")) {
            sexTextView.setText("女");
        }
    }

    private void showAgePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_age_picker, null);
        builder.setView(dialogView);

        final NumberPicker agePicker = dialogView.findViewById(R.id.age_picker);
        agePicker.setWrapSelectorWheel(false);
        agePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                selectedAgeTextView.setText(String.valueOf(newVal));
            }
        });

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle OK button click if needed
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle Cancel button click if needed
            }
        });

        builder.show();
    }
}
