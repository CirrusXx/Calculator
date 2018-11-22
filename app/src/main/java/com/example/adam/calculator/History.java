package com.example.adam.calculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Intent intent = getIntent();

        ArrayList<String> listOfHistory = intent.getStringArrayListExtra("history");


        TextView listView = findViewById(R.id.historyView);

        for(String i : listOfHistory)
        {
            listView.setText(listView.getText()+i+"\n");
        }


    }
    public void back(View view)
    {
        finish();
    }




}
