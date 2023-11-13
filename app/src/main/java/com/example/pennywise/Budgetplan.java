package com.example.pennywise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class Budgetplan extends AppCompatActivity {

    private TextView textViewCurrentBudget;
    private ListView listViewLastChanges;
    private BottomNavigationView bottomNavigationView;

    private EditText editTextBudgetLimit, editTextAusgabenEssen, editTextAusgabenHaushalt, editTextAusgabenFreizeit, editTextAusgabenSonstiges;
    private Button saveBudgetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgetplan);

        // Ihre bestehenden Button-Initialisierungen
        final Button buttonHome = findViewById(R.id.navigation_home);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonHomeClicked();
            }
        });

        final Button buttonBudgetplan = findViewById(R.id.navigation_budgetplan);
        buttonBudgetplan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonBudgetplanActivity();
            }
        });

        final Button buttonAnalysis = findViewById(R.id.navigation_analysis);
        buttonAnalysis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonAnalyseActivity();
            }
        });

        final Button buttonHelp = findViewById(R.id.navigation_help);
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonHelpActivity();
            }
        });

        // Initialisierung der neuen EditText-Felder und des Save Buttons
        editTextBudgetLimit = findViewById(R.id.editTextBudgetLimit);
        editTextAusgabenEssen = findViewById(R.id.editTextAusgabenEssen);
        editTextAusgabenHaushalt = findViewById(R.id.editTextAusgabenHaushalt);
        editTextAusgabenFreizeit = findViewById(R.id.editTextAusgabenFreizeit);
        editTextAusgabenSonstiges = findViewById(R.id.editTextAusgabenSonstiges);
        saveBudgetButton = findViewById(R.id.saveBudgetButton);

        saveBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBudgetData();
            }
        });

        // Laden der gespeicherten Daten beim Start
        loadBudgetData();
    }

    private void saveBudgetData() {
        SharedPreferences sharedPreferences = getSharedPreferences("BudgetPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("BudgetLimit", Integer.parseInt(editTextBudgetLimit.getText().toString()));
        editor.putInt("AusgabenEssen", Integer.parseInt(editTextAusgabenEssen.getText().toString()));
        editor.putInt("AusgabenHaushalt", Integer.parseInt(editTextAusgabenHaushalt.getText().toString()));
        editor.putInt("AusgabenFreizeit", Integer.parseInt(editTextAusgabenFreizeit.getText().toString()));
        editor.putInt("AusgabenSonstiges", Integer.parseInt(editTextAusgabenSonstiges.getText().toString()));

        editor.apply();
    }

    private void loadBudgetData() {
        SharedPreferences sharedPreferences = getSharedPreferences("BudgetPrefs", MODE_PRIVATE);

        editTextBudgetLimit.setText(String.valueOf(sharedPreferences.getInt("BudgetLimit", 0)));
        editTextAusgabenEssen.setText(String.valueOf(sharedPreferences.getInt("AusgabenEssen", 0)));
        editTextAusgabenHaushalt.setText(String.valueOf(sharedPreferences.getInt("AusgabenHaushalt", 0)));
        editTextAusgabenFreizeit.setText(String.valueOf(sharedPreferences.getInt("AusgabenFreizeit", 0)));
        editTextAusgabenSonstiges.setText(String.valueOf(sharedPreferences.getInt("AusgabenSonstiges", 0)));
    }

    // Ihre bestehenden Navigationsmethoden
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