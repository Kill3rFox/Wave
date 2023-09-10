import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter
{
    private final Game game;
    private final HUD hud;
    private final Handler handler;
    private final Random r = new Random();
    public Menu(Game game, Handler handler, HUD hud)
    {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
    }
    //Mouse is pressed//
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (Game.gameState == Game.STATE.Menu) {

            //Play//
            if (mouseOver(mx, my, 150)) {
                Game.gameState = Game.STATE.Select;
                return;
            }
            //Help//
            if (mouseOver(mx, my, 250)) {
                Game.gameState = Game.STATE.Help;
            }

            //Quit//
            if (mouseOver(mx, my, 350)) {
                System.exit(1);
            }
        }
        //Select Difficulty//
        if (Game.gameState == Game.STATE.Select) {

            //Normal//
            if (mouseOver(mx, my, 150)) {
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                game.diff = 0;
            }
            //Hard//
            if (mouseOver(mx, my, 250)) {
                Game.gameState = Game.STATE.Game;
                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.clearEnemys();
                handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 50), r.nextInt(Game.HEIGHT - 50), ID.BasicEnemy, handler));
                game.diff = 1;
            }

            //Back//
            if (mouseOver(mx, my, 350))
            {
                Game.gameState = Game.STATE.Menu;
                return;
            }
        }

        //Back for help//
        if (Game.gameState == Game.STATE.Help)
        {
            if (mouseOver(mx, my, 350)) {
                Game.gameState = Game.STATE.Menu;
                return;
            }
        }
        //Try Again//
        if (Game.gameState == Game.STATE.End)
        {
            if (mouseOver(mx, my, 350)) {
                Game.gameState = Game.STATE.Menu;
                hud.setLevel(1);
                hud.score(0);
            }
        }
    }
    //Button released//
    public void mouseReleased(MouseEvent e)
    {

    }

    //Checks what mouse is over//
    private boolean mouseOver(int mx, int my, int y)
    {
        if(mx > 210 && mx < 210 + 200)
        {
            return my > y && my < y + 64;
        }
        else
            return false;
    }

    public void tick() {

    }
    //Draws the menus//
    public void render(Graphics g)
    {
        //Menu//
        if(Game.gameState == Game.STATE.Menu)
        {
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Wave Game", 165, 110);

            //Play button//
            g.setFont(fnt2);
            g.drawRect(210,150,200,64);
            g.drawString("Play", 270, 190);
            //Help button//
            g.drawRect(210,250,200,64);
            g.drawString("Help", 270, 290);
            //Quit button//
            g.drawRect(210,350,200,64);
            g.drawString("Quit", 270, 390);
        }
        //Help//
        else if(Game.gameState == Game.STATE.Help)
        {
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);
            Font fnt3 = new Font("arial", Font.BOLD, 15);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Help", 245, 75);
            //Stuff in the menu//
            g.setFont(fnt3);
            g.drawString("Use the 'WASD' keys to move around in this fast phased game.", 100, 110);
            g.drawString("W = Up, A = Left, S = Down, D = Right, P = Pause, ESC = Exit.", 100, 130);
            g.drawString("In this game your goal is to dodge enemies and get as many", 100, 150);
            g.drawString("points as possible. Every 10 levels there is a boss enemy.", 100, 170);

            g.setFont(fnt2);
            g.setColor(Color.blue);
            g.drawString("ENJOY!", 250, 220);

            //Back button//
            g.setColor(Color.red);
            g.setFont(fnt2);
            g.drawRect(210,350,200,64);
            g.drawString("Back", 270, 390);
        }
        //End//
        else if(Game.gameState == Game.STATE.End)
        {
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("GAME OVER", 150, 85);

            g.setFont(fnt2);
            g.setColor(Color.green);
            g.drawString("SCORE: " + hud.getScore(), 215, 210);

            g.setFont(fnt2);
            g.setColor(Color.white);
            g.drawRect(210,350,200,64);
            g.drawString("Try Again", 245, 390);
        }
        //Select Difficulty/
        else if(Game.gameState == Game.STATE.Select)
        {
            Font fnt = new Font("arial", Font.BOLD, 50);
            Font fnt2 = new Font("arial", Font.BOLD, 30);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("Select Difficulty", 140, 110);

            //Play button//
            g.setFont(fnt2);
            g.drawRect(210,150,200,64);
            g.drawString("Normal", 250, 190);
            //Help button//
            g.drawRect(210,250,200,64);
            g.drawString("Hard", 270, 290);
            //Quit button//
            g.drawRect(210,350,200,64);
            g.drawString("Back", 270, 390);
        }
    }
}
