package ui.Dashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import com.example.myapplication.R;
import com.google.android.material.textfield.TextInputEditText;

import ui.Dashboard.recycle.Item;
import ui.Dashboard.recycle.RecordAdapter;

public class DashboardFragment extends Fragment {
    private RecyclerView mrecyclerView;
    private RecordAdapter adapter;
    private ArrayList<Item> itemList;
    private ImageButton addButton;
    private static final int EDIT_TASK_REQUEST_CODE = 1;

    private void startEditTaskActivity() {
        Intent intent = new Intent(getActivity(), EditTaskActivity.class);
        startActivityForResult(intent, EDIT_TASK_REQUEST_CODE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mrecyclerView = view.findViewById(R.id.recyclerView);
        addButton = view.findViewById(R.id.addButton); // 找到按钮
        itemList = generateItemList();
        adapter = new RecordAdapter(itemList);
        mrecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mrecyclerView.setAdapter(adapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击按钮后执行添加任务的操作
                startEditTaskActivity();
            }
        });
        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            // 从编辑界面返回的数据
            String name = data.getStringExtra("textname");
            String time = data.getStringExtra("texttime");
            String style = data.getStringExtra("textstyle");
            String level = data.getStringExtra("level");

            // 创建一个新的 Item 实例并添加到 itemList 中
            Item newItem = new Item();
            newItem.setTextname(name);
            newItem.setTexttime(time);
            newItem.setTextstyle(style);
            newItem.setTextlevel(level);
            itemList.add(newItem);

            // 更新 RecyclerView
            adapter.notifyItemInserted(itemList.size() - 1);
        }
    }


    private void updateItemInRecyclerView(Item editedItem) {
        // 在RecyclerView中查找并更新相应的项目
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getTextname() == editedItem.getTextname()) {
                itemList.set(i, editedItem);
                adapter.notifyItemChanged(i);
                break;
            }
        }
    }
    private ArrayList<Item> generateItemList() {
        ArrayList<Item> itemList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Item item = new Item();
            item.setTextlevel("lv5");
            item.setTextname("死亡日");
            item.setTextstyle("个人任务");
            item.setTexttime("4.4");
            itemList.add(item);
        }
        return itemList;
    }
}