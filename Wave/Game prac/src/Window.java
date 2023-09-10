import javax.swing.*;
import java.awt.*;
import java.io.Serial;

//Creates the window for the game//
public class Window extends Canvas {
    @Serial
    private static final long serialVersionUID = -240840600533728354L;

    public Window(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title);
        //Height and width of window//
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        //Closing game and messing with window//
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        //Class added to frame//
        frame.add(game);
        frame.setVisible(true);
        game.start();
    }
}
