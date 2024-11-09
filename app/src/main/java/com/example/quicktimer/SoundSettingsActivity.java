package com.example.quicktimer;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SoundSettingsActivity extends AppCompatActivity {

    private RadioGroup soundOptionsGroup;
    private Button previewButton, saveButton;
    private MediaPlayer mediaPlayer;
    private int selectedSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_settings);

        soundOptionsGroup = findViewById(R.id.soundOptionsGroup);
        previewButton = findViewById(R.id.previewButton);
        saveButton = findViewById(R.id.saveButton);

        SharedPreferences sharedPreferences = getSharedPreferences("QuickTimerPrefs", MODE_PRIVATE);
        selectedSound = sharedPreferences.getInt("selectedSound", R.raw.sound1);

        previewButton.setOnClickListener(v -> {
            int selectedId = getSelectedSoundId();
            if (selectedId != 0) {
                playSound(selectedId);
            } else {
                Toast.makeText(this, "Please select a sound to preview", Toast.LENGTH_SHORT).show();
            }
        });

        saveButton.setOnClickListener(v -> {
            selectedSound = getSelectedSoundId();
            if (selectedSound != 0) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("selectedSound", selectedSound);
                editor.apply();
                Toast.makeText(this, "Sound saved successfully!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Please select a sound to save", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private int getSelectedSoundId() {
        int checkedRadioButtonId = soundOptionsGroup.getCheckedRadioButtonId();
        if (checkedRadioButtonId == R.id.soundOption1) return R.raw.sound1;
        else if (checkedRadioButtonId == R.id.soundOption2) return R.raw.sound2;
        else if (checkedRadioButtonId == R.id.soundOption3) return R.raw.sound3;
        return 0;
    }

    private void playSound(int soundResId) {
        if (mediaPlayer != null) mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(this, soundResId);
        mediaPlayer.start();
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null) mediaPlayer.release();
        super.onDestroy();
    }
}
