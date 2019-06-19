
package com.pankaj.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.*;

public class WithComputerActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private Boolean player1turn = true;
    private Boolean computerturn = false;

    private int count = 0;
    public int counter = 0;

    private int player1points;
    private int computerpoints;

    private TextView tv_p1;
    private TextView tv_pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_computer);

        tv_p1 = findViewById(R.id.tv_p1);
        tv_pc = findViewById(R.id.tv_pc);

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


        if (true) {
            ((Button) view).setText("X");
            computerturn();
        }


        count++;

        if (ckeckforWin()) {

            if (player1turn) {
                player1wins();
            } else {
                computerwins();

            }
        } else if (count >= 9) {
            draw();
        } else {

            player1turn = !player1turn;
//            computerturn = !computerturn;

//            if (player1turn) {
//                player1turn = !player1turn;
//                computerturn = !computerturn;
//            } else {
//                computerturn = !computerturn;
//                player1turn = !player1turn;
//            }
        }
    }

    private void computerturn() {

        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }


        if (field[0][0].equals("X") && field[0][1].equals("X") && field[0][2].equals("")) {
            buttons[0][2].setText("O");
            count++;
        } else if (field[0][0].equals("X") && field[0][2].equals("X") && field[0][1].equals("")) {
            buttons[0][1].setText("O");
            count++;
        } else if (field[0][0].equals("X") && field[1][0].equals("X") && field[2][0].equals("")) {
            buttons[2][0].setText("O");
            count++;
        } else if (field[0][0].equals("X") && field[2][0].equals("X") && field[1][0].equals("")) {
            buttons[1][0].setText("O");
            count++;
        } else if (field[0][0].equals("X") && field[1][1].equals("X") && field[2][2].equals("")) {
            buttons[2][2].setText("O");
            count++;
        } else if (field[0][0].equals("X") && field[2][2].equals("X") && field[1][1].equals("")) {
            buttons[1][1].setText("O");
            count++;
        } else if (field[0][1].equals("X") && field[0][2].equals("X") && field[0][0].equals("")) {
            buttons[0][0].setText("O");
            count++;
        } else if (field[0][1].equals("X") && field[1][1].equals("X") && field[2][1].equals("")) {
            buttons[2][1].setText("O");
            count++;
        } else if (field[0][1].equals("X") && field[2][1].equals("X") && field[1][1].equals("")) {
            buttons[1][1].setText("O");
            count++;
        } else if (field[0][2].equals("X") && field[1][1].equals("X") && field[2][0].equals("")) {
            buttons[2][0].setText("O");
            count++;
        } else if (field[0][2].equals("X") && field[2][0].equals("X") && field[1][1].equals("")) {
            buttons[1][1].setText("O");
            count++;
        } else if (field[0][2].equals("X") && field[1][2].equals("X") && field[2][2].equals("")) {
            buttons[2][2].setText("O");
            count++;
        } else if (field[0][2].equals("X") && field[2][2].equals("X") && field[1][2].equals("")) {
            buttons[1][2].setText("O");
            count++;
        }

        //Second Row

        else if (field[1][0].equals("X") && field[2][0].equals("X") && field[0][0].equals("")) {
            buttons[0][0].setText("O");
            count++;
        } else if (field[1][0].equals("X") && field[1][1].equals("X") && field[1][2].equals("")) {
            buttons[1][2].setText("O");
            count++;
        } else if (field[1][0].equals("X") && field[1][2].equals("X") && field[1][1].equals("")) {
            buttons[1][1].setText("O");
            count++;
        } else if (field[1][1].equals("X") && field[2][1].equals("X") && field[0][1].equals("")) {
            buttons[0][1].setText("O");
            count++;
        } else if (field[1][1].equals("X") && field[1][2].equals("X") && field[1][0].equals("")) {
            buttons[1][0].setText("O");
            count++;
        } else if (field[1][2].equals("X") && field[2][2].equals("X") && field[0][2].equals("")) {
            buttons[0][2].setText("O");
            count++;
        }

        //Thrid Row

        else if (field[2][0].equals("X") && field[1][1].equals("X") && field[0][2].equals("")) {
            buttons[0][2].setText("O");
            count++;
        } else if (field[2][0].equals("X") && field[2][1].equals("X") && field[2][2].equals("")) {
            buttons[2][2].setText("O");
            count++;
        } else if (field[2][0].equals("X") && field[2][2].equals("X") && field[2][1].equals("")) {
            buttons[2][1].setText("O");
            count++;
        } else if (field[2][1].equals("X") && field[2][2].equals("X") && field[2][0].equals("")) {
            buttons[2][0].setText("O");
            count++;
        } else PCplayaChance();

    }

    private void PCplayaChance() {


        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {


                if (field[i][j].equals("")) {
                    buttons[i][j].setText("O");
                    count++;
                    return;
                }
            }
        }
        count++;
        return;


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
                if (field[i][0].equals("X")) {
                    player1turn = true;
                } else player1turn = false;
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                if (field[0][i].equals("X")) {
                    player1turn = true;
                } else player1turn = false;
                return true;
            }
        }

        if (field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            if (field[0][0].equals("X")) {
                player1turn = true;
            } else player1turn = false;
            return true;
        }

        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            if (field[0][2].equals("X")) {
                player1turn = true;
            } else player1turn = false;
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

    private void computerwins() {
        computerpoints++;
        Toast.makeText(this, "Computer Wins", Toast.LENGTH_LONG).show();
        updatepointsText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void

    updatepointsText() {
        tv_p1.setText("Player 1(X): " + player1points);
        tv_pc.setText("Computer(O): " + computerpoints);
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
        computerpoints = 0;
        updatepointsText();
        resetBoard();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count", count);
        outState.putInt("player1points", player1points);
        outState.putInt("computerpoints", computerpoints);
        outState.getBoolean("player1turn", player1turn);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        count = savedInstanceState.getInt("count");
        player1points = savedInstanceState.getInt("player1points");
        computerpoints = savedInstanceState.getInt("computerpoints");
        player1turn = savedInstanceState.getBoolean("player1turn");
    }
}
