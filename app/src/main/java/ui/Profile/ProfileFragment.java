package ui.Profile;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.myapplication.R;

import java.util.ArrayList;

import ui.Dashboard.recycle.Item;
import ui.Dashboard.recycle.RecordAdapter;

public class ProfileFragment extends Fragment {
    private ImageButton mchangeButton;

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
        return view;
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
