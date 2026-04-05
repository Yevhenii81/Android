package com.example.hw_29_03;

import android.os.Bundle;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int SIZE = 100;
    int[][] board = new int[SIZE][SIZE];
    boolean isXTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout grid = findViewById(R.id.grid);

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                Button btn = new Button(this);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.width = 120;
                params.height = 120;
                btn.setLayoutParams(params);

                int r = row;
                int c = col;

                btn.setOnClickListener(v -> {
                    if (board[r][c] != 0) return;

                    board[r][c] = isXTurn ? 1 : 2;
                    btn.setText(isXTurn ? "X" : "O");

                    if (checkWin(r, c)) {
                        Toast.makeText(this, "WIN!", Toast.LENGTH_SHORT).show();
                    }

                    isXTurn = !isXTurn;
                });

                grid.addView(btn);
            }
        }
    }

    private boolean checkWin(int row, int col) {
        int player = board[row][col];

        return count(row, col, 1, 0, player) + count(row, col, -1, 0, player) >= 4 ||
                count(row, col, 0, 1, player) + count(row, col, 0, -1, player) >= 4 ||
                count(row, col, 1, 1, player) + count(row, col, -1, -1, player) >= 4 ||
                count(row, col, 1, -1, player) + count(row, col, -1, 1, player) >= 4;
    }

    private int count(int row, int col, int dx, int dy, int player) {
        int count = 0;

        for (int i = 1; i < 5; i++) {
            int r = row + dx * i;
            int c = col + dy * i;

            if (r < 0 || r >= SIZE || c < 0 || c >= SIZE) break;
            if (board[r][c] != player) break;

            count++;
        }
        return count;
    }
}