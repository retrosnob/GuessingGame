package com.example.justin.guessinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Random random = new Random();
    Button btnGuess;
    EditText editTextGuess;
    TextView textViewResponse;
    int target;
    boolean gameInProgress = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGuess = (Button)findViewById(R.id.btnGuess);
        editTextGuess = (EditText)findViewById(R.id.editTextGuess);
        textViewResponse = (TextView)findViewById(R.id.textViewResponse);
        newGame();
    }

    protected void newGame() {

        target = random.nextInt(100) + 1;
        textViewResponse.setText("Waiting for first guess...");
        btnGuess.setText("Guess");
        editTextGuess.setEnabled(true);
        gameInProgress = true;
    }

    protected void btnClick(View view) {
        if (gameInProgress) {
            String sGuess = editTextGuess.getText().toString();
            if (sGuess.trim().length() == 0) { // EditText objects never return null
                Toast.makeText(view.getContext(), "Enter a guess", Toast.LENGTH_SHORT).show();
            } else {
                int guess = Integer.parseInt(sGuess);
                if (guess > target) {
                    textViewResponse.setText(sGuess + ": Too high!");
                } else if (guess < target) {
                    textViewResponse.setText(sGuess + ": Too low!");
                } else {
                    textViewResponse.setText(sGuess + ": You win!");
                    editTextGuess.setEnabled(false);
                    btnGuess.setText("Play again");
                    gameInProgress = false;
                }
                editTextGuess.setText("");
            }
        } else {
            newGame();
        }
    }

}
