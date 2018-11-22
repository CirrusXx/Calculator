package com.example.adam.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    StringBuilder equation = new StringBuilder();
    ArrayList<String> history = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        }

    public void add(View view)
    {
        if(equation.length()>14)
        {
            return;
        }

        TextView button = (TextView)view;
        String character = button.getText().toString();
        equation.append(character);

        editText();
    }

    public void calculate(View view)
    {
        Expression expression = new Expression(equation.toString());

        double result = expression.calculate();

        TextView textView = findViewById(R.id.showEquation);
        textView.setText(String.valueOf(result));

        history.add(equation.toString()+"="+result);

        equation.delete(0,equation.length());
    }

    public void deleteLast(View view)
    {
        if(equation.length()==0)
        {
            return;
        }
        int index = equation.length()-1;
        equation.deleteCharAt(index);
        editText();
    }
    public void deleteAll(View view)
    {
        equation.delete(0,equation.length());
        editText();
    }

    public void openHistory(View view)
    {
        Intent intent = new Intent(this,History.class);

        intent.putExtra("history",history);

        startActivity(intent);

    }

    public void editText()
    {
        TextView textView = findViewById(R.id.showEquation);
        textView.setText(equation.toString());
    }


}