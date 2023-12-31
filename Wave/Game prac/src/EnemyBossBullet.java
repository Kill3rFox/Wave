import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject
{
    private final Handler handler;
    Random r = new Random();

    public EnemyBossBullet(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        velX = (r.nextInt((5 + 5) - 5));
        velY = 5;
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    //Moves enemy around//
    public void tick() {
        x += velX;
        y += velY;

        if(y >= Game.HEIGHT)
            handler.removeObject(this);

        handler.addObject(new Trail((int)x,(int)y,ID.Trail, Color.red,12,12, 0.02f, handler));
    }
    //Puts enemy on screen//
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x, (int)y, 12, 12);
    }
}
