package ui.Profile;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.myapplication.R;

import java.util.ArrayList;

import ui.Dashboard.AnotherFragment;
import ui.Dashboard.recycle.Item;
import ui.Dashboard.recycle.RecordAdapter;

public class ProfileFragment extends Fragment {
    private ImageButton mchangeButton;
    private ImageButton soloImageButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // 在这里指定 ProfileFragment 的界面布局
        mchangeButton = view.findViewById(R.id.changeButton);
        mchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecyclerDialog ();
            }
        });
        soloImageButton = view.findViewById(R.id.solo);

        // 设置点击事件
        soloImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建并启动新的 Fragment
                openNewFragment();
            }
        });
        return view;
    }
    private void openNewFragment() {
        // 创建新的 Fragment 实例
        AnotherFragment anotherFragment = new AnotherFragment();

        // 获取 FragmentManager
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // 开始 Fragment 事务
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // 将新的 Fragment 替换为当前 Fragment
        fragmentTransaction.replace(R.id.fragment_container, anotherFragment);

        // 添加事务到后台堆栈（可选）
        fragmentTransaction.addToBackStack(null);

        // 提交事务
        fragmentTransaction.commit();
    }








        public void showRecyclerDialog () {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_with_recycler, null);
            builder.setView(dialogView);

            RecyclerView recyclerView = dialogView.findViewById(R.id.recyclerView2);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            ArrayList<Item> itemList = generateItemList(); // 示例方法，用于生成数据
            RecordAdapter adapter = new RecordAdapter(itemList);
            recyclerView.setAdapter(adapter);

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        private ArrayList<Item> generateItemList () {
            ArrayList<Item> itemList = new ArrayList<>();
            // 这里根据需要生成数据并添加到列表中
            return itemList;
        }
    }
