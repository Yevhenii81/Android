package com.example.hw_10_04;

import android.app.Service;
import android.app.WallpaperManager;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class WallpaperService extends Service {

    private static final String TAG = "WallpaperService";

    private Handler handler;
    private Runnable wallpaperRunnable;
    private boolean isRunning = false;

    private final int[] wallpapers = {
            R.drawable.wall1,
            R.drawable.wall2,
            R.drawable.wall3
    };

    private int currentIndex = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");

        handler = new Handler(Looper.getMainLooper());

        wallpaperRunnable = new Runnable() {
            @Override
            public void run() {
                changeWallpaper();
                handler.postDelayed(this, 10000);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        if (!isRunning) {
            isRunning = true;
            changeWallpaper();
            handler.postDelayed(wallpaperRunnable, 10000);
        }

        return START_STICKY;
    }

    private void changeWallpaper() {
        try {
            WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            wallpaperManager.setResource(wallpapers[currentIndex]);

            Log.d(TAG, "Wallpaper changed to index: " + currentIndex);
            Toast.makeText(this, "Wallpaper changed: " + currentIndex, Toast.LENGTH_SHORT).show();

            currentIndex++;
            if (currentIndex >= wallpapers.length) {
                currentIndex = 0;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error changing wallpaper", e);
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        isRunning = false;

        if (handler != null && wallpaperRunnable != null) {
            handler.removeCallbacks(wallpaperRunnable);
        }

        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
