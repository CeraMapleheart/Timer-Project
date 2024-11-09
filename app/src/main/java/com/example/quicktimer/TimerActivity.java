package com.example.quicktimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TimerActivity extends AppCompatActivity {

    private TextView timerDisplay;
    private EditText inputHours, inputMinutes, inputSeconds;
    private Button startButton, pauseButton, resetButton;
    private CountDownTimer countDownTimer;
    private long timeInMillis;
    private boolean isRunning = false;
    private long timeRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerDisplay = findViewById(R.id.timerDisplay);
        inputHours = findViewById(R.id.inputHours);
        inputMinutes = findViewById(R.id.inputMinutes);
        inputSeconds = findViewById(R.id.inputSeconds);
        startButton = findViewById(R.id.startButton);
        pauseButton = findViewById(R.id.pauseButton);
        resetButton = findViewById(R.id.resetButton);

        startButton.setOnClickListener(v -> startTimer());
        pauseButton.setOnClickListener(v -> pauseTimer());
        resetButton.setOnClickListener(v -> resetTimer());
    }

    private void startTimer() {
        if (!isRunning) {
            int hours = Integer.parseInt(inputHours.getText().toString().isEmpty() ? "0" : inputHours.getText().toString());
            int minutes = Integer.parseInt(inputMinutes.getText().toString().isEmpty() ? "0" : inputMinutes.getText().toString());
            int seconds = Integer.parseInt(inputSeconds.getText().toString().isEmpty() ? "0" : inputSeconds.getText().toString());

            timeInMillis = (hours * 3600 + minutes * 60 + seconds) * 1000;

            countDownTimer = new CountDownTimer(timeInMillis, 1000) {
                public void onTick(long millisUntilFinished) {
                    timeRemaining = millisUntilFinished;
                    int hrs = (int) (millisUntilFinished / 3600000);
                    int mins = (int) (millisUntilFinished % 3600000) / 60000;
                    int secs = (int) ((millisUntilFinished % 60000) / 1000);
                    timerDisplay.setText(String.format("%02d:%02d:%02d", hrs, mins, secs));
                }

                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(TimerActivity.this, R.raw.notification_sound);
                    mediaPlayer.start();
                    Toast.makeText(TimerActivity.this, "Time's up!", Toast.LENGTH_SHORT).show();
                }
            }.start();
            isRunning = true;
        }
    }

    private void pauseTimer() {
        if (isRunning) {
            countDownTimer.cancel();
            isRunning = false;
        }
    }

    private void resetTimer() {
        if (isRunning) {
            countDownTimer.cancel();
            isRunning = false;
        }
        timerDisplay.setText("00:00:00");
        inputHours.setText("");
        inputMinutes.setText("");
        inputSeconds.setText("");
    }
}
