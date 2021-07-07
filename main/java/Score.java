package pl.edu.pwr.s249346.myapplication;

import android.graphics.Color;
import android.graphics.Paint;

public class Score {

    Paint paint;
    final float position_x = 50,position_y = 80;
    int points=0;
    String text;

    public Score()
    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setTextSize(80);

        text = "Score: 0";
    }

    public void update()
    {
        points++;
        text = "Score: "+points;
    }

    public void levelUpdate(Game_engine game_engine)
    {
        if(points>0 && points%3==0) {
            game_engine.enemySwitchTime-=100;
        }
    }
}
