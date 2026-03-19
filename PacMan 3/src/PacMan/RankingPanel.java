package PacMan;

import javax.swing.*;
import java.awt.*;

public class RankingPanel extends JPanel {
    private JList<ScoreEntry> scoreList;
    private DefaultListModel<ScoreEntry> scoreListModel;

    public RankingPanel(Ranking ranking) {
        setLayout(new BorderLayout());
        this.setBackground(Color.black);
        this.setForeground(Color.white);
        scoreListModel = new DefaultListModel<>();
        for(ScoreEntry scoreEntry : ranking.getScores()) {
            scoreListModel.addElement(scoreEntry);
        }
        scoreList = new JList<>(scoreListModel);
        scoreList.setFont(new Font("Arial", Font.PLAIN, 20));
        scoreList.setForeground(Color.white);
        scoreList.setBackground(Color.black);
        scoreList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(new JLabel("Ranking", SwingConstants.CENTER), BorderLayout.NORTH);
        this.add(new JScrollPane(scoreList), BorderLayout.CENTER);
    }
}
