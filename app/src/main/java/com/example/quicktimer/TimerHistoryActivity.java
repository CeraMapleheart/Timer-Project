package com.example.quicktimer;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class TimerHistoryActivity extends AppCompatActivity {

    private ListView historyListView;
    private TimerDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_history);

        historyListView = findViewById(R.id.historyListView);
        dbHelper = new TimerDatabaseHelper(this);

        ArrayList<String> historyList = loadHistory();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, historyList);
        historyListView.setAdapter(adapter);
    }

    private ArrayList<String> loadHistory() {
        ArrayList<String> history = new ArrayList<>();
        Cursor cursor = dbHelper.getAllHistory();
        while (cursor.moveToNext()) {
            String duration = cursor.getString(1); // duration column
            String endTime = cursor.getString(2); // end time column
            history.add("Duration: " + duration + " | Ended at: " + endTime);
        }
        cursor.close();
        return history;
    }
}
