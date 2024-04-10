package com.example.myapplication.Data;

import java.util.ArrayList;

import ui.Dashboard.recycle.Item;

public class DataManager {
    private static DataManager instance;
    private ArrayList<Item> itemList;

    private DataManager() {
        itemList = new ArrayList<>();
    }

    public static synchronized DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public ArrayList<Item> getItemList() {
        return itemList;
    }

    public void addItem(Item item) {
        itemList.add(item);
    }

    // 其他操作数据的方法...
}
