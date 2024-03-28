package ui.Dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import com.example.myapplication.R;

import ui.Dashboard.recycle.Item;
import ui.Dashboard.recycle.RecordAdapter;

public class DashboardFragment extends Fragment {
    private RecyclerView mrecyclerView;
    private RecordAdapter adapter;
    private ArrayList<Item> itemList;
    private ImageButton addButton;
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
                generateItemList();
            }
        });
        return view;
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