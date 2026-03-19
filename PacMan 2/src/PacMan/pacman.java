package PacMan;

import javax.swing.*;
import java.awt.*;

public class pacman extends Character implements Runnable {
    private GameController gameController;
    public Direction direction;
    private boolean running = true;
    private int[][] map;
    private Runnable onMove;
    private int score = 0;
    public int speed = 150;
    private JPanel cards;

    public pacman(int startX, int startY, GameController gameController, Runnable onMove) {
        super(startX, startY);
        this.gameController = gameController;
        this.onMove = onMove;
        this.direction = Direction.RIGHT;
    }
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move(){
        int newX = startX;
        int newY = startY;
        switch(direction){
            case RIGHT:
                newX++;
                break;
            case LEFT:
                newX--;
                break;
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
        }

        int nextCell = gameController.getCell(newY, newX);
        if (nextCell != 1) {
            if(nextCell == 2){
                score +=10;
            }else if(nextCell == 4) {
                gameController.stopGame();

                String player = JOptionPane.showInputDialog(null, "You loose! Enter your name");
                if (player == null || player.equals(""))
                    player = "Player";
                Ranking ranking = new Ranking();
                ranking.loadFromFile();
                ranking.addScore(new ScoreEntry(player, score));
                ranking.saveToFile();

                JOptionPane.showMessageDialog(null, "Your score is saved");
                CardLayout layout = (CardLayout) cards.getLayout();
                layout.show(cards, "menu");
            }else if(nextCell == 5){
                score +=20;
            }else if(nextCell == 6){
                score +=30;
            }else if(nextCell == 7){
                speed = 130;

            }
            gameController.setCell(startY, startX, 0);
            startX = newX;
            startY = newY;
            gameController.setCell(startY, startX, 3);
        }
    }
    public int getX(){
        return startX;
    }
    public int getY(){
        return startY;
    }
    public int getScore() {
        return score;
    }
    public void stop(){
        running = false;
    }
    public void setCardPanel(JPanel cards) {
        this.cards = cards;
    }

    public void run(){
        while(running){
            move();
            onMove.run();
            try{
                Thread.sleep(speed);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
