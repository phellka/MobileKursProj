package com.example.kursworkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.kursworkapplication.data.OrdersData;
import com.example.kursworkapplication.data.Reports.ReportsLogic;
import com.example.kursworkapplication.data.Reports.cutleriesOrders;
import com.example.kursworkapplication.data.Reports.lunchesOrders;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class reportLunchesOrdersActivity extends AppCompatActivity {

    ReportsLogic reportsLogic;
    String login = "";
    String role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_lunches_orders);

        SharedPreferences sPref = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        login = sPref.getString("login", "");
        role = sPref.getString("role", "");

        reportsLogic = new ReportsLogic(this, login);

        Intent intent = getIntent();
        int[] orders = intent.getIntArrayExtra("orders");

        List<lunchesOrders> list = reportsLogic.getLunchesByOrders(login,
                Arrays.stream(orders).boxed().collect(Collectors.toList()));

        TableLayout table = findViewById(R.id.reportLunchesOrdersTable);
        TableRow head = new TableRow(this);
        head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        TextView h1 = new TextView(this);
        h1.setText("Заказ");
        h1.setTextSize(18);
        h1.setTextColor(Color.parseColor("#D67777"));
        h1.setPadding(10, 5, 0, 0);
        head.addView(h1);
        TextView h2 = new TextView(this);
        h2.setText("Обед");
        h2.setTextSize(18);
        h2.setTextColor(Color.parseColor("#D67777"));
        h2.setPadding(20, 5, 0, 0);
        head.addView(h2);
        table.addView(head);
        final View vline = new View(this);
        vline.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
        vline.setBackgroundColor(Color.parseColor("#BC5454"));
        table.addView(vline);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        for(lunchesOrders lun : list){
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            TextView log = new TextView(this);
            log.setText(lun.getOrder());
            log.setTextSize(18);
            log.setTextColor(Color.parseColor("#D67777"));
            log.setMaxWidth(displayMetrics.widthPixels / 2);
            row.addView(log);
            TextView rol = new TextView(this);
            rol.setText(lun.getLunch());
            rol.setTextSize(18);
            rol.setTextColor(Color.parseColor("#D67777"));
            rol.setMaxWidth(displayMetrics.widthPixels / 2);
            row.addView(rol);
            table.addView(row);

            final View vline1 = new View(this);
            TableRow.LayoutParams params = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    1
            );
            params.setMargins(0, 5, 0, 5);
            vline1.setLayoutParams(params);
            vline1.setBackgroundColor(Color.parseColor("#BC5454"));
            table.addView(vline1);
        }

        Button save = findViewById(R.id.reportLunchesOrdersToPdf);
        save.setOnClickListener(v -> {

        });
    }
}