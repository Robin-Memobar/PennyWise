package com.example.pennywise;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
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

        householdamount = findViewById(R.id.householdAmount);
        foodAmount = findViewById(R.id.foodAmount);
        hobbyAmount = findViewById(R.id.hobbyAmount);
        restAmount = findViewById(R.id.restAmount);

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
