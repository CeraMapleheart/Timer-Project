package com.example.quicktimer1;

import static com.quicktimer.TimerDatabaseHelper.*;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class TimerHistoryActivity extends AppCompatActivity {

    private ListView historyListView;
    private com.quicktimer.TimerDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_history);

        historyListView = findViewById(R.id.historyListView);
        dbHelper = new com.quicktimer.TimerDatabaseHelper(this);

        // Retrieve timer history from SQLite database
        Cursor cursor = dbHelper.getAllTimers();

        // Define the columns to display
        String[] from = {COLUMN_TIME, COLUMN_DATE};
        int[] to = {R.id.timeTextView, R.id.dateTextView};

        // Set up the adapter
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,
                R.layout.timer_history_item, cursor, from, to, 0);

        historyListView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
