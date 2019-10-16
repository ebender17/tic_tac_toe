import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToeFrame extends JFrame {

    //Game
    TicTacToe game = new TicTacToe();
    TicTacToeTile[][] board = game.getBoard();

    //Score count
    int XWins;
    int OWins;
    int Ties;

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

    //Fonts
    Font gameBoardFont = new Font("Helvetica", Font.BOLD, 20);
    Font XOButtons = new Font("Helvetica", Font.BOLD, 30);
    Font mainLabelFont = new Font("Helvetica", Font.BOLD, 48);

    //Layout manager objects
    BorderLayout mainPanelLayout = new BorderLayout();
    BorderLayout buttonPanelLayout = new BorderLayout();
    GridLayout boardPanelLayout = new GridLayout(game.getBoardSize(), game.getBoardSize());
    GridLayout resultsPanelLayout = new GridLayout(3, 1);

    //Button
    JButton quitButton = new JButton("Quit", new ImageIcon("assets/quit.png"));



    public TicTacToeFrame(String title) {
        super(title);

        UISetup();

        add(mainPanel);
        setSize(700, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void UISetup() {
        //set up layout of each panel
        mainPanel.setLayout(mainPanelLayout);
        buttonPanel.setLayout(buttonPanelLayout);
        gameBoardPanel.setLayout(boardPanelLayout);
        resultsPanel.setLayout(resultsPanelLayout);

        //set the fonts
        XWinsLabel.setFont(gameBoardFont);
        OWinsLabel.setFont(gameBoardFont);
        TiesLabel.setFont(gameBoardFont);
        titleLabel.setFont(mainLabelFont);

        //create game board
        setupGameBoard();

        //add result labels to result panel
        resultsPanel.add(XWinsLabel);
        resultsPanel.add(OWinsLabel);
        resultsPanel.add(TiesLabel);

        //add actionListener to quit button & add quit button to button panel
        quitButton.addActionListener((ActionEvent actionEvent)-> System.exit(0));
        buttonPanel.add(quitButton);

        //add all panels to main panel
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(gameBoardPanel, BorderLayout.CENTER);
        mainPanel.add(resultsPanel, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);


    }

    private void setupGameBoard() {
        for (int row = 0; row < game.getBoardSize(); row++) {
            for (int col = 0; col < game.getBoardSize(); col++) {
                gameBoardPanel.add(board[row][col]);
                board[row][col].setFont(XOButtons);
                board[row][col].addActionListener((ActionEvent actionEvent)-> {
                    TicTacToeTile selected = (TicTacToeTile) actionEvent.getSource();
                    game.playTurn(selected.getRow(), selected.getColumn());
                    selected.setForeground(
                            game.getCurrentTurn().name().equals("X") ? Color.BLUE: Color.RED //if current turn equal X set color to red, else blue
                    );
                    for(ActionListener al: selected.getActionListeners()) {
                        selected.removeActionListener(al);
                    }

                    game.calculateResult();

                    if(game.isOver()) {
                        System.out.println("The game is over and the result is: " + game.getResult());
                        updateGameResults();
                        Boolean done = SafeInput.getYNConfirmDialog("Play Again?");

                        if (!done) {
                            System.exit(0);
                        }

                        resetGame();
                    }
                });

            }

        }
    }

    private void updateGameResults() {
        switch (game.getResult()) {
            case "X":
                XWins++;
                XWinsLabel.setText("X: " + XWins);
                break;
            case "0":
                OWins++;
                OWinsLabel.setText("O: " + OWins);
                break;
            case "tie":
                Ties++;
                TiesLabel.setText("Ties: " + Ties);
                break;

        }

    }


    private void resetGame() {

        game = new TicTacToe();
        board = game.getBoard();
        gameBoardPanel.removeAll();
        setupGameBoard();
    }
}
