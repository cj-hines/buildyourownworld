package byow.Core;

/* written by cj */

import InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.introcs.StdDraw;

import java.awt.Font;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Game {
    private TERenderer ter;
    private String NAME;
    private int WIDTH = 80;
    private int HEIGHT = 50;
    private Avatar AVATAR;
    private long SEED;
    private Random RANDOM;
    private File gamesFolder;
    private WorldGenerator GRID;
    //List of old moves;
    //public HashMap<NPC, Position> ENTITIES;
    private TETile[][] finalworldframe = new TETile[WIDTH][HEIGHT];
    int numRooms = (int) (WIDTH * HEIGHT * .0025);

    public Game(long seed, int w, int h, Avatar avatar) {
        this.SEED = seed;
        this.RANDOM = new Random(SEED);
        this.WIDTH = w;
        this.HEIGHT = h;
        this.AVATAR = avatar;
        this.GRID = new WorldGenerator();

        hud();
        interactWithKeyboardMouse();
    }

    public void doorToWorld(long seed) {
        this.SEED = seed;
        new Game(SEED, WIDTH, HEIGHT, AVATAR);
    }

    public void interactWithKeyboardMouse() {
        InputSource inputSource = new KeyboardInputSource();

        while (inputSource.possibleNextInput()) {
            while (true) {
                while (!StdDraw.hasNextKeyTyped()) {
                    StdDraw.enableDoubleBuffering();
                    followMouse();
                }

                char p = inputSource.getNextKey();
                if (p == ':') {
                    // if next key is q
                    char k = inputSource.getNextKey();
                    if (k == 'Q' || k == 'q') {
                        System.out.println("quit and save");
                        saveGame();
                        System.exit(0); //break;
                    }
                }

                //UP
                if (p == 'W' || p == 'w') {
                    System.out.println("up");
                    if (!isWall(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() + 1)) {

                        if (isLocked(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() + 1)) {
                            if (AVATAR.hasKey() == false) {
                                Interactions i = new Interactions();
                                i.lockedDoor();
                                char l = inputSource.getNextKey();
                                while (l != '!') {
                                    i.lockedDoor();
                                    l = inputSource.getNextKey();
                                }
                            }
                            newhud();
                            StdDraw.show();
                        } else if (isUnlocked(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() + 1)) {
                            int L = RANDOM.nextInt(99999);
                            doorToWorld(L);
                        } else if (isFlower(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() + 1)) {
                            //map position x, y == FLOOR
                            //AVATAR.goUp();
                            newhud();
                            StdDraw.setPenColor(StdDraw.PINK);
                            Font front = new Font("Monaco", Font.BOLD, 11);
                            StdDraw.setFont(front);
                            StdDraw.text(WIDTH - 8, 2, "+1 FLOWER");
                            StdDraw.setPenColor(StdDraw.GREEN);
                            StdDraw.text(WIDTH - 8, 1, "Your character is happier.");
                            StdDraw.show();
                            StdDraw.pause(500);
                            // do something else to win?
                        } else if (isGrass(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() + 1)) {
                            //map position x, y == FLOOR
                            Interactions i = new Interactions();
                            i.grassAttack();
                            if (i.response() == false) {
                                AVATAR.loseHealth();
                                i.wrongAnswer();
                            } else {
                                i.correctAnswer();
                            }
                            //AVATAR.goUp();
                            newhud();
                            StdDraw.show();
                        } else if (isSand(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() + 1)) {
                            //map position x, y == FLOOR
                            //AVATAR.goUp();
                            AVATAR.loseHealth();
                            newhud();
                            StdDraw.setPenColor(StdDraw.RED);
                            Font front = new Font("Monaco", Font.BOLD, 11);
                            StdDraw.setFont(front);
                            StdDraw.text(WIDTH - 7, 2, "-1 HEALTH");
                            StdDraw.setPenColor(StdDraw.YELLOW);
                            StdDraw.text(WIDTH - 7, 1, "Sand is sharp :/");
                            StdDraw.show();
                            StdDraw.pause(500);
                            //newhud(Tileset.SAND);//sand in interactions
                            //StdDraw.show();
                        } else if (isTree(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() + 1)) {
                            //map position x, y == FLOOR
                            Interactions i = new Interactions();
                            i.treeMessage();
                            char e = inputSource.getNextKey();
                            String s = "";
                            if (e == '@') {
                                i.treeCarved(s);
                            }
                            while (e != '!') {
                                e = inputSource.getNextKey();
                                s = s + e;
                                i.treeCarved(s);
                            }
                            //AVATAR.goUp();
                            newhud();
                            StdDraw.show();
                        } else {
                            AVATAR.goUp();
                            newhud();
                            StdDraw.show();
                        }
                    }
                }

                //LEFT
                if (p == 'A' || p == 'a') {
                    System.out.println("left");
                    if (!isWall(AVATAR.getPosition().getX() - 1, AVATAR.getPosition().getY())) {

                        if (isLocked(AVATAR.getPosition().getX() - 1, AVATAR.getPosition().getY())) {
                            if (AVATAR.hasKey() == false) {
                                Interactions i = new Interactions();
                                i.lockedDoor();
                                char l = inputSource.getNextKey();
                                while (l != '!') {
                                    i.lockedDoor();
                                    l = inputSource.getNextKey();
                                }
                            }
                            newhud();
                            StdDraw.show();
                        } else if (isUnlocked(AVATAR.getPosition().getX() - 1, AVATAR.getPosition().getY())) {
                            int L = RANDOM.nextInt(99999);
                            doorToWorld(L);
                        } else if (isFlower(AVATAR.getPosition().getX() - 1, AVATAR.getPosition().getY())) {
                            //map position x, y == FLOOR
                            //AVATAR.goLeft();
                            newhud();
                            StdDraw.setPenColor(StdDraw.PINK);
                            Font front = new Font("Monaco", Font.BOLD, 11);
                            StdDraw.setFont(front);
                            StdDraw.text(WIDTH - 8, 2, "+1 FLOWER");
                            StdDraw.setPenColor(StdDraw.GREEN);
                            StdDraw.text(WIDTH - 8, 1, "Your character is happier.");
                            StdDraw.show();
                            StdDraw.pause(500);
                        } else if (isGrass(AVATAR.getPosition().getX() - 1, AVATAR.getPosition().getY())) {
                            //map position x, y == FLOOR
                            Interactions i = new Interactions();
                            i.grassAttack();
                            if (i.response() == false) {
                                AVATAR.loseHealth();
                                i.wrongAnswer();
                            } else {
                                i.correctAnswer();
                            }
                            //AVATAR.goLeft();
                            newhud();
                            StdDraw.show();
                        } else if (isSand(AVATAR.getPosition().getX() - 1, AVATAR.getPosition().getY())) {
                            //map position x, y == FLOOR
                            //AVATAR.goLeft();
                            AVATAR.loseHealth();
                            newhud();
                            StdDraw.setPenColor(StdDraw.RED);
                            Font front = new Font("Monaco", Font.BOLD, 11);
                            StdDraw.setFont(front);
                            StdDraw.text(WIDTH - 7, 2, "-1 HEALTH");
                            StdDraw.setPenColor(StdDraw.YELLOW);
                            StdDraw.text(WIDTH - 7, 1, "Sand is sharp :/");
                            StdDraw.show();
                            StdDraw.pause(500);
                        } else if (isTree(AVATAR.getPosition().getX() - 1, AVATAR.getPosition().getY())) {
                            //map position x, y == FLOOR
                            Interactions i = new Interactions();
                            i.treeMessage();
                            char e = inputSource.getNextKey();
                            String s = "";
                            if (e == '@') {
                                i.treeCarved(s);
                            }
                            while (e != '!') {
                                e = inputSource.getNextKey();
                                s = s + e;
                                i.treeCarved(s);
                            }
                            //AVATAR.goLeft();
                            newhud();
                            StdDraw.show();
                        } else {
                            AVATAR.goLeft();
                            newhud();
                            StdDraw.show();
                        }
                    }
                }

                //DOWN
                if (p == 'S' || p == 's') {
                    System.out.println("down");
                    if (!isWall(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() - 1)) {

                        if (isLocked(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() - 1)) {
                            if (AVATAR.hasKey() == false) {
                                Interactions i = new Interactions();
                                i.lockedDoor();
                                char l = inputSource.getNextKey();
                                while (l != '!') {
                                    i.lockedDoor();
                                    l = inputSource.getNextKey();
                                }
                            }
                            newhud();
                            StdDraw.show();
                        } else if (isUnlocked(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() - 1)) {
                            int L = RANDOM.nextInt(99999);
                            doorToWorld(L);
                        } else if (isFlower(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() - 1)) {
                            //map position x, y == FLOOR
                            //AVATAR.goDown();
                            newhud();
                            StdDraw.setPenColor(StdDraw.PINK);
                            Font front = new Font("Monaco", Font.BOLD, 11);
                            StdDraw.setFont(front);
                            StdDraw.text(WIDTH - 8, 2, "+1 FLOWER");
                            StdDraw.setPenColor(StdDraw.GREEN);
                            StdDraw.text(WIDTH - 8, 1, "Your character is happier.");
                            StdDraw.show();
                            StdDraw.pause(500);
                        } else if (isGrass(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() - 1)) {
                            //map position x, y == FLOOR
                            Interactions i = new Interactions();
                            i.grassAttack();
                            if (i.response() == false) {
                                AVATAR.loseHealth();
                                i.wrongAnswer();
                            } else {
                                i.correctAnswer();
                            }
                            //AVATAR.goDown();
                            newhud();
                            StdDraw.show();
                        } else if (isSand(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() - 1)) {
                            //map position x, y == FLOOR
                            //AVATAR.goDown();
                            AVATAR.loseHealth();
                            newhud();
                            StdDraw.setPenColor(StdDraw.RED);
                            Font front = new Font("Monaco", Font.BOLD, 11);
                            StdDraw.setFont(front);
                            StdDraw.text(WIDTH - 7, 2, "-1 HEALTH");
                            StdDraw.setPenColor(StdDraw.YELLOW);
                            StdDraw.text(WIDTH - 7, 1, "Sand is sharp :/");
                            StdDraw.show();
                            StdDraw.pause(500);
                        } else if (isTree(AVATAR.getPosition().getX(), AVATAR.getPosition().getY() - 1)) {
                            //map position x, y == FLOOR
                            Interactions i = new Interactions();
                            i.treeMessage();
                            char e = inputSource.getNextKey();
                            String s = "";
                            if (e == '@') {
                                i.treeCarved(s);
                            }
                            while (e != '!') {
                                e = inputSource.getNextKey();
                                s = s + e;
                                i.treeCarved(s);
                            }
                            //AVATAR.goDown();
                            newhud();
                            StdDraw.show();
                        } else {
                            AVATAR.goDown();
                            newhud();
                            StdDraw.show();
                        }
                    }
                }

                //RIGHT
                if (p == 'D' || p == 'd') {
                    System.out.println("right");
                    if (!isWall(AVATAR.getPosition().getX() + 1, AVATAR.getPosition().getY())) {

                        if (isLocked(AVATAR.getPosition().getX() + 1, AVATAR.getPosition().getY())) {
                            if (AVATAR.hasKey() == false) {
                                Interactions i = new Interactions();
                                i.lockedDoor();
                                char l = inputSource.getNextKey();
                                while (l != '!') {
                                    i.lockedDoor();
                                    l = inputSource.getNextKey();
                                }
                            }
                            newhud();
                            StdDraw.show();
                        } else if (isUnlocked(AVATAR.getPosition().getX() + 1, AVATAR.getPosition().getY())) {
                            int L = RANDOM.nextInt(99999);
                            doorToWorld(L);
                        } else if (isFlower(AVATAR.getPosition().getX() + 1, AVATAR.getPosition().getY())) {
                            //map position x, y == FLOOR
                            //AVATAR.goRight();
                            newhud();
                            StdDraw.setPenColor(StdDraw.PINK);
                            Font front = new Font("Monaco", Font.BOLD, 11);
                            StdDraw.setFont(front);
                            StdDraw.text(WIDTH - 8, 2, "+1 FLOWER");
                            StdDraw.setPenColor(StdDraw.GREEN);
                            StdDraw.text(WIDTH - 8, 1, "Your character is happier.");
                            StdDraw.show();
                            StdDraw.pause(500);
                        } else if (isGrass(AVATAR.getPosition().getX() + 1, AVATAR.getPosition().getY())) {
                            //map position x, y == FLOOR
                            Interactions i = new Interactions();
                            i.grassAttack();
                            if (i.response() == false) {
                                AVATAR.loseHealth();
                                i.wrongAnswer();
                            } else {
                                i.correctAnswer();
                            }
                            //AVATAR.goRight();
                            newhud();
                            StdDraw.show();
                        } else if (isSand(AVATAR.getPosition().getX() + 1, AVATAR.getPosition().getY())) {
                            //map position x, y == FLOOR
                            //AVATAR.goRight();
                            AVATAR.loseHealth();
                            newhud();
                            StdDraw.setPenColor(StdDraw.RED);
                            Font front = new Font("Monaco", Font.BOLD, 11);
                            StdDraw.setFont(front);
                            StdDraw.text(WIDTH - 7, 2, "-1 HEALTH");
                            StdDraw.setPenColor(StdDraw.YELLOW);
                            StdDraw.text(WIDTH - 7, 1, "Sand is sharp :/");
                            StdDraw.show();
                            StdDraw.pause(500);
                        } else if (isTree(AVATAR.getPosition().getX() + 1, AVATAR.getPosition().getY())) {
                            //map position x, y == FLOOR
                            Interactions i = new Interactions();
                            i.treeMessage();
                            char e = inputSource.getNextKey();
                            String s = "";
                            if (e == '@') {
                                i.treeCarved(s);
                            }
                            while (e != '!') {
                                e = inputSource.getNextKey();
                                s = s + e;
                                i.treeCarved(s);
                            }
                            //AVATAR.goRight();
                            newhud();
                            StdDraw.show();
                        } else {
                            AVATAR.goRight();
                            newhud();
                            StdDraw.show();
                        }
                    }
                }
            }
        }
    }

    public boolean isFloor(int x, int y) {
        return finalworldframe[x][y] == Tileset.FLOOR;
    }

    public boolean isWall(int x, int y) {
        return finalworldframe[x][y] == Tileset.WALL;
    }

    public boolean isLocked(int x, int y) {
        return finalworldframe[x][y] == Tileset.LOCKED_DOOR;
    }

    public boolean isUnlocked(int x, int y) {
        return finalworldframe[x][y] == Tileset.UNLOCKED_DOOR;
    }

    public boolean isFlower(int x, int y) {
        return finalworldframe[x][y] == Tileset.FLOWER;
    }

    public boolean isGrass(int x, int y) {
        return finalworldframe[x][y] == Tileset.GRASS;
    }

    public boolean isSand(int x, int y) {
        return finalworldframe[x][y] == Tileset.SAND;
    }

    public boolean isTree(int x, int y) {
        return finalworldframe[x][y] == Tileset.TREE;
    }

    public void hud() {
        ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        Font font = new Font("Monaco", Font.BOLD, 13);
        StdDraw.setFont(font);
        StdDraw.text((WIDTH / 2), HEIGHT - 1, "CS61B: The Game");
        StdDraw.text((WIDTH / 2), 1, "Player: " + AVATAR.getName());
        StdDraw.text(WIDTH - 8, HEIGHT - 1, "Health: " + AVATAR.getHealth() + "/5 | Luck: " + AVATAR.getLuck() + "/5");
        StdDraw.text(4, HEIGHT - 1, "Seed: " + SEED);
        renderWorld();
        StdDraw.show();
    }

    public void newhud() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
        Font font = new Font("Monaco", Font.BOLD, 13);
        StdDraw.setFont(font);
        StdDraw.text((WIDTH / 2), HEIGHT - 1, "CS61B: The Game");
        StdDraw.text((WIDTH / 2), 1, "Player: " + AVATAR.getName());
        StdDraw.text(WIDTH - 4, HEIGHT - 1, "Health: " + AVATAR.getHealth() + "/5");
        StdDraw.text(4, HEIGHT - 1, "Seed: " + SEED);
        finalworldframe[AVATAR.getPosition().getX()][AVATAR.getPosition().getY()] = Tileset.AVATAR;
        finalworldframe[AVATAR.getOldX()][AVATAR.getOldY()] = Tileset.FLOOR;
        ter.renderFrame(finalworldframe);
    }

    public void renderWorld() {
        ter = new TERenderer();
        finalworldframe = GRID.createWorld(finalworldframe, RANDOM.nextInt(numRooms) + 2);
        initializeAvatar(AVATAR.getPosition().getX(), AVATAR.getPosition().getY());
        for (int x = 0; x < WIDTH; x++) {
            if (finalworldframe[x][2] != Tileset.WALL & finalworldframe[x][2] != Tileset.NOTHING) {
                finalworldframe[x][2] = Tileset.WALL;
            }
        }
        for (int y = 0; y < WIDTH; y++) {
            if (finalworldframe[y][HEIGHT - 3] != Tileset.WALL & finalworldframe[y][HEIGHT - 3] != Tileset.NOTHING) {
                finalworldframe[y][HEIGHT - 3] = Tileset.WALL;
            }
        }
        ter.renderFrame(finalworldframe);
    }

    public void initializeAvatar(int x, int y) {
        if (x <= 1 || x >= WIDTH - 1 || y <= 3 || y >= HEIGHT - 3) {
            int ex = RANDOM.nextInt(WIDTH);
            int why = RANDOM.nextInt(HEIGHT);
            initializeAvatar(ex, why);
        } else if (finalworldframe[x][y] == Tileset.FLOOR) {
            System.out.println("X:" + x);
            System.out.println("Y:" + y);
            finalworldframe[x][y] = Tileset.AVATAR;
            AVATAR.position(x, y);
        } else {
            int ex = RANDOM.nextInt(WIDTH - 1);
            int why = RANDOM.nextInt(HEIGHT - 3);
            initializeAvatar(ex, why);
        }
    }

    private void saveGame() { // SEED, location of avatar, name
        File gameFile = new File("./lastgame.txt");
        if (!gameFile.exists()) {
            try  {
                gameFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String result = gameAsString();
        System.out.println(result);
        try {
            FileWriter gameWriter = new FileWriter("lastgame.txt");
            gameWriter.write(result);
            gameWriter.close();
            System.out.println("Game saved.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving.");
            e.printStackTrace();
        }
    }

    private String gameAsString() {
        String seed = Long.toString(SEED);
        String avatar = AVATAR.getName();
        String avatarHealth = Integer.toString(AVATAR.getHealth());
        String avatarX = Integer.toString(AVATAR.getPosition().getX());
        String avatarY = Integer.toString(AVATAR.getPosition().getY());
        return seed + ". " + avatarHealth + ". " + avatar + ". " + avatarX + ". " + avatarY;
    }


    public void followMouse() {
        // mouse location
        int mousex = (int) StdDraw.mouseX();
        int mousey = (int) StdDraw.mouseY();
        if (mousex < WIDTH & mousey < HEIGHT - 2) {
            if (finalworldframe[mousex][mousey] == Tileset.NOTHING) {
                newhud();
                StdDraw.show();
            }
            if (finalworldframe[mousex][mousey] == Tileset.WALL) {
                newhud();

                StdDraw.setPenColor(StdDraw.WHITE);
                Font front = new Font("Monaco", Font.BOLD, 11);
                StdDraw.setFont(front);

                StdDraw.text(10, 2, "WALL: This is a wall. Ghosts can walk through");
                StdDraw.text(10, 1, "walls. But sadly, you are not a ghost.");
                StdDraw.show();
            } else if (finalworldframe[(int) mousex][mousey] == Tileset.FLOOR) {
                newhud();

                StdDraw.setPenColor(StdDraw.WHITE);
                Font front = new Font("Monaco", Font.BOLD, 11);
                StdDraw.setFont(front);

                StdDraw.text(10, 2, "FLOOR: This is the floor you walk on.");
                StdDraw.text(10, 1, "Believe it or not, it's hardwood flooring.");
                StdDraw.show();
            } else if (finalworldframe[(int) mousex][mousey] == Tileset.AVATAR) {
                newhud();

                StdDraw.setPenColor(StdDraw.WHITE);
                Font front = new Font("Monaco", Font.BOLD, 11);
                StdDraw.setFont(front);

                StdDraw.text(10, 2, AVATAR.getName() + ": This is you. You are an aimless");
                StdDraw.text(10, 1, "@ symbol in a fake, poorly coded gameworld.");
                StdDraw.show();
            }
            else if (finalworldframe[(int) mousex][mousey] == Tileset.FLOWER) {
                newhud();

                StdDraw.setPenColor(StdDraw.WHITE);
                Font front = new Font("Monaco", Font.BOLD, 11);
                StdDraw.setFont(front);

                StdDraw.text(10, 2, "FLOWER: You can pick these pretty flowers, ");
                StdDraw.text(10, 1, "so you're not alone on your journey.");
                StdDraw.show();
            }
            else if (finalworldframe[(int) mousex][mousey] == Tileset.SAND) {
                newhud();

                StdDraw.setPenColor(StdDraw.WHITE);
                Font front = new Font("Monaco", Font.BOLD, 11);
                StdDraw.setFont(front);

                StdDraw.text(10, 2, "SAND PIT: Be careful! There's sand here.");
                StdDraw.text(10, 1, "You might get hurt walking in sand.");
                StdDraw.show();
            }
            else if (finalworldframe[(int) mousex][mousey] == Tileset.TREE) {
                newhud();

                StdDraw.setPenColor(StdDraw.WHITE);
                Font front = new Font("Monaco", Font.BOLD, 11);
                StdDraw.setFont(front);

                StdDraw.text(10, 2, "TREE: Trees might have carved messages.");
                StdDraw.text(10, 1, "Tree sap is delicious but full of carbs.");
                StdDraw.show();
            }
            else if (finalworldframe[(int) mousex][mousey] == Tileset.GRASS) {
                newhud();

                StdDraw.setPenColor(StdDraw.WHITE);
                Font front = new Font("Monaco", Font.BOLD, 11);
                StdDraw.setFont(front);

                StdDraw.text(10, 2, "GRASS: This grass is golf turf. Who knows");
                StdDraw.text(10, 1, "what's hiding in grass.. watch out!");
                StdDraw.show();
            }
            else if (finalworldframe[(int) mousex][mousey] == Tileset.LOCKED_DOOR) {
                newhud();

                StdDraw.setPenColor(StdDraw.WHITE);
                Font front = new Font("Monaco", Font.BOLD, 11);
                StdDraw.setFont(front);

                StdDraw.text(11, 2, "LOCKED DOOR: You need a key to open this door.");
                StdDraw.text(11, 1, "Walk around, maybe you'll find something...");
                StdDraw.show();
            }
            else if (finalworldframe[(int) mousex][mousey] == Tileset.UNLOCKED_DOOR) {
                newhud();

                StdDraw.setPenColor(StdDraw.WHITE);
                Font front = new Font("Monaco", Font.BOLD, 11);
                StdDraw.setFont(front);

                StdDraw.text(10, 2, "OPEN DOOR: This door does not need a key.");
                StdDraw.text(10, 1, "Go through! Life is like an open door.");
                StdDraw.show();
            }
        }
    }
}
