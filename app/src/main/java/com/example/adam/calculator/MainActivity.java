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

    public void addToEquation(View view)
    {
        int lengthOfEquation = equation.length()-1;
        TextView button = (TextView)view;
        String character = button.getText().toString();
        if(lengthOfEquation>14)
        {
            return;
        }
        if(checkIfSymbol(character) && lengthOfEquation >=0)
        {
            if(checkIfLastIsASymbol(lengthOfEquation)) {
                deleteLast(view);
                equation.append(character);
                editText();
                return;
            }
        }
        equation.append(character);
        editText();
    }

    public void calculateEquation(View view)
    {
        int lengthOfEquation = equation.length()-1;
        if(checkIfLastIsASymbol(lengthOfEquation)) {
            deleteLast(view);
            }

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
            return;
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

    public boolean checkIfSymbol(String newCharInEquation)
    {
        if(newCharInEquation.equals("+"))
            return true;
        else if(newCharInEquation.equals("-"))
            return true;
        else if(newCharInEquation.equals("*"))
            return true;
        else if(newCharInEquation.equals("/"))
            return true;
        else if(newCharInEquation.equals("."))
            return true;
        else return false;
    }
    public boolean checkIfLastIsASymbol(int lengthOfEquation)
    {
        if(equation.charAt(lengthOfEquation) == '-'
                || equation.charAt(lengthOfEquation) == '+'
                || equation.charAt(lengthOfEquation) == '/'
                || equation.charAt(lengthOfEquation) == '*'
                || equation.charAt(lengthOfEquation) == '.')
            return true;
        else return false;
    }
    public void editText()
    {
        TextView textView = findViewById(R.id.showEquation);
        textView.setText(equation.toString());
    }
}