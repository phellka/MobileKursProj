package com.example.kursworkapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.kursworkapplication.data.Lunch;
import com.example.kursworkapplication.data.LunchesData;

public class LunchesActivity extends AppCompatActivity {
    String login = "";
    LunchesData lunchesData;
    ArrayAdapter<Lunch> adapter;
    ListView listViewLunches;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunches);

        SharedPreferences sPref = getSharedPreferences("User", MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        login = sPref.getString("login", "");
        lunchesData = new LunchesData(this, login);

        listViewLunches = findViewById(R.id.lunchesListView);
        Button add = findViewById(R.id.lunchesButtonAdd);
        Button upd = findViewById(R.id.lunchesButtonChange);
        Button del = findViewById(R.id.lunchesButtonDelete);

        adapter = new ArrayAdapter<Lunch>(this, R.layout.listitem,
                lunchesData.findAllLunches(login));
        listViewLunches.setAdapter(adapter);
        listViewLunches.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        adapter.notifyDataSetChanged();

        add.setOnClickListener(v -> {
            Intent intent = new Intent(this, LunchActivity.class);
            startActivityForResult(intent, 99);
            adapter.notifyDataSetChanged();
        });
        upd.setOnClickListener(v -> {
            int lunch = -1;
            SparseBooleanArray sparseBooleanArray = listViewLunches.getCheckedItemPositions();
            for (int i = 0; i < listViewLunches.getCount(); ++i){
                if(sparseBooleanArray.get(i) == true){
                    lunch = adapter.getItem(i).getId();
                }
            }
            if (lunch == -1){
                return;
            }
            Intent intent = new Intent(this, LunchActivity.class);
            intent.putExtra("Id", lunch);
            startActivityForResult(intent, 99);
            adapter.notifyDataSetChanged();
            listViewLunches.clearChoices();
        });
        del.setOnClickListener(v -> {
            int lunch = -1;
            SparseBooleanArray sparseBooleanArray = listViewLunches.getCheckedItemPositions();
            for (int i = 0; i < listViewLunches.getCount(); ++i) {
                if (sparseBooleanArray.get(i) == true) {
                    lunch = adapter.getItem(i).getId();
                }
            }
            if (lunch != -1) {
                lunchesData.deleteLunch(lunch, login);
                adapter.notifyDataSetChanged();
            }
            listViewLunches.clearChoices();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.notifyDataSetChanged();
    }
}