package pl.edu.pwr.s249346.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textView_title;
    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        playButton = findViewById(R.id.button);
        playButton.setOnClickListener(this);
        playButton.setText(R.string.play);
        playButton.setTextSize(30);

        textView_title = findViewById(R.id.textView_title);
        textView_title.setText(R.string.title);
        textView_title.setTextSize(35);
        textView_title.setTextColor(Color.rgb(51,160,255));

    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {

    if(v.getId()==R.id.button) {
        Intent intent = new Intent(this,Game.class);
        startActivity(intent);
    }

    }
}