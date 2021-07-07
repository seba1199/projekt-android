package pl.edu.pwr.s249346.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;



public class Game_engine extends View {

    Background background;
    Character character;
    int screen_width,screen_height;
    Vector<Enemy> enemies = new Vector<>();
    Vector<Bullet> characterBullets = new Vector<>();
    Vector<Bullet> enemyBullets = new Vector<>();
    Score score;
    Animation animation;
    Timer switchTimer,enemyTimer,updateTimer,enemyBulletsTimer;
    int enemySwitchTime=6000;
    Collision collision;
    Vector<HP> hpVector = new Vector<>();
    HP characterHP;
    boolean isEnemyDestroyed=false;
    final int bottomPosGen = 800,topPosGen=0;
    Intent intent;


    public Game_engine (Context context)  {
        super(context);

        intent=new Intent();
        intent.setClass(getContext(),GameOverActivity.class);

        background = new Background(context);
        character = new Character(context);

        updateTimer = new Timer();
        enemyTimer=new Timer();
        enemyBulletsTimer=new Timer();

        screen_width= Resources.getSystem().getDisplayMetrics().widthPixels;
        screen_height= Resources.getSystem().getDisplayMetrics().heightPixels;

        characterHP=new HP();

        setEnemyTimer(context);

        updateTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                for(Enemy enemy:enemies) {
                    enemy.movement();
                }

                for (Bullet characterBullet:characterBullets) {
                    characterBullet.updateCharacter();
                }

                for(Bullet enemyBullet:enemyBullets) {
                    enemyBullet.updateEnemy();
                }

                invalidate();
            }
        }, 0, 30);


        score = new Score();
        animation = new Animation();
        switchTimer = new Timer();

        switchTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                animation.characterUpdate(character);

                for(Enemy enemy:enemies) {
                    animation.enemyUpdate(enemy);
                }

                invalidate();
            }
        }, 0, 450);


        enemyBulletsTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                for(int i=0;i<enemies.size();i++) {
                    enemyBullets.addElement(new Bullet(context,enemies.elementAt(i).left_position,
                            enemies.elementAt(i).top_position+enemies.elementAt(i).enemyHeight/2f,2));
                }
            }
        }, 0, 3000);

       collision = new Collision();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background.background,0,0,null);
        canvas.drawBitmap(character.scaledCharacter,character.left_position,character.top_position,null);
        canvas.drawText(score.text,score.position_x,score.position_y,score.paint);
        canvas.drawText(characterHP.text,characterHP.position_x,characterHP.position_y,characterHP.paint);

        for(int i=0;i<enemies.size();i++) {
            canvas.drawBitmap(enemies.elementAt(i).scaledEnemy,enemies.elementAt(i).left_position,
                    enemies.elementAt(i).top_position,null);

            canvas.drawText(hpVector.elementAt(i).text,hpVector.elementAt(i).position_x,
                    hpVector.elementAt(i).position_y,hpVector.elementAt(i).paint);

            hpVector.elementAt(i).updateEnemyPosition(enemies.elementAt(i));

            if(enemies.elementAt(i).left_position<=0) {
                enemies.removeElementAt(i);
                characterHP.updateCharacterHP(character);
                if(character.HP<=70) {
                    intent.putExtra("points",String.valueOf(score.points));
                    getContext().startActivity(intent);
                }
            }
        }

        for(int i=0;i<characterBullets.size();i++) {
            canvas.drawBitmap(characterBullets.elementAt(i).fireball,characterBullets.elementAt(i).left_position,
                    characterBullets.elementAt(i).top_position,null);

            if(characterBullets.elementAt(i).left_position>=screen_width) characterBullets.removeElementAt(i);
        }

        for (int e = 0; e < enemies.size(); e++) {
                 for (int i = 0; i < characterBullets.size(); i++) {
                    if (collision.isEnemyShot(enemies.elementAt(e), characterBullets.elementAt(i))) {
                        characterBullets.removeElementAt(i);
                        hpVector.elementAt(e).updateEnemyHP(enemies.elementAt(e));
                        if (enemies.elementAt(e).HP <= 0) {
                            hpVector.removeElementAt(e);
                            enemies.removeElementAt(e);
                            isEnemyDestroyed = true;
                            score.update();
                            score.levelUpdate(this);
                            setEnemyTimer(getContext());
                            break;
                        }
                    }
                }
                if (isEnemyDestroyed) {
                    isEnemyDestroyed = false;
                    break;
                }
            }

        characterHP.updateCharacterPosition(character);

        for(int i=0;i<enemyBullets.size();i++) {
            canvas.drawBitmap(enemyBullets.elementAt(i).fireball,enemyBullets.elementAt(i).left_position,
                    enemyBullets.elementAt(i).top_position,null);

            if(enemyBullets.elementAt(i).left_position<=0) enemyBullets.removeElementAt(i);
        }

        for(int i=0;i<enemyBullets.size();i++) {
                if (collision.isCharacterShot(character, enemyBullets.elementAt(i)))
                {
                    enemyBullets.removeElementAt(i);
                    characterHP.updateCharacterHP(character);
                    if(character.HP<=70) {
                        intent.putExtra("points",String.valueOf(score.points));
                        getContext().startActivity(intent);
                    }
                }
            }

    }


  @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(character.top_position<=topPosGen && event.getX() < (float)screen_width/2 &&
                event.getY() < (float)screen_height/2) {
            return super.onTouchEvent(event);
        }
        else if(character.top_position>=bottomPosGen && event.getX() < (float)screen_width/2 &&
                event.getY() > (float)screen_height/2){
            return super.onTouchEvent(event);
        }

        if(event.getX() < (float)screen_width/2) {
            if(event.getY() < (float)screen_height/2) {
                character.top_position -= character.speed;
            }
            else if(event.getY() > (float)screen_height/2) {
                character.top_position += character.speed;
            }
        }
        else if(event.getX() > (float)screen_width/2) {
            characterBullets.addElement(new Bullet(getContext(),character.left_position+character.scaledCharacter.getWidth(),
                    character.top_position+character.scaledCharacter.getHeight()/2f,1));
        }

        invalidate();
        return super.onTouchEvent(event);
    }


    public void setEnemyTimer(Context context)  {
        enemyTimer.cancel();
        enemyTimer= new Timer();

        enemyTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                enemies.addElement(new Enemy(context,screen_width, (int) (screen_height-
                        Math.floor(Math.random()*(800 +1)+500)),
                        (int) Math.floor(Math.random()*(2 +1)+0)));

                hpVector.addElement(new HP());
                invalidate();

            }
        }, 0, enemySwitchTime);
    }


}
