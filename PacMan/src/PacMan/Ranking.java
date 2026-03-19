package PacMan;

import java.io.*;
import java.util.*;

public class Ranking implements Serializable {
    private String file = "ranking.ser";
    private List<ScoreEntry> scores = new ArrayList<>();

    public void addScore(ScoreEntry score) {
        scores.add(score);
        scores.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
    }

    public List<ScoreEntry> getScores() {
        return scores;
    }public void saveToFile() {
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(scores);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void loadFromFile() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            scores = (List<ScoreEntry>) in.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
