package byow.Core;

/* written by cj */

import InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import edu.princeton.cs.introcs.StdDraw;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 50;

    private String INPUT;
    private InputSource inputSource;
    private long SEED;
    private Avatar avatar;
    private Game GAME;
    static Random RANDOM;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        inputSource = new KeyboardInputSource();
        StdDraw.enableDoubleBuffering();
        Menu.firstMenu(); // draw menu w the names n shit

        while (inputSource.possibleNextInput()) {
            char p = inputSource.getNextKey();
            if (p == 'N' || p == 'n') {
                System.out.println("new");
                Menu.moreMenu(StdDraw.WHITE, "What's your name? (no spaces)", "Press '!' when finished.");
                String name = typing();
                avatar = new Avatar(name);
                Menu.moreMenu(StdDraw.GREEN, "-NEW GAME-",
                        "Enter Seed #. Press 'S' when finished.");
                newWorld(); // ends with 'S'
            }
            if (p == 'L' || p == 'l') {
                System.out.println("load");
                Menu.moreMenu(StdDraw.GREEN, "-LOADING GAME-", "Back for more!");
                StdDraw.pause(1000);
                loadGame("lastgame");
            }
            if (p == 'Q' || p == 'q') {
                System.out.println("quit");
                //break;
                System.exit(0);
            }
            if (p == 'R' || p == 'r') {
                Menu.lore();
                char m = inputSource.getNextKey();
                if (m == 'm' || m == 'M') {
                    interactWithKeyboard();
                }
            }

            if (p == ':') {
                // if next key is q
                char k = inputSource.getNextKey();
                if (k == 'Q' ||  k == 'q') {
                    System.out.println("quit and save");
                    saveGame(GAME);
                    System.exit(0); //break;
                }
            }
        }
    }

    public String typing() {
        char x = inputSource.getNextKey();
        String y = "";
        while (x != '!') {
            y = y + x;
            Menu.moreMenu(y, StdDraw.WHITE, "What's your name? (no spaces)", "Press '!' when finished.");
            x = inputSource.getNextKey();
        }
        return y;
    }

    private void newWorld() {
        char x = inputSource.getNextKey();
        String y = "";
        while (x != 'S' & x != 's') {
            y = y + x;
            Menu.moreMenu(y);
            x = inputSource.getNextKey();
        }
        SEED = Long.parseLong(y);
        RANDOM = new Random(SEED);
        this.GAME = new Game(SEED, WIDTH, HEIGHT, avatar); // BUILDS GAME and HUD
    }

    private void saveGame(Game game) { // String name or Game game
        File gameFile = new File("./lastgame.txt");
        if (!gameFile.exists()) {
            try  {
                gameFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //String result = game.getName() + "," + game.getAvatar() game.getSeed() game.getWorld();
        String result = INPUT.substring(0, INPUT.length() - 2);
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

    private void loadGame(String name) {
        File gameFile = new File("./lastgame.txt");
        if (!gameFile.exists()) {
            // no game exists
            Menu.firstMenu();
        }
        try {
            Scanner sc = new Scanner(gameFile);
            String gameInfo = sc.nextLine();
            String[] gameStuff = gameInfo.split(". ");

            String sd = gameStuff[0];
            long seed = Integer.parseInt(sd);
            SEED = seed;
            int x = Integer.parseInt(gameStuff[3]);
            int y = Integer.parseInt(gameStuff[4]);

            int health = Integer.parseInt(gameStuff[1]);
            Avatar player = new Avatar(gameStuff[2], health);
            player.position(x, y);
            RANDOM = new Random(seed);
            this.GAME = new Game(SEED, WIDTH, HEIGHT, player);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        //interactWithKeyboard();
        //this.GAME = new Game(gameStuff[??], etc.); //BUILDS GAME
    }

    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */

    public TETile[][] interactWithInputString(String input) {
        // Fill out this method so that it run the engine using the input passed in as an
        // argument, and return a 2D tile representation of the world that would have been
        // drawn if the same inputs had been given to interactWithKeyboard(). See
        // proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.
        TETile[][] ok = new TETile[WIDTH][HEIGHT];
        return ok;
    }
}
