import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
public class TicTacToeFrame extends JFrame {
    //Panels
    JPanel mainPanel = new JPanel();
    JPanel gameBoardPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel resultsPanel = new JPanel();

    //Labels
    JLabel titleLabel = new JLabel("Tic Tac Toe");
    JLabel XWinsLabel = new JLabel("X : " + XWins);
    JLabel OWinsLabel = new JLabel("Y : " + OWins);
    JLabel TiesLabel = new JLabel("Ties: " + Ties);
    //Layout manager objects
    BorderLayout mainPanelLayout = new BorderLayout();
    BorderLayout buttonPanelLayout = new BorderLayout();
    GridLayout boardPanelLayout = new GridLayout(game.getBoardSize(), game.getBoardSize());
    GridLayout resultsPanelLayout = new GridLayout(3, 1);

    //Button
    JButton quitButton = new JButton("Quit", new ImageIcon("assets/quit.png"));
    public TicTacToeFrame(String title) {
        super(title);
        add(mainPanel);
        setSize(700, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
