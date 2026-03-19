package PacMan;

public abstract class Character {
    protected int startX;
    protected int startY;

    public Character(int startX, int startY) {
        this.startX = startX;
        this.startY = startY;
    }
    public int getX() {
        return startX;
    }
    public int getStartY() {
        return startY;
    }
    public abstract void move();
}
