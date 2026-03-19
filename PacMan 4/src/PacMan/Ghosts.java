package PacMan;

import javax.swing.*;
import java.util.Random;

public class Ghosts extends Character implements Runnable{
    private GameController gameController;
    public Direction direction;
    private boolean running = true;
    private int[][] map;
    private Runnable onMove;
    private int kroki = 0;
    private int previousStep = 0;//zapamietywanie co było "pod" duchem
    public Ghosts(int startX, int startY, GameController gameController, Runnable onMove) {
        super(startX, startY);
        this.gameController = gameController;
        this.onMove = onMove;
        this.direction = Direction.values()[new Random().nextInt(Direction.values().length)];
    }
    public void move(){
        synchronized(gameController) {
            int newX = startX;
            int newY = startY;

            switch (direction) {
                case RIGHT:
                    newX = startX + 1;
                    break;
                case LEFT:
                    newX = startX - 1;
                    break;
                case UP:
                    newY = startY + 1;
                    break;
                case DOWN:
                    newY = startY - 1;
                    break;
            }
            int nextCell = gameController.getCell(newY, newX);

            if (nextCell != 1 && nextCell != 4) {
                gameController.setCell(startY, startX, previousStep);
                previousStep = nextCell;

                startX = newX;
                startY = newY;

                if (previousStep == 2 && Math.random() < 0.04) {
                    int randomPowerUp = 5 + new Random().nextInt(3);
                    previousStep = randomPowerUp;
                }

                gameController.setCell(startY, startX, 4);
            }
            else {
                direction = Direction.values()[new Random().nextInt(Direction.values().length)];
            }
        }

    }
    public int getX(){
        return startX;
    }
    public int getY(){
        return startY;
    }
    public void stop(){
        running = false;
    }
    public void run(){
        while(running){
            move();
            onMove.run();
            try{
                Thread.sleep(140);
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}
