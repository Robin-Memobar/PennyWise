package com.example.pennywise; // Ändern Sie dies entsprechend dem Paketnamen Ihrer App

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pennywise.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private TextView textViewCurrentBudget;
    private ListView listViewLastChanges;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TextView für das aktuelle Budget initialisieren
        textViewCurrentBudget = findViewById(R.id.textViewCurrentBudget);
        // ListView für die letzten Änderungen initialisieren
        listViewLastChanges = findViewById(R.id.listViewLastChanges);
        // BottomNavigationView initialisieren
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Setzen Sie hier den aktuellen Budgetbetrag aus Ihrer Datenquelle
        updateCurrentBudget(9123.00); // Beispielwert

        // Ein ArrayAdapter, um die letzten Änderungen anzuzeigen
        String[] lastChanges = getLastChanges();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lastChanges);
        listViewLastChanges.setAdapter(adapter);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        // Listener für die BottomNavigationView hinzufügen
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.navigation_home) {
                // Hier Code einfügen, um das Home-Fragment oder die Home-Activity anzuzeigen
            } else if (id == R.id.navigation_budget) {
                // Hier Code einfügen, um das Budgetplan-Fragment oder die Budgetplan-Activity anzuzeigen
            } else if (id == R.id.navigation_analysis) {
                // Hier Code einfügen, um das Analyse-Fragment oder die Analyse-Activity anzuzeigen
            } else if (id == R.id.navigation_help) {
                // Hier Code einfügen, um das Hilfe-Fragment oder die Hilfe-Activity anzuzeigen
            }

            return true; // true gibt an, dass die Item-Auswahl behandelt wurde
        });
    }

    private void updateCurrentBudget(double budget) {
        // Formatieren Sie den Betrag und setzen Sie den Text
        String budgetText = String.format("Aktuelles Budget: %.2f", budget);
        textViewCurrentBudget.setText(budgetText);
    }

    private String[] getLastChanges() {
        // Dies würde die letzten Änderungen von Ihrer Datenquelle holen
        // Hier ist ein Platzhalter-Beispiel
        return new String[] {
                "Stromrechnung - 300",
                "Budgeterhöhung - 1000",
                "Gym-Abonnement - 54"
        };
    }
}
