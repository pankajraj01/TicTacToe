package com.pankaj.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private Boolean player1turn = true;

    private int count;

    private int player1points;
    private int player2points;

    private TextView tv_p1;
    private TextView tv_p2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_p1 = findViewById(R.id.tv_p1);
        tv_p2 = findViewById(R.id.tv_p2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String btn_id = "btn_" + i + j;
                int res_ID = getResources().getIdentifier(btn_id, "id", getPackageName());
                buttons[i][j] = findViewById(res_ID);
                buttons[i][j].setOnClickListener(this);
            }
        }

        Button reset = findViewById(R.id.btn_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });

    }

    @Override
    public void onClick(View view) {

        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1turn) {
            ((Button) view).setText("X");
        } else {
            ((Button) view).setText("O");
        }
        count++;

        if (ckeckforWin()) {
            if (player1turn) {
                player1wins();
            } else {
                player2wins();
            }
        } else if (count == 9) {
            draw();
        } else {
            player1turn = !player1turn;
        }
    }

    private boolean ckeckforWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void player1wins() {

        player1points++;
        Toast.makeText(this, "Player 1 Wins", Toast.LENGTH_LONG).show();
        updatepointsText();
        resetBoard();
    }

    private void player2wins() {
        player2points++;
        Toast.makeText(this, "Player 2 Wins", Toast.LENGTH_LONG).show();
        updatepointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatepointsText() {
        tv_p1.setText("Player 1(X): " + player1points);
        tv_p2.setText("Player 2(O): " + player2points);
    }

    private void resetBoard() {

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }

        count = 0;
        player1turn = true;
    }

    private void resetGame() {
        player1points = 0;
        player2points = 0;
        updatepointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count", count);
        outState.putInt("player1points", player1points);
        outState.putInt("player2points", player2points);
        outState.getBoolean("player1turn", player1turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        count = savedInstanceState.getInt("count");
        player1points = savedInstanceState.getInt("player1points");
        player2points = savedInstanceState.getInt("player2points");
        player1turn = savedInstanceState.getBoolean("player1turn");
    }
}