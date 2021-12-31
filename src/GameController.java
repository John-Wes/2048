import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class GameController extends JPanel {
    Board board;
    JLabel score, best, status; 

    public GameController(JLabel score, JLabel best, JLabel status) {
        this.score = score;
        this.best = best;
        this.status = status;
        board = new Board();
        setFocusable(true);

        /**
         * Add a listener to see if a move was made
         */
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                String dir = "";

                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    dir = "left";
                } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    dir = "right";
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    dir = "down";
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    dir = "up";
                }

                board.move(dir);
                score.setText("Score: " + Integer.toString(board.getScore()));
                best.setText("Best: " + Integer.toString(board.getBest()));
                updateStatus();
                repaint();

            }
        });
    };

    /**
     * Updates the message displayed by the status
     */
    public void updateStatus() {
        if (!board.isGameOver()) {
            status.setText("Use the arrows to make a move!");
        } else if (board.hasWon()) {
            status.setText("You win! Press reset to start a new game!");
        } else {
            status.setText("You lose! Press reset to start a new game!");
        }
    }

    /**
     * Resets the game
     */
    public void reset() {
        board.reset();
        score.setText("Score: " + Integer.toString(board.getScore()));
        best.setText("Best: " + Integer.toString(board.getBest()));
        updateStatus();
        requestFocusInWindow();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        board.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(475, 485);
    }

}