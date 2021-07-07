package pl.edu.pwr.s249346.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity implements View.OnClickListener{

    TextView gameOverText,scoreText;
    Button gameOverButton,exitButton;
    String points_value;
    String finalScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        gameOverText = findViewById(R.id.gameOverText);
        gameOverText.setText(R.string.gameOver);
        gameOverText.setTextSize(45);
        gameOverText.setTextColor(Color.rgb(255,0,17));

        Intent intent = getIntent();
        points_value=intent.getStringExtra("points");
        finalScore=getString(R.string.score)+" "+points_value;

        scoreText = findViewById(R.id.scoreText);
        scoreText.setText(finalScore);
        scoreText.setTextSize(25);
        scoreText.setTextColor(Color.rgb(0,85,255));

        gameOverButton = findViewById(R.id.gameOverButton);
        gameOverButton.setOnClickListener(this);
        gameOverButton.setText(R.string.tryAgain);
        gameOverButton.setTextSize(20);

        exitButton=findViewById(R.id.exitButton);
        exitButton.setOnClickListener(this);
        exitButton.setTextSize(20);
        exitButton.setText(R.string.quit);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.gameOverButton) {
            Intent intent = new Intent(this,Game.class);
            startActivity(intent);
        }

        else if(v.getId()==R.id.exitButton) {
            this.finishAffinity();
        }
    }
}