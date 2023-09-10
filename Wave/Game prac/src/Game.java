import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.Serial;
import java.util.Random;

public class Game extends Canvas implements Runnable
{
    @Serial
    private static  final long serialVersionUID = 1550691097823471818L;

    //Height and width set//
    public static final int WIDTH = 640, HEIGHT = WIDTH /12 * 9;

    //Game runs through this//
    private Thread thread;
    private boolean running = false;

    //Pause menu//
    public static boolean paused = false;

    //Difficulty//
    public int diff = 0;

    //Random instance//
    private final Random r;
    //Handler declared//
    private final Handler handler;
    //HUD//
    private final HUD hud;
    //Enemy Spawner//
    private final Spawn spawner;

    private final Menu menu;

    //Game states//
    public enum STATE
    {
      Menu,
        Select,
        Help,
      Game,
        End
    }

    //Changes whats on screen :D//
    public static STATE gameState = STATE.Menu;

    //Initializes stuff//
    public Game() {
        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);
        this.addKeyListener(new KeyInput(handler, this));
        this.addMouseListener(menu);

        new Window(WIDTH, HEIGHT, "Wave Game", this);

        spawner = new Spawn(handler, hud, this);

        r = new Random();
        if (gameState == STATE.Game) {
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
        }
        else
        {
            for(int i = 0; i < 20; i++)
                handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
        }
    }

    //Starts thread//
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    //Stops thread and catches exception//
    public synchronized void stop()
    {
        try
        {
            thread.join();
            running =  false;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    //Game loop//
    public void run()
    {
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while(running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1)
            {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }

        }
        stop();
    }

    private void tick()
    {
        if(gameState == STATE.Game)
        {
            if(!paused)
            {
                hud.tick();
                spawner.tick();
                handler.tick();
                if(HUD.HEALTH <= 0)
                {
                    HUD.HEALTH = 100;
                    gameState = STATE.End;
                    handler.clearEnemys();
                    for(int i = 0; i < 20; i++)
                        handler.addObject(new MenuParticle(r.nextInt(WIDTH), r.nextInt(HEIGHT), ID.MenuParticle, handler));
                }
            }

        }
        else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select)
        {
            menu.tick();
            handler.tick();
        }

    }

    //Renders graphics//
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Sets the color of background//
        g.setColor(Color.BLACK);
        g.fillRect(0,0,WIDTH,HEIGHT);

        handler.render(g);

        //paused//
        if(paused)
        {
            g.setColor(Color.white);
            g.drawString("PAUSED", 100, 100);
        }

        if(gameState == STATE.Game)
        {
            hud.render(g);
        }
        else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select)
        {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    //Keep player in bounds//
    public static float clamp(float var, float min, float max)
    {
        if(var >= max)
            return max;
        else return Math.max(var, min);
    }

    //Main//
    public static void main(String[] args)
    {
        new Game();
    }

}
