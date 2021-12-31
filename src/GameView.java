import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GameView implements Runnable {
    public void run() {   

        final JFrame frame = new JFrame("2048");
        frame.setLocation(400, 75);
        frame.setLayout(new BorderLayout());

        final JLabel score = new JLabel("Score: 0");
        score.setFont(new Font("Serif", Font.BOLD, 20));
        final JLabel best = new JLabel("Best: 0");
        best.setFont(new Font("Serif", Font.BOLD, 20));
        final JLabel status = new JLabel();
        status.setFont(new Font("Serif", Font.BOLD, 20));

        status.setHorizontalAlignment(JLabel.CENTER);
        status.setVerticalAlignment(JLabel.CENTER);
        
        
        GameController gameController = new GameController(score, best, status);
        frame.add(gameController, BorderLayout.CENTER);


        final JPanel controlPanel = new JPanel(new BorderLayout());
        frame.add(controlPanel, BorderLayout.NORTH);

        final JPanel center = new JPanel(new BorderLayout());

        final JPanel left = new JPanel(new BorderLayout());
        final JPanel right = new JPanel(new BorderLayout());

        center.add(left, BorderLayout.WEST);
        center.add(right, BorderLayout.EAST);

        final JLabel leftCushion = new JLabel("     ");
        final JLabel rightCushion = new JLabel("     ");
        final JLabel topCushion = new JLabel("     ");

        controlPanel.add(leftCushion, BorderLayout.WEST);
        controlPanel.add(center, BorderLayout.CENTER);
        controlPanel.add(rightCushion, BorderLayout.EAST);

        final JLabel title = new JLabel();
        title.setFont(new Font("Serif", Font.BOLD, 50));
        title.setText("2048");

        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.reset();
                gameController.repaint();
            }
        });
        
        left.add(title, BorderLayout.NORTH);
        left.add(reset, BorderLayout.SOUTH);
        right.add(topCushion, BorderLayout.NORTH);
        right.add(best, BorderLayout.CENTER);
        right.add(score, BorderLayout.SOUTH);
        

        frame.add(status, BorderLayout.SOUTH);

        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        gameController.reset();            
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GameView());
    }
}