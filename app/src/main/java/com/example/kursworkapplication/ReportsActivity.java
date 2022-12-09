package com.example.kursworkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.Objects;

public class ReportsActivity extends AppCompatActivity {

    String login = "";
    String role = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);

        SharedPreferences sPref = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        login = sPref.getString("login", "");
        role = sPref.getString("role", "");

        Button lunches = findViewById(R.id.reportsButtonLunches);
        Button cutleries = findViewById(R.id.reportsButtonCutleries);
        Button allUsers = findViewById(R.id.reportsButtonAllUsers);

        if (!Objects.equals(role, "admin")){
            allUsers.setVisibility(View.INVISIBLE);
        }

        allUsers.setOnClickListener(v -> {
            Intent intent = new Intent(this, reportUsersActivity.class);
            startActivity(intent);
        });
        cutleries.setOnClickListener(v -> {
            Intent intent = new Intent(this, reportCutleriesOrdersActivity.class);
            startActivity(intent);
        });
        lunches.setOnClickListener(v -> {
            Intent intent = new Intent(this, reportChoseOrdersActivity.class);
            startActivity(intent);
        });
    }
}