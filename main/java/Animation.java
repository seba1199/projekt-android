package pl.edu.pwr.s249346.myapplication;

import android.graphics.Bitmap;
import java.util.Timer;


public class Animation {

    int characterWidth,enemyWidth;
    int characterCount=0,enemyCount=0;

    public void characterUpdate(Character character)
    {
        characterWidth = character.character.getWidth()/4;

        if(characterCount==0) {
            character.scaledCharacter = Bitmap.createBitmap(character.character,0,0,character.character.getWidth()/4,
                    character.character.getHeight());
        }
        else if(characterCount==1) {
            character.scaledCharacter = Bitmap.createBitmap(character.character,characterWidth,
                    0,character.character.getWidth()/4, character.character.getHeight());
        }
        else if(characterCount==2) {
            character.scaledCharacter = Bitmap.createBitmap(character.character,characterWidth*2,0,
                    character.character.getWidth()/4, character.character.getHeight());
        }
        else if(characterCount==3) {
            character.scaledCharacter = Bitmap.createBitmap(character.character,characterWidth*3,0,
                    character.character.getWidth()/4, character.character.getHeight());
        }

        characterCount++;
        if(characterCount==4) characterCount=0;

    }


    public void enemyUpdate(Enemy enemy) {

        enemyWidth = enemy.enemy.getWidth()/3;

        if(enemyCount==0) {
            enemy.scaledEnemy = Bitmap.createBitmap(enemy.enemy,0,0,enemy.enemy.getWidth()/3,
                    enemy.enemy.getHeight());
        }
        else if(enemyCount==1) {
            enemy.scaledEnemy = Bitmap.createBitmap(enemy.enemy,enemyWidth,0,enemy.enemy.getWidth()/3,
                    enemy.enemy.getHeight());
        }
        else if(enemyCount==2) {
            enemy.scaledEnemy = Bitmap.createBitmap(enemy.enemy,enemyWidth*2,0,enemy.enemy.getWidth()/3,
                    enemy.enemy.getHeight());
        }

        enemyCount++;
        if(enemyCount==3) enemyCount=0;

    }

}
