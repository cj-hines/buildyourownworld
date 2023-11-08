package byow.Core;

/* written by cj */

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;
import static byow.Core.Engine.RANDOM;

public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Position(double x, double y) {
        int xint = (int) Math.round(x);
        int yint = (int) Math.round(y);
        this.x = xint;
        this.y = yint;
    }

    public Position(Position ref, int xOff, int yOff) {
        this(ref.getX() + xOff, ref.getY() + yOff);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean equals(Position p) {
        if (this == null || p == null) {
            return false;
        }
        return this.x == p.getX() && this.y == p.getY();
    }

    public static void normalize(Position a, Position b) {
        //normalize position when using random numbers
        //make sure a is top left and b is bottom right
        if (a.getX() > b.getX()) {
            int temp = a.getX();
            a.changePos(b.getX(), a.getY());
            b.changePos(temp, b.getY());
        }
        if (a.getY() < b.getY()) {
            int temp = a.getY();
            a.changePos(a.getX(), b.getY());
            b.changePos(b.getX(), temp);
        }
    }

    public void changePos(int a, int b) {
        this.x = a;
        this.y = b;
    }

    public boolean isInside(Room r) {
        int topX = r.getTopLeft().getX();
        int topY = r.getTopLeft().getY();
        int botX = r.getBotRight().getX();
        int botY = r.getBotRight().getY();
        if (this.x >= topX
                && this.x <= botX
                && this.y <= topY
                && this.y >= botY) {
            return true;
        }
        return false;
    }

    public static Position randomPos() {
        int x = RANDOM.nextInt(WIDTH - 1);
        int y = RANDOM.nextInt(HEIGHT - 1);
        while (x == 0) {
            x = RANDOM.nextInt(WIDTH - 1);
        }
        while (y == 0) {
            y = RANDOM.nextInt(HEIGHT - 1);
        }
        return new Position(x, y);
    }

    public static Position inRoom(Room r) {
        int topX = r.getTopLeft().getX();
        int topY = r.getTopLeft().getY();
        int botX = r.getBotRight().getX();
        int botY = r.getBotRight().getY();
        int x = RANDOM.nextInt(botX);
        int y = RANDOM.nextInt(topY);
        while (x <= topX) {
            x = RANDOM.nextInt(botX);
        }
        while (y <= botY) {
            y = RANDOM.nextInt(topY);
        }
        return new Position(x, y);
    }
}
