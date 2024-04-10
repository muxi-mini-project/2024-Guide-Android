package ui.Dashboard.recycle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class RecordAdapter extends RecyclerView.Adapter<RecordAdapter.RecordViewHolder> {
    private ArrayList<Item> itemList;

    public RecordAdapter(ArrayList<Item> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new RecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.time.setText(item.getTexttime());
        holder.name.setText(item.getTextname());
        holder.style.setText(item.getTextstyle());
        holder.level.setText(item.getTextlevel());

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setItemList(ArrayList<Item> itemList) {

        this.itemList = itemList;
        notifyDataSetChanged(); // 通知适配器数据已更改
    }

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView time;
        public TextView style;
        public TextView level;
        public RecordViewHolder(@NonNull View itemView) {
            super(itemView);
            level = itemView.findViewById(R.id.level);
            time = itemView.findViewById(R.id.texttime);
            style = itemView.findViewById(R.id.textstyle);
            name = itemView.findViewById(R.id.textname);
        }
    }
}

