package PacMan;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public GameWindow() {
        setTitle("Pac-Man");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setSize(790, 930);
        setLocationRelativeTo(null);

        CardLayout cardLayout = new CardLayout();
        JPanel cards = new JPanel(cardLayout);

        MenuPanel menu = new MenuPanel(cardLayout, cards);
        mapSize mapa = new mapSize(cardLayout, cards);

        cards.add(menu, "menu");
        cards.add(mapa, "RozmiarMapy");


        setContentPane(cards);
        cardLayout.show(cards, "menu");
        setVisible(true);



    }
}