package com.example.kursworkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.kursworkapplication.data.Reports.ReportsLogic;
import com.example.kursworkapplication.data.Reports.allUsersUnit;

import java.util.List;

public class reportUsersActivity extends AppCompatActivity {

    ReportsLogic reportsLogic;
    String login = "";
    String role = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_users);

        SharedPreferences sPref = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        login = sPref.getString("login", "");
        role = sPref.getString("role", "");

        reportsLogic = new ReportsLogic(this, login);

        List<allUsersUnit> list = reportsLogic.getAllUsersData(login, role);

        TableLayout table = findViewById(R.id.reportUsersTable);
        table.removeAllViews();

        TableRow head = new TableRow(this);
        head.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        TextView h1 = new TextView(this);
        h1.setText("Логин");
        h1.setTextSize(18);
        h1.setTextColor(Color.parseColor("#D67777"));
        h1.setPadding(10, 0, 0, 0);
        head.addView(h1);
        TextView h2 = new TextView(this);
        h2.setText("Роль");
        h2.setTextSize(18);
        h2.setTextColor(Color.parseColor("#D67777"));
        h2.setPadding(20, 0, 0, 0);
        head.addView(h2);
        TextView h3 = new TextView(this);
        h3.setText("Заказы");
        h3.setTextSize(18);
        h3.setTextColor(Color.parseColor("#D67777"));
        h3.setPadding(20, 0, 0, 0);
        head.addView(h3);
        TextView h4 = new TextView(this);
        h4.setText("Обеды");
        h4.setTextSize(18);
        h4.setTextColor(Color.parseColor("#D67777"));
        h4.setPadding(20, 0, 0, 0);
        head.addView(h4);
        TextView h5 = new TextView(this);
        h5.setText("Приборы");
        h5.setTextSize(18);
        h5.setTextColor(Color.parseColor("#D67777"));
        h5.setPadding(20, 0, 0, 0);
        head.addView(h5);
        table.addView(head);
        final View vline = new View(this);
        vline.setLayoutParams(new
                TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 2));
        vline.setBackgroundColor(Color.parseColor("#BC5454"));
        table.addView(vline);


        for(allUsersUnit userUnit : list){
            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            TextView log = new TextView(this);
            log.setText(userUnit.getLogin());
            log.setTextSize(18);
            log.setTextColor(Color.parseColor("#D67777"));
            log.setPadding(10, 0, 0, 0);
            row.addView(log);
            TextView rol = new TextView(this);
            rol.setText(userUnit.getRole());
            rol.setTextSize(18);
            rol.setTextColor(Color.parseColor("#D67777"));
            rol.setPadding(20, 0, 0, 0);
            row.addView(rol);
            TextView ord = new TextView(this);
            ord.setText(Integer.toString(userUnit.getOrdersCount()));
            ord.setTextSize(18);
            ord.setTextColor(Color.parseColor("#D67777"));
            ord.setPadding(20, 0, 0, 0);
            row.addView(ord);
            TextView lun = new TextView(this);
            lun.setText(Integer.toString(userUnit.getLunchesCount()));
            lun.setTextSize(18);
            lun.setTextColor(Color.parseColor("#D67777"));
            lun.setPadding(20, 0, 0, 0);
            row.addView(lun);
            TextView cut = new TextView(this);
            cut.setText(Integer.toString(userUnit.getCutleriesCount()));
            cut.setTextSize(18);
            cut.setTextColor(Color.parseColor("#D67777"));
            cut.setPadding(20, 0, 0, 0);
            row.addView(cut);
            table.addView(row);

            final View vline1 = new View(this);
            vline1.setLayoutParams(new
                    TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, 1));
            vline1.setBackgroundColor(Color.parseColor("#BC5454"));
            table.addView(vline1);
        }

        Button save = findViewById(R.id.reportUsersToPdf);
        save.setOnClickListener(v -> {

        });
    }
}