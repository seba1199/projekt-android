package pl.edu.pwr.s249346.myapplication;



public class Collision{

    public boolean isEnemyShot(Enemy enemy, Bullet bullet)
    {
        return enemy.left_position < bullet.left_position + bullet.ballWidth && enemy.top_position <= bullet.top_position
                + bullet.ballHeight && enemy.top_position + enemy.enemyHeight >= bullet.top_position;
    }

    public boolean isCharacterShot(Character character, Bullet bullet)
    {
        return character.left_position+character.character_width/2f > bullet.left_position &&
                character.top_position <= bullet.top_position + bullet.ballHeight &&
                character.top_position + character.character_height >= bullet.top_position;
    }

}
