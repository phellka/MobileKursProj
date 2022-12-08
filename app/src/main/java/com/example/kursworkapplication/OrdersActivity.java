package com.example.kursworkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.kursworkapplication.data.Order;
import com.example.kursworkapplication.data.OrdersData;

import java.util.ArrayList;
import java.util.List;

public class OrdersActivity extends AppCompatActivity {

    String login = "";
    OrdersData ordersData;
    ArrayAdapter<Order> adapter;
    ListView listViewOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orders);

        SharedPreferences sPref = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        login = sPref.getString("login", "");
        ordersData = new OrdersData(this, login);

        listViewOrders = findViewById(R.id.ordersListView);
        Button add = findViewById(R.id.ordersButtonAdd);
        Button upd = findViewById(R.id.ordersButtonChange);
        Button del = findViewById(R.id.ordersButtonDelete);

        adapter = new ArrayAdapter<Order>(this, R.layout.listitem,
                ordersData.findAllOrders(login));
        listViewOrders.setAdapter(adapter);
        listViewOrders.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter.notifyDataSetChanged();

        add.setOnClickListener(v -> {
            Intent intent = new Intent(this, OrderActivity.class);
            //startActivity(intent);
            startActivityForResult(intent, 99);
            adapter.notifyDataSetChanged();
        });
        upd.setOnClickListener(v -> {
            int order = -1;
            SparseBooleanArray sparseBooleanArray = listViewOrders.getCheckedItemPositions();
            for (int i = 0; i < listViewOrders.getCount(); ++i){
                if(sparseBooleanArray.get(i) == true){
                    order = adapter.getItem(i).getId();
                }
            }
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("Id", order);
            //startActivity(intent);
            startActivityForResult(intent, 99);
            adapter.notifyDataSetChanged();
            listViewOrders.clearChoices();
        });
        del.setOnClickListener(v -> {
            int order = -1;
            SparseBooleanArray sparseBooleanArray = listViewOrders.getCheckedItemPositions();
            for (int i = 0; i < listViewOrders.getCount(); ++i) {
                if (sparseBooleanArray.get(i) == true) {
                    order = adapter.getItem(i).getId();
                }
            }
            if (order != -1) {
                ordersData.deleteOrder(order, login);
                adapter.notifyDataSetChanged();
            }
            listViewOrders.clearChoices();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();
    }
}