package pl.edu.pwr.s249346.myapplication;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;


public class Character extends View{

    Bitmap character,scaledCharacter;
    final int speed = 80;
    int HP=100;
    int left_position=100,top_position=500;
    int character_width,character_height;


    public Character(Context context)
    {
        super(context);

        character = BitmapFactory.decodeResource(getResources(),R.drawable.character);
        character = Bitmap.createScaledBitmap(character,600,300,false);

        character_width=character.getWidth();
        character_height=character.getHeight();
    }

}
