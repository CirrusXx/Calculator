package com.example.adam.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    DataBaseManager dataBase = new DataBaseManager(this);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TextView listView = findViewById(R.id.historyView);
        listView.setText(dataBase.getData().toString());
    }

    public void back(View view) {
        finish();
    }

    public void deleteHistoryOnClick(View view) {
        dataBase.clearDatabase();
    }
}
