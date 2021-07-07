package pl.edu.pwr.s249346.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;


public class Bullet extends View {

    float left_position,top_position;
    int ballWidth,ballHeight;
    final int speed = 30;
    Bitmap fireball;

    public Bullet(Context context,float x,float y,int picture)
    {
        super(context);

        switch(picture) {
            case 1:
                fireball = BitmapFactory.decodeResource(getResources(),R.drawable.ball);
                break;
            case 2:
                fireball = BitmapFactory.decodeResource(getResources(),R.drawable.fireball);
                break;
        }

        fireball = Bitmap.createScaledBitmap(fireball,50,50,false);

        left_position = x;
        top_position = y;

        ballHeight=fireball.getHeight();
        ballWidth=fireball.getWidth();
    }

    void updateCharacter()
    {
        left_position += speed;
    }

    void updateEnemy()
    {
        left_position-=speed;
    }
}
