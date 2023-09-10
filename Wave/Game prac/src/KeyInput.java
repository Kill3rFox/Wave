import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//User Input//
public class KeyInput extends KeyAdapter
{
    private final Handler handler;
    private final boolean[] keyDown = new boolean[4];

    Game game;

    public KeyInput(Handler handler, Game game)
    {
        this.handler = handler;

        this.game = game;

        keyDown[0] = false;
        keyDown[1] = false;
        keyDown[2] = false;
        keyDown[3] = false;

    }
    //Key is pressed//
    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();
        //Loops through to get the player obj//
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            //Events for player//
            if(tempObject.getId() == ID.Player)
            {
                //Up//
                if(key == KeyEvent.VK_W)
                {
                    tempObject.setVelY(-5);
                    keyDown[0] = true;
                }
                //Down//
                if(key == KeyEvent.VK_S)
                {
                    tempObject.setVelY(5);
                    keyDown[1] = true;
                }
                //Left//
                if(key == KeyEvent.VK_A)
                {
                    tempObject.setVelX(-5);
                    keyDown[2] = true;
                }
                //Right//
                if(key == KeyEvent.VK_D)
                {
                    tempObject.setVelX(5);
                    keyDown[3] = true;
                }
            }

        }
        //Pause//
        if(key == KeyEvent.VK_P)
        {
            if(Game.gameState == Game.STATE.Game)
            {
                Game.paused = !Game.paused;
            }
        }
        //Exit//
        if(key == KeyEvent.VK_ESCAPE)
            System.exit(1);
    }

    //When the key is released//
    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();
        //Loops through to get the player obj//
        for(int i = 0; i < handler.object.size(); i++)
        {
            GameObject tempObject = handler.object.get(i);

            //Events for player//
            if(tempObject.getId() == ID.Player)
            {
                //Up//
                if(key == KeyEvent.VK_W)
                {
                    keyDown[0] = false;
                }
                //Down//
                if(key == KeyEvent.VK_S)
                {
                    keyDown[1] = false;
                }
                //Left//
                if(key == KeyEvent.VK_A)
                {
                    keyDown[2] = false;
                }
                //Right//
                if(key == KeyEvent.VK_D)
                {
                    keyDown[3] = false;
                }

                //Ver
                if(!keyDown[0] && !keyDown[1])
                    tempObject.setVelY(0);
                //Hor
                if(!keyDown[2] && !keyDown[3])
                    tempObject.setVelX(0);
            }
        }
    }
}
