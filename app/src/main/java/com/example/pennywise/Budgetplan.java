package com.example.pennywise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.mlkit.vision.common.InputImage;
import com.google.mlkit.vision.text.TextRecognition;
import com.google.mlkit.vision.text.TextRecognizer;
import com.google.mlkit.vision.text.latin.TextRecognizerOptions;

import android.os.Bundle;
import android.Manifest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

public class Budgetplan extends AppCompatActivity {

    private TextView textViewCurrentBudget;
    private ListView listViewLastChanges;
    private BottomNavigationView bottomNavigationView;

    private EditText editTextBudgetLimit;
    private Button saveBudgetButton;

    private ImageView imageViewCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budgetplan);


        imageViewCamera = findViewById(R.id.imageView);
        imageViewCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Camera", "request");
                requestCameraPermission();
            }
        });


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

        // Initialisierung der EditText-Felder und des Save Buttons
        editTextBudgetLimit = findViewById(R.id.editTextBudgetLimit);
        saveBudgetButton = findViewById(R.id.saveBudgetButton);
        saveBudgetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBudgetData();
            }
        });

        // Spinner initialisieren
        Spinner artSpinner = findViewById(R.id.artSpinner);
        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        initializeSpinners(artSpinner, categorySpinner);

        // Laden der gespeicherten Daten beim Start
        loadBudgetData();
    }

    private void initializeSpinners(Spinner artSpinner, Spinner categorySpinner) {
        // Auswahlmöglichkeiten und Adapter für artSpinner
        String[] categories = {"Wähle eine Kategorie", "Haushalt", "Freizeit", "Essen", "Sonstiges"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        artSpinner.setAdapter(adapter);

        // Auswahlmöglichkeiten und Adapter für categorySpinner
        String[] arten = {"Wähle eine Art", "Budget Erhöhung", "Ausgabe"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arten);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter1);
    }

    private void requestCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            Log.d("Camera", "check");
            dispatchTakePictureIntent();
        }
    }

    private static final int REQUEST_CAMERA_PERMISSION = 101;
    private static final int REQUEST_IMAGE_CAPTURE = 102;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                dispatchTakePictureIntent();
            } else {
                // Berechtigung wurde abgelehnt, Benutzer informieren
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            // Verwenden Sie das Bild hier
        }
    }



    private void saveBudgetData() {
        SharedPreferences sharedPreferences = getSharedPreferences("BudgetPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("BudgetLimit", Integer.parseInt(editTextBudgetLimit.getText().toString()));
        editor.apply();
    }

    private void loadBudgetData() {
        SharedPreferences sharedPreferences = getSharedPreferences("BudgetPrefs", MODE_PRIVATE);
        editTextBudgetLimit.setText(String.valueOf(sharedPreferences.getInt("BudgetLimit", 0)));
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
