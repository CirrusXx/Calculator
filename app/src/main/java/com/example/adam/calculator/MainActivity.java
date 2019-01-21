package com.example.adam.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.mariuszgromada.math.mxparser.Expression;

public class MainActivity extends AppCompatActivity {


    private StringBuilder equation = new StringBuilder();
    private DataBaseCalculator dataBase = new DataBaseCalculator(this);
    private String[] symbolsArray = {"+", "-", "/", ".", "*"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void addToEquationOnClick(View view) {
        if (getLastIndexOfEquation() > 14) {
            return;
        }

        if (getLengthOfTheEquation() == 0 && checkIfAddedIsSymbol(getCharacter(view))) {
            switch (getCharacter(view)) {
                case "-":
                    addToEquation(getCharacter(view));
                    editTextOfEquation();
                    return;
                case ".":
                    equation.append("0");
                    addToEquation(getCharacter(view));
                    editTextOfEquation();
                    return;
                default:
                    return;
            }
        }

        if (checkIfAddedIsSymbol(getCharacter(view)) && getLastIndexOfEquation() >= 0) {
            if (checkIfLastInAnEquationIsASymbol(getLastIndexOfEquation())) {
                deleteLastOnClick(view);
                addToEquation(getCharacter(view));
                editTextOfEquation();
                return;
            }
        }
        addToEquation(getCharacter(view));
        editTextOfEquation();
    }

    public void CalculateEquationOnClick(View view) {
        if (getLengthOfTheEquation() == 0) {
            return;
        }

        if (checkIfLastInAnEquationIsASymbol(getLastIndexOfEquation())) {
            deleteLastOnClick(view);
        }

        showResultOfEquation(resultOfEquation());
        addToDataBase(equation.toString() + "=" + resultOfEquation());
        deleteEverythingInEquation();
    }

    public void deleteLastOnClick(View view) {
        if (equation.length() == 0)
            return;
        equation.deleteCharAt(getLastIndexOfEquation());
        editTextOfEquation();
    }

    public void openHistoryOnClick(View view) {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    public void deleteAllAndEditTextOnClick(View view) {
        equation.delete(0, equation.length());
        editTextOfEquation();
    }

    private void addToDataBase(String result) {
        dataBase.insertData(result);
    }

    private boolean checkIfAddedIsSymbol(String character) {
        for (String aSymbolsArray : symbolsArray) {
            if (aSymbolsArray.equals(character)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkIfLastInAnEquationIsASymbol(int lengthOfEquation) {
        for (String aSymbolsArray : symbolsArray) {
            if (aSymbolsArray.equals(String.valueOf(equation.charAt(lengthOfEquation)))) {
                return true;
            }
        }
        return false;
    }

    private int getLastIndexOfEquation() {
        return equation.length() - 1;
    }

    private int getLengthOfTheEquation() {
        return equation.length();
    }

    private void showResultOfEquation(double result) {
        TextView textView = findViewById(R.id.showEquation);
        textView.setText(String.valueOf(result));
    }

    private double resultOfEquation() {
        Expression expression = new Expression(equation.toString());
        return expression.calculate();
    }

    private void deleteEverythingInEquation() {
        equation.delete(0, equation.length());
    }

    private void editTextOfEquation() {
        TextView textView = findViewById(R.id.showEquation);
        textView.setText(equation.toString());
    }

    private String getCharacter(View view) {
        TextView button = (TextView) view;
        return button.getText().toString();
    }

    private void addToEquation(String character) {
        equation.append(character);
    }
}