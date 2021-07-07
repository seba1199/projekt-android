package pl.edu.pwr.s249346.myapplication;

import android.graphics.Color;
import android.graphics.Paint;

public class HP {

    Paint paint;
    float position_x,position_y;
    int points=100;
    String text;

    public HP()
    {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.RED);
        paint.setTextSize(50);

        text=String.valueOf(points);
    }

    public void updateEnemyPosition(Enemy enemy)
    {
       position_x=enemy.left_position+enemy.enemyWidth/10f;
       position_y=enemy.top_position-5;
    }

    public void updateEnemyHP(Enemy enemy)
    {
        enemy.HP-=Math.floor(Math.random()*(25-10+1)+10);
        text=String.valueOf(enemy.HP);
    }

    public void updateCharacterPosition(Character character)
    {
        position_x=character.left_position+character.character_width/13f;
        position_y=character.top_position;
    }

    public void updateCharacterHP(Character character)
    {
        character.HP-=Math.floor(Math.random()*(8-5+1)+5);
        text=String.valueOf(character.HP);
    }
}
