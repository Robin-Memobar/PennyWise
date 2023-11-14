package com.example.pennywise;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pennywise.Budgetplan;
import com.example.pennywise.Help;
import com.example.pennywise.MainActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Analyse extends AppCompatActivity {
    private TextView householdamount;
    private TextView foodAmount;
    private TextView hobbyAmount;
    private TextView restAmount;
    private ListView listViewLastChanges;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.analyse);

        // Lade und zeige die Beträge an
        loadAndDisplayAmounts();


        final Button buttonHome = findViewById(R.id.navigation_home);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonHomeClicked(v);
            }
        });

        final Button buttonBudgetplan = findViewById(R.id.navigation_budgetplan);
        buttonBudgetplan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonBudgetplanActivity(v);
            }
        });

        final Button buttonAnalysis = findViewById(R.id.navigation_analysis);
        buttonAnalysis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonAnalyseActivity(v);
            }
        });

        final Button buttonHelp = findViewById(R.id.navigation_help);
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                buttonHelpActivity(v);
            }
        });



    }
    private void loadAndDisplayAmounts() {
        SharedPreferences sharedPreferences = getSharedPreferences("BudgetPrefs", MODE_PRIVATE);

        // Werte aus SharedPreferences abrufen
        int haushaltBetrag = sharedPreferences.getInt("AusgabenHaushalt", 0);
        int essenBetrag = sharedPreferences.getInt("AusgabenEssen", 0);
        int freizeitBetrag = sharedPreferences.getInt("AusgabenFreizeit", 0);
        int sonstigesBetrag = sharedPreferences.getInt("AusgabenSonstiges", 0);

        householdamount = findViewById(R.id.householdAmount);
        foodAmount = findViewById(R.id.foodAmount);
        hobbyAmount = findViewById(R.id.hobbyAmount);
        restAmount = findViewById(R.id.restAmount);

        // Werte in den TextViews anzeigen
        householdamount.setText(String.format("%d", haushaltBetrag));
        foodAmount.setText(String.format("%d", essenBetrag));
        hobbyAmount.setText(String.format("%d", freizeitBetrag));
        restAmount.setText(String.format("%d", sonstigesBetrag));
    }

    private void buttonHomeClicked(View view) {
        startButtonClickAnimation(view);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void buttonBudgetplanActivity(View view) {
        startButtonClickAnimation(view);
        Intent intent = new Intent(this, Budgetplan.class);
        startActivity(intent);
    }

    private void buttonAnalyseActivity(View view) {
        startButtonClickAnimation(view);
        Intent intent = new Intent(this, Analyse.class);
        startActivity(intent);
    }

    private void buttonHelpActivity(View view) {
        startButtonClickAnimation(view);
        Intent intent = new Intent(this, Help.class);
        startActivity(intent);
    }

    private void startButtonClickAnimation(View view) {
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(view, "scaleX", 0.9f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f);
        scaleDownX.setDuration(300);
        scaleDownY.setDuration(300);

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.play(scaleDownX).with(scaleDownY);

        scaleDown.start();
    }
}
