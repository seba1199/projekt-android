package pl.edu.pwr.s249346.myapplication;


import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.util.logging.Handler;


public class Background extends View{

   Bitmap background;
   int screen_width, screen_height;

    public Background(Context context)
    {
        super(context);
        screen_width=Resources.getSystem().getDisplayMetrics().widthPixels;
        screen_height= Resources.getSystem().getDisplayMetrics().heightPixels;

        background= BitmapFactory.decodeResource(getResources(),R.drawable.background);
        background = Bitmap.createScaledBitmap(background,screen_width,screen_height,false);
    }

}
