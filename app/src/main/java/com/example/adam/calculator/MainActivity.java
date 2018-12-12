package com.example.adam.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {


    StringBuilder equation = new StringBuilder();
    DataBaseManager dataBase = new DataBaseManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addToEquationOnClick(View view) {
        if (getSizeOfEquation() > 14) {
            return;
        }

        if (getSizeOfEquation() == -1 && checkIfSymbol(getCharacter(view))) {
            if (getCharacter(view).equals("-")) {
                addToEquation(getCharacter(view));
                editText();
                return;
            } else if (getCharacter(view).equals(".")) {
                equation.append("0");
                addToEquation(getCharacter(view));
                editText();
                return;
            } else return;
        }

        if (checkIfSymbol(getCharacter(view)) && getSizeOfEquation() >= 0) {
            if (checkIfLastIsASymbol(getSizeOfEquation())) {
                deleteLastOnClick(view);
                addToEquation(getCharacter(view));
                editText();
                return;
            }
        }
        addToEquation(getCharacter(view));
        editText();
    }

    public void CalculateEquationOnClick(View view) {
        if (equation.length() == 0) {
            return;
        }

        if (checkIfLastIsASymbol(getSizeOfEquation())) {
            deleteLastOnClick(view);
        }

        showResultOfEquation(resultOfEquation());
        addToDataBase(equation.toString() + "=" + resultOfEquation());
        deleteEverythingInEquation();
    }

    public void deleteAllAndEditTextOnClick(View view) {
        equation.delete(0, equation.length());
        editText();
    }

    public void deleteLastOnClick(View view) {
        if (equation.length() == 0)
            return;
        equation.deleteCharAt(getSizeOfEquation());
        editText();
    }

    public void openHistoryOnClick(View view) {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    public void addToDataBase(String result) {
        dataBase.insertData(result);
    }

    public boolean checkIfSymbol(String character) {
        return (character.equals("+")
                || character.equals("*")
                || character.equals("-")
                || character.equals("/")
                || character.equals("."));
    }

    public boolean checkIfLastIsASymbol(int lengthOfEquation) {
        return (equation.charAt(lengthOfEquation) == '-'
                || equation.charAt(lengthOfEquation) == '+'
                || equation.charAt(lengthOfEquation) == '/'
                || equation.charAt(lengthOfEquation) == '*'
                || equation.charAt(lengthOfEquation) == '.');
    }

    public int getSizeOfEquation() {
        return equation.length() - 1;
    }

    public void showResultOfEquation(double result) {
        TextView textView = findViewById(R.id.showEquation);
        textView.setText(String.valueOf(result));
    }

    public double resultOfEquation() {
        Expression expression = new Expression(equation.toString());
        return expression.calculate();
    }

    public void deleteEverythingInEquation() {
        equation.delete(0, equation.length());
    }

    public void editText() {
        TextView textView = findViewById(R.id.showEquation);
        textView.setText(equation.toString());
    }

    private String getCharacter(View view) {
        TextView button = (TextView) view;
        return button.getText().toString();
    }

    public void addToEquation(String character) {
        equation.append(character);
    }
}