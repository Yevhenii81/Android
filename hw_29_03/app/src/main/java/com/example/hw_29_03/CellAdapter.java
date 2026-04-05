package com.example.hw_29_03;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

public class CellAdapter extends RecyclerView.Adapter<CellAdapter.ViewHolder> {

    int[][] board;
    boolean isXTurn = true;
    int SIZE = 100;

    public CellAdapter(int[][] board) {
        this.board = board;
    }

    @Override
    public int getItemCount() {
        return SIZE * SIZE;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int row = position / SIZE;
        int col = position % SIZE;

        holder.cell.setText(getSymbol(board[row][col]));

        holder.cell.setOnClickListener(v -> {
            if (board[row][col] != 0) return;

            board[row][col] = isXTurn ? 1 : 2;
            notifyItemChanged(position);

            if (checkWin(row, col)) {
                Toast.makeText(v.getContext(), "WIN!", Toast.LENGTH_SHORT).show();
            }

            isXTurn = !isXTurn;
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView cell;

        public ViewHolder(View itemView) {
            super(itemView);
            cell = itemView.findViewById(R.id.cell);
        }
    }

    private String getSymbol(int value) {
        if (value == 1) return "X";
        if (value == 2) return "O";
        return "";
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