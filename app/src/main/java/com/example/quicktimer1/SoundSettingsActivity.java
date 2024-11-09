package com.example.quicktimer1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

public class SoundSettingsActivity extends AppCompatActivity {

    private ListView soundListView;
    private Button saveButton;
    private String selectedSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_settings);

        soundListView = findViewById(R.id.soundListView);
        saveButton = findViewById(R.id.saveButton);

        // Populate sound list (You can add more sounds here)
        String[] soundOptions = {"Default", "Sound 1", "Sound 2"};

        // Custom adapter for ListView (you need to create this adapter or use a simple ArrayAdapter)
        soundListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, soundOptions));

        // Load selected sound from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("TimerSettings", MODE_PRIVATE);
        selectedSound = prefs.getString("selected_sound", "Default");

        // Set the list view to highlight the saved sound option
        // Find position of saved sound and set it as selected (simplified version here)
        int selectedPosition = Arrays.asList(soundOptions).indexOf(selectedSound);
        soundListView.setSelection(selectedPosition);

        soundListView.setOnItemClickListener((parent, view, position, id) -> {
            selectedSound = soundOptions[position];
        });

        saveButton.setOnClickListener(v -> {
            // Save the selected sound to SharedPreferences
            SharedPreferences.Editor editor = getSharedPreferences("TimerSettings", MODE_PRIVATE).edit();
            editor.putString("selected_sound", selectedSound);
            editor.apply();

            Toast.makeText(SoundSettingsActivity.this, "Sound saved", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
