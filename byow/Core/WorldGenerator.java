package byow.Core;

/* written by cj */

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;
import java.util.ArrayList;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;
import static byow.Core.Engine.RANDOM;


public class WorldGenerator {

    public static final int MAXSIZE = ((WIDTH) * (HEIGHT)) / 20;


    public TETile[][] createWorld(TETile[][] grid, int h) {
        ArrayList<Room> rooms = new ArrayList<>();

        //set grid to empty
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 2; y < HEIGHT - 2; y += 1) { // WOAH
                grid[x][y] = Tileset.NOTHING;
            }
        }
        Room first = Room.generate();

        while (first.getWidth() * first.getHeight() > MAXSIZE || !first.isValid(rooms)
                || first.getWidth() <= 2 || first.getHeight() <= 2) {
            first = Room.generate();
        }
        rooms.add(first);
        System.out.println("first room generated");


        while (h > 0) {
            int attempts = 0;
            Room newRoom = Room.generate();
            while (newRoom.getWidth() * newRoom.getHeight() > MAXSIZE
                    || newRoom.getWidth() <= 2 || newRoom.getHeight() <= 2
                    || !newRoom.isValid(rooms) && attempts < 1500) {
                newRoom = Room.generate();
                attempts++;
            }
            System.out.println("generated on attempt " + attempts);
            h -= 1;
            rooms.add(newRoom);

        }


        //add hallways
        int numRooms = rooms.size();
        for (int i = 0; i < numRooms; i++) {
            Random rand = new Random(rooms.get(i).getHeight());
            int numHallways = rand.nextInt(4);
            for (int x = 0; x <= numHallways; x++) {
                if (x == 0) {
                    rooms.add(Room.generateVerticalHallway(rooms.get(i), true));
                }
                if (x == 1) {
                    rooms.add(Room.generateHorizontalHallway(rooms.get(i), true));
                }
                if (x == 2) {
                    rooms.add(Room.generateVerticalHallway(rooms.get(i), false));
                }
                if (x == 3) {
                    rooms.add(Room.generateHorizontalHallway(rooms.get(i), false));
                }
            }

        }


        //draw rooms
        for (int i = 0; i < rooms.size(); i++) {
            rooms.get(i).draw(grid);
        }
        return grid;
    }

//    public static void main(String[] args) {
//        TERenderer ter = new TERenderer();
//        ter.initialize(WIDTH, HEIGHT);
//
//        TETile[][] world = new TETile[WIDTH][HEIGHT];
////        createWorld(world, 15);
//
//        ter.renderFrame(world);
//    }

}
