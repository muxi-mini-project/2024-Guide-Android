package ui.Dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.myapplication.R;

public class AnotherFragment extends DashboardFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // 加载该 Fragment 的布局文件
        View view = inflater.inflate(R.layout.fragment_another, container, false);

        // 在这里可以进行布局文件中控件的初始化和其他逻辑处理

        return view;
    }
}
