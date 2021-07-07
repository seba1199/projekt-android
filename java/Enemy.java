package pl.edu.pwr.s249346.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;


public class Enemy extends View{

    Bitmap enemy,scaledEnemy;
    final int speed = 5;
    int left_position,top_position;
    int enemyHeight,enemyWidth;
    int HP = 100;


    public Enemy(Context context,int left_position,int top_position,int picture_number)
    {
        super(context);

        this.left_position=left_position;
        this.top_position=top_position;

        switch(picture_number) {
            case 0:
                enemy = BitmapFactory.decodeResource(getResources(), R.drawable.dragon);
                break;
            case 1:
                enemy = BitmapFactory.decodeResource(getResources(), R.drawable.dragon2);
                break;
            case 2:
                enemy = BitmapFactory.decodeResource(getResources(), R.drawable.dragon3);
                break;
        }

        enemy = Bitmap.createScaledBitmap(enemy,600,300,false);

        scaledEnemy = Bitmap.createBitmap(enemy,0,0,enemy.getWidth()/3,enemy.getHeight());

        enemyWidth=enemy.getWidth();
        enemyHeight=enemy.getHeight();
    }

    public void movement()
    {
        left_position-=speed;
    }

}
