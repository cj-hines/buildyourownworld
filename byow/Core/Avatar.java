package byow.Core;

/* written by cj */

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class Avatar {
    private String NAME;
    private int HEALTH;
    private static final TETile SKIN = Tileset.AVATAR;
    private Position POSITION;
    private int oldX;
    private int oldY;
    private boolean hasKey;

    public Avatar(String name) {
        this.NAME = name;
        this.HEALTH = 5;
        this.POSITION = new Position(40, 25);
        this.hasKey = false;
    }

    public Avatar(String name, int health) {
        this.NAME = name;
        this.HEALTH = health;
    }

    public void goLeft() {
        oldX = POSITION.getX();
        oldY = POSITION.getY();
        this.POSITION = new Position(this.POSITION, -1, 0);
    }

    public void goRight() {
        oldX = POSITION.getX();
        oldY = POSITION.getY();
        this.POSITION = new Position(this.POSITION, 1, 0);
    }

    public void goUp() {
        oldX = POSITION.getX();
        oldY = POSITION.getY();
        this.POSITION = new Position(this.POSITION, 0, 1);
    }

    public void goDown() {
        oldX = POSITION.getX();
        oldY = POSITION.getY();
        this.POSITION = new Position(this.POSITION, 0, -1);
    }

    public void position(int x, int y) {
        Position p = new Position(x, y);
        this.POSITION = p;
    }

    public Position getPosition() {
        return POSITION;
    }

    public int getHealth() {
        return HEALTH;
    }

    public String getName() {
        return NAME;
    }

    public int getOldX() {
        return oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public boolean hasKey() {
        return hasKey;
    }

    public void loseHealth() {
        HEALTH = HEALTH - 1;
        if (HEALTH == 0) {
            Interactions i = new Interactions();
            i.gameOver();
        }
    }
}
