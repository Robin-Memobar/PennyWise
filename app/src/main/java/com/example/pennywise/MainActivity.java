package com.example.pennywise;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCurrentBudget;
    private ListView listViewLastChanges;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listViewLastChanges = findViewById(R.id.listViewLastChanges);
    textViewCurrentBudget = findViewById(R.id.textViewCurrentBudget);
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
        updateCurrentBudget();
        updateLastChanges();
    }

    private void updateCurrentBudget() {
        SharedPreferences sharedPreferences = getSharedPreferences("BudgetPrefs", MODE_PRIVATE);
        int budgetLimit = sharedPreferences.getInt("BudgetLimit", 0);
        int totalExpenses = sharedPreferences.getInt("AusgabenEssen", 0) +
                sharedPreferences.getInt("AusgabenHaushalt", 0) +
                sharedPreferences.getInt("AusgabenFreizeit", 0) +
                sharedPreferences.getInt("AusgabenSonstiges", 0);

        int currentBudget = budgetLimit - totalExpenses;
        textViewCurrentBudget.setText(String.format("Aktuelles Budget: %d", currentBudget));
    }

    private void updateLastChanges() {
        SharedPreferences sharedPreferences = getSharedPreferences("BudgetPrefs", MODE_PRIVATE);
        List<Ausgabe> ausgabenListe = new ArrayList<>();
        ausgabenListe.add(new Ausgabe(sharedPreferences.getInt("AusgabenEssen", 0), "Essen"));
        ausgabenListe.add(new Ausgabe(sharedPreferences.getInt("AusgabenHaushalt", 0), "Haushalt"));
        ausgabenListe.add(new Ausgabe(sharedPreferences.getInt("AusgabenFreizeit", 0), "Freizeit"));
        ausgabenListe.add(new Ausgabe(sharedPreferences.getInt("AusgabenSonstiges", 0), "Sonstiges"));

        // Sortieren der Liste in absteigender Reihenfolge nach Betrag
        Collections.sort(ausgabenListe, (a1, a2) -> Integer.compare(a2.betrag, a1.betrag));

        // Extrahieren der drei höchsten Ausgaben
        List<Ausgabe> topDreiAusgaben = ausgabenListe.subList(0, Math.min(3, ausgabenListe.size()));

        // Konvertieren der Werte in Strings für die Anzeige
        List<String> anzeigeListe = new ArrayList<>();
        for (Ausgabe ausgabe : topDreiAusgaben) {
            anzeigeListe.add(ausgabe.toString());
        }

        // Anzeigen der Daten in der ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, anzeigeListe);
        listViewLastChanges.setAdapter(adapter);
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

    private void updateCurrentBudget(double budget) {
        String budgetText = String.format("Aktuelles Budget: %.2f", budget);
        textViewCurrentBudget.setText(budgetText);
    }

    private String[] getLastChanges() {

        return new String[] {
                "Stromrechnung - 300",
                "Budgeterhöhung - 1000",
                "Gym-Abonnement - 54"
        };
    }
}
