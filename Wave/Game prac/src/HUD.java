import java.awt.*;

public class HUD
{
    //Makes the health bar//
    public static float HEALTH = 100;

    private float greenValue = 255;
    //Score and level//
    private int score = 0;
    private int level = 1;

    public void tick()
    {
        HEALTH = (int) Game.clamp(HEALTH,0,100);

        //Transition hp to red//
        greenValue = HEALTH * 2;

        score++;
    }

    public void render(Graphics g)
    {
        //Health bar//
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, (int)greenValue, 0));
        g.fillRect(15, 15, (int)HEALTH * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        //Score and level//
        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }

    //Getter and setter for score//
    public void score(int score)
    {
        this.score = score;
    }
    public int getScore()
    {
        return score;
    }

    public int getLevel(){return level;}
    public void setLevel(int level){this.level = level;}


}
