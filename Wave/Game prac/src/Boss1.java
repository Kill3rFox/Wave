import java.awt.*;
import java.util.Random;

public class Boss1 extends GameObject
{
    private final Handler handler;
    Random r = new Random();

    private int timer = 80;
    private int timer2 = 50;

    public Boss1(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = 0;
        velY = 2;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 96, 96);
    }

    //Moves enemy around//
    public void tick() {
        x += velX;
        y += velY;

        //Big boi comes down//
        if (timer <= 0)
            velY = 0;
        else
            timer--;

        if(timer <= 0)
            timer2--;

        //For the lil boss bullets//
        if(timer2 <= 0)
        {
            if(velX == 0)
                velX = 2;
            if(velX > 0)
                velX += 0.01f;
            else if(velX < 0)
                velX -= 0.005f;

            //Went a bit too fast//
            velX = Game.clamp(velX, -10, 10);

            int spawn = r.nextInt(10);
            if(spawn == 0)
                handler.addObject(new EnemyBossBullet((int) x + 48, (int) y + 48, ID.BasicEnemy, handler));
        }

        if(x <= 0 || x >= Game.WIDTH - 96)
            velX *= -1;
    }
    //Puts enemy on screen//
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 96, 96);
    }
}