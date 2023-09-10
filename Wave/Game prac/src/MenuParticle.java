import java.awt.*;
import java.util.Random;

//This is gonna make the menu look cool//
public class MenuParticle extends GameObject
{
    private final Handler handler;

    Random r = new Random();

    //Color//
    private final Color col;


    public MenuParticle(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;

        //Moves the particle around//
       velX = (r.nextInt(5 + 5) -5);
       velY = (r.nextInt(5 + 5) -5);

        //Randomizes the color;
        col = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Rectangle getBounds()
    {
        return new Rectangle((int)x, (int)y, 16, 16);
    }
    //Moves particle around//
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16)
            velX *= -1;

        handler.addObject(new Trail((int)x,(int)y,ID.Trail, col,16,16, 0.05f, handler));
    }
    //Puts particle on screen//
    public void render(Graphics g) {
        g.setColor(col);
        g.fillRect((int)x, (int)y, 16, 16);
    }
}
