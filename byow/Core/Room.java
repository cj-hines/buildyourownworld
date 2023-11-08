package byow.Core;

/* written by cj */


import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.ArrayList;

import static byow.Core.Engine.*;


public class Room {
    //instance variables
    private int size;
    private Position topLeft;
    private Position botRight;


    public Room(Position top, Position bot) {
        Position.normalize(top, bot);
        topLeft = top;
        botRight = bot;
    }

    public static Room generate(Position t, Position b) {
        return new Room(t, b);
    }

    public static Room generate() {
        return new Room(Position.randomPos(), Position.randomPos());
    }

    public static Room generateHorizontalHallway(Room r, Boolean direction) {
        Position inR = Position.inRoom(r);
        //true right false left
        if (direction) {
            return new Room(inR, new Position(WIDTH - 3, inR.getY() - 1));
        } else {
            return new Room(new Position(1, inR.getY() + 1), inR);
        }
    }

    public static Room generateVerticalHallway(Room r, Boolean direction) {
        //true up false down
        Position inR = Position.inRoom(r);
        if (direction) {
            return new Room(new Position(inR.getX() - 1, HEIGHT - 4), inR);
        } else {
            return new Room(inR, new Position(inR.getX() + 1, 3));
        }
    }


    public Position getTopLeft() {
        return topLeft;
    }

    public Position getBotRight() {
        return botRight;
    }


    public boolean isValid(ArrayList<Room> r) { //is it in scope of map or overlaps another room
        if (this.overlap(r)) {
            return false;
        }
        return true;
    }

    public int getHeight() {
        return Math.abs(topLeft.getY() - botRight.getY());
    }

    public int getWidth() {
        return Math.abs(botRight.getX() - topLeft.getX());
    }

    public void draw(TETile[][] world) {
        for (int x = topLeft.getX() - 1; x <= botRight.getX() + 1; x++) {
            for (int y = botRight.getY() - 1; y <= topLeft.getY() + 1; y++) {
                //used for hallways so they end at edge of map
                if (x == 1 || x == WIDTH - 1 || y == 3 || y == HEIGHT - 3) {
                    world[x][y] = Tileset.WALL;
                }
                if (x == topLeft.getX() - 1 || x == botRight.getX() + 1) { //pad outer edge make wall
                    if (world[x][y] != Tileset.FLOOR && world[x][y] != Tileset.FLOWER
                            && world[x][y] != Tileset.TREE && world[x][y] != Tileset.GRASS
                            && world[x][y] != Tileset.SAND) {
                        world[x][y] = Tileset.WALL;
                    }

                } else if (y == botRight.getY() - 1 || y == topLeft.getY() + 1) {
                    int rando = RANDOM.nextInt(100);
                    if (world[x][y] != Tileset.FLOOR && world[x][y] != Tileset.FLOWER
                            && world[x][y] != Tileset.TREE && world[x][y] != Tileset.GRASS
                            && world[x][y] != Tileset.SAND) {
                        if (rando == 99 || rando == 1) { //2% chance locked door may lead to nothing
                            world[x][y] = Tileset.LOCKED_DOOR;
                        } else if (rando == 4) { //1& chance of regular door
                            world[x][y] = Tileset.UNLOCKED_DOOR;
                        } else {
                            world[x][y] = Tileset.WALL;
                        }
                    }

                } else {
                    int rando = RANDOM.nextInt(100);
                    if (rando == 47 || rando == 86) { //2% chance of flower
                        world[x][y] = Tileset.FLOWER;
                    } else if (rando == 21 || rando == 74 || rando == 51) { //3% chance of tree
                        world[x][y] = Tileset.TREE;
                    } else if (rando == 11) { //1% chance of sand
                        world[x][y] = Tileset.SAND;
                    } else if (rando == 1 || rando == 5 || rando == 91
                            || rando == 99) { //4% chance of grass
                        world[x][y] = Tileset.GRASS;
                    } else {
                        world[x][y] = Tileset.FLOOR;
                    }

                }
            }
        }
    }

    public boolean isHallway() {
        return getWidth() == 1 || getHeight() == 1;
    }


    public boolean overlap(ArrayList<Room> r) {
        if (r.size() == 0 || r.size() == 1) {
            return false;
        }
        for (int i = 0; i < r.size(); i++) {
            for (int x = 1; x < r.size(); x++) {
                if (r.get(i).getTopLeft().isInside(r.get(x))
                        || r.get(i).getBotRight().isInside(r.get(x))) {
                    return true;
                }
            }
        }
        return false;

    }
}
