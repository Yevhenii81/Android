package com.example.hw_10_04;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStart;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);

        btnStart.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, WallpaperService.class);
                startService(intent);
                Toast.makeText(MainActivity.this, "Service started", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Start error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

        btnStop.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, WallpaperService.class);
            stopService(intent);
            Toast.makeText(MainActivity.this, "Service stopped", Toast.LENGTH_SHORT).show();
        });
    }
}
