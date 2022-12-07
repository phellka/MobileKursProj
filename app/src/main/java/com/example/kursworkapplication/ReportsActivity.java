package com.example.kursworkapplication;

import androidx.appcompat.app.AppCompatActivity;

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
        Button allLunches = findViewById(R.id.reportsButtonAllLunches);
        Button allCutleries = findViewById(R.id.reportsButtonAllCutleries);

        if (!Objects.equals(role, "admin")){
            allCutleries.setVisibility(View.INVISIBLE);
            allLunches.setVisibility(View.INVISIBLE);
        }

        allLunches.setOnClickListener(v -> {
            int a = 10;
        });
    }
}