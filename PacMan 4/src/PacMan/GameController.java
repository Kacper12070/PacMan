package PacMan;

import javax.swing.*;
import java.util.Map;

public class GameController {
    private pacman pacman;
    private MapView mapView;
    private int[][] map;
    private Ghosts g1, g2, g3, g4;

    public GameController(int rows, int columns) {
        mapView = new MapView(rows, columns);
        this.map = mapView.getMap();
        pacman = new pacman(1, 1, this, () -> {
            mapView.repaint();
            mapView.updateScore(pacman.getScore());
        });
        new Thread(pacman).start();

        g1 = new Ghosts(rows-5, columns - 5, this, mapView::repaint);
        g2 = new Ghosts(rows/2, columns/2, this, mapView::repaint);
        g3 = new Ghosts(rows /2, columns/2+5, this, mapView::repaint);
        g4 = new Ghosts(rows/2+5, columns -5, this, mapView::repaint);

        new Thread(g1).start();
        new Thread(g2).start();
        new Thread(g3).start();
        new Thread(g4).start();

        PacmanController pacmanController = new PacmanController(pacman);
        mapView.setController(pacmanController);
    }

    public MapView getView() {
        return mapView;
    }

    public pacman getPacman() {
        return pacman;
    }

    public synchronized int getCell(int row, int col) {
        return map[row][col];
    }

    public synchronized void setCell(int row, int col, int value) {
        map[row][col] = value;
        mapView.repaint();
    }
    public void stopGame(){
        pacman.stop();
        g1.stop();
        g2.stop();
        g3.stop();
        g4.stop();
    }
}
