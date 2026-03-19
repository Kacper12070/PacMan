package PacMan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mapSize extends JPanel{
    public mapSize(CardLayout cardLayout, JPanel gamePanel){
        setBackground(Color.black);
        setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));

        JLabel wiersze = new JLabel("Ilość wierszy: ", JLabel.CENTER);
        wiersze.setForeground(Color.white);
        wiersze.setAlignmentX(Component.CENTER_ALIGNMENT);
        wiersze.setFont(new Font("Arial",Font.BOLD,30));

        JLabel kolumny = new JLabel("Ilość kolumn: ", JLabel.CENTER);
        kolumny.setForeground(Color.white);
        kolumny.setAlignmentX(Component.CENTER_ALIGNMENT);
        kolumny.setMaximumSize(new Dimension(200,70));
        kolumny.setFont(new Font("Arial",Font.BOLD,30));

        JSlider wierszePrzycisk = new JSlider(JSlider.HORIZONTAL, 10, 100, 10);
        wierszePrzycisk.setForeground(Color.white);
        wierszePrzycisk.setMaximumSize(new Dimension(300, 20));
        wierszePrzycisk.setPaintTicks(true);
        wierszePrzycisk.setPaintLabels(true);
        wierszePrzycisk.setMajorTickSpacing(10);

        JSlider kolumnyPrzycisk = new JSlider(JSlider.HORIZONTAL, 10, 100, 10);
        kolumnyPrzycisk.setForeground(Color.white);
        kolumnyPrzycisk.setMaximumSize(new Dimension(300, 20));
        kolumnyPrzycisk.setPaintTicks(true);
        kolumnyPrzycisk.setPaintLabels(true);
        kolumnyPrzycisk.setMajorTickSpacing(10);
        kolumnyPrzycisk.setBackground(Color.white);

        JButton playGame = new JButton("Play Game");
        playGame.setForeground(Color.BLACK);
        playGame.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createVerticalStrut(30));
        this.add(wiersze);
        this.add(wierszePrzycisk);
        this.add(Box.createVerticalStrut(25));
        this.add(kolumny);
        this.add(kolumnyPrzycisk);
        this.add(Box.createVerticalStrut(50));
        this.add(playGame);

        playGame.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        int rows = wierszePrzycisk.getValue();
                        int cols = kolumnyPrzycisk.getValue();

                        GameController gameController = new GameController(rows,cols);

                        //skalowanie
                        int maxWidth = 800;
                        int maxHeight = 950;

                        int cellWidth = maxWidth/cols;
                        int cellHeight = maxHeight/rows;

                        int cellSize =  Math.min(cellWidth, cellHeight);

                        gameController.getView().setCellSize(cellSize);
                        gamePanel.add(gameController.getView(), "mapa");
                        cardLayout.show(gamePanel, "mapa");

                        gameController.getPacman().setCardPanel(gamePanel);
                        SwingUtilities.invokeLater(() -> gameController.getView().requestFocusInWindow());

                    }
                }
        );

    }
}
