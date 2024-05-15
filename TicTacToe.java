// Tic Tac Toe is fun!

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends Frame implements ActionListener {
    private Button[] buttons = new Button[9];
    private boolean isXturn = true;

    public TicTacToe() {
        setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setVisible(true);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Game");
        MenuItem newGame = new MenuItem("New Game");
        newGame.addActionListener(e -> resetGame());
        menu.add(newGame);
        menuBar.add(menu);
        setMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        if (clickedButton.getLabel().equals("")) {
            clickedButton.setLabel(isXturn ? "X" : "O");
            isXturn = !isXturn;
            checkWinner();
        }
    }

    private void checkWinner() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 9; i++) {
            board[i / 3][i % 3] = buttons[i].getLabel();
        }

        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]) && !board[i][0].equals("")) {
                announceWinner(board[i][0]);
                return;
            }
            if (board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]) && !board[0][i].equals("")) {
                announceWinner(board[0][i]);
                return;
            }
        }
        if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]) && !board[0][0].equals("")) {
            announceWinner(board[0][0]);
            return;
        }
        if (board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]) && !board[0][2].equals("")) {
            announceWinner(board[0][2]);
            return;
        }
    }

    private void announceWinner(String winner) {
        Dialog dialog = new Dialog(this, "Game Over", true);
        dialog.setLayout(new FlowLayout());
        dialog.add(new Label("Player " + winner + " wins!"));
        Button okButton = new Button("OK");
        okButton.addActionListener(e -> {
            dialog.setVisible(false);
            resetGame();
        });
        dialog.add(okButton);
        dialog.setSize(200, 100);
        dialog.setVisible(true);
    }

    private void resetGame() {
        for (Button button : buttons) {
            button.setLabel("");
        }
        isXturn = true;
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
