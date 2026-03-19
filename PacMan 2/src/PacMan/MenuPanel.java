package PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MenuPanel extends JPanel{
    private final JPanel mainPanel;
    public MenuPanel(CardLayout cardLayout, JPanel mainPanel) {
        this.mainPanel = mainPanel;
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));//pionowe dodawanie elementów
        Dimension sizeButton = new Dimension(200, 90);

        JLabel title = new JLabel("Pac-Man", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 40));;
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(Color.YELLOW);


        JButton startButton = new JButton("New Game");
        JButton highScoresButton = new JButton("High Scores");
        JButton exitButton = new JButton("Exit");

        startButton.setMaximumSize(sizeButton);
        highScoresButton.setMaximumSize(sizeButton);
        exitButton.setMaximumSize(sizeButton);

        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        highScoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);


        add(title);
        add(Box.createVerticalStrut(50));
        add(startButton);
        add(Box.createVerticalStrut(50));
        add(highScoresButton);
        add(Box.createVerticalStrut(50));
        add(exitButton);

        startButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                            cardLayout.show(mainPanel, "RozmiarMapy");


                    }
                }
        );
        highScoresButton.addActionListener(e -> {
            Ranking ranking = new Ranking();
            ranking.loadFromFile();

            RankingPanel rankingPanel = new RankingPanel(ranking);
            rankingPanel.setFocusable(true);
            rankingPanel.addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_Q &&
                            e.isControlDown() && e.isShiftDown()) {
                        CardLayout layout = (CardLayout) mainPanel.getLayout();
                        layout.show(mainPanel, "menu");
                    }
                }
            });
            mainPanel.add(rankingPanel, "ranking");
            cardLayout.show(mainPanel, "ranking");

            SwingUtilities.invokeLater(() -> rankingPanel.requestFocusInWindow());
        });
        exitButton.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        System.exit(0);
                    }
                }
        );
    }
}
