package com.example.pennywise;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class Budgetplan extends AppCompatActivity {

    private TextView textViewCurrentBudget;
    private ListView listViewLastChanges;
    private BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgetplan);

        final Button buttonHome = (Button) findViewById(R.id.navigation_home);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonHomeClicked();
            }
        });

        final Button buttonBudgetplan = (Button) findViewById(R.id.navigation_budgetplan);
        buttonBudgetplan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonBudgetplanActivity();
            }
        });

        final Button buttonAnalysis = (Button) findViewById(R.id.navigation_analysis);
        buttonAnalysis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonAnalyseActivity();
            }
        });

        final Button buttonHelp = (Button) findViewById(R.id.navigation_help);
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonHelpActivity();
            }
        });
    }

    private void buttonHomeClicked() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void buttonBudgetplanActivity() {
        Intent intent = new Intent(this, Budgetplan.class);
        startActivity(intent);
    }

    private void buttonAnalyseActivity() {
        Intent intent = new Intent(this, Analyse.class);
        startActivity(intent);
    }

    private void buttonHelpActivity() {
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }
}