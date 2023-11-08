package com.example.pennywise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private ArrayList<String> expensesList;
    private ArrayAdapter<String> expensesAdapter;
    private EditText expenseInput;
    private ListView expensesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expensesList = new ArrayList<>();
        expensesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, expensesList);
        expenseInput = findViewById(R.id.expenseInput);
        expensesListView = findViewById(R.id.expensesList);

        expensesListView.setAdapter(expensesAdapter);
    }

    public void addExpense(View view) {
        String expense = expenseInput.getText().toString();
        if (!expense.isEmpty()) {
            expensesList.add(expense);
            expensesAdapter.notifyDataSetChanged();
            expenseInput.setText("");
        }
    }
}
