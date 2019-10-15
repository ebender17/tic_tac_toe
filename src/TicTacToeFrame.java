import org.w3c.dom.ls.LSOutput;
import javax.swing.*;
public class TicTacToeFrame extends JFrame {
    public TicTacToeFrame(String title) {
        super(title);
        add(mainPanel);
        setSize(700, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
