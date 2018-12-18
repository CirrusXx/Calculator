package com.example.adam.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class HistoryActivity extends AppCompatActivity {

    private DataBaseCalculator dataBase = new DataBaseCalculator(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setListView(dataBase.getData().toString());
    }

    public void back(View view) {
        finish();
    }

    public void deleteHistoryOnClick(View view) {
        dataBase.clearDatabase();
    }

    private void setListView(String data) {
        TextView listView = findViewById(R.id.historyView);
        listView.setText(data);
    }
}
