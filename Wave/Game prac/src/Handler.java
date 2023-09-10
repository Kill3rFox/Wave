import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;

//Updates and renders objects//
public class Handler
{
    ArrayList<GameObject> object = new ArrayList<GameObject>();

    //Goes through the objects//
    public void tick()
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g)
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    //Wipes the screen//
    public void clearEnemys()
    {
        for(int i = 0; i < object.size(); i++)
        {
            GameObject tempObject = object.get(i);

            if(tempObject.getId() == ID.Player)
            {
                object.clear();
                if(Game.gameState != Game.STATE.End)
                {
                    addObject(new Player((int) tempObject.getX(), (int) tempObject.getY(), ID.Player, this));
                }
            }
        }
    }

    //Adds an object to the linkedlist//
    public void addObject(GameObject object)
    {
        this.object.add(object);
    }
    //removes object from linkedlist//
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
}
