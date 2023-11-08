package byow.Core;

//import edu.princeton.cs.introcs.StdDraw;
//import java.awt.*;

/* written by cj */


import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;

public class Menu {

    public static void firstMenu() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(WIDTH * 16, HEIGHT * 16);
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.PINK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.6, "CS61B: The Game");
        StdDraw.text(0.5, 0.45, "New Game (N)");
        StdDraw.text(0.5, 0.4, "Load Game (L)");
        StdDraw.text(0.5, 0.35, "Quit (Q)");
        StdDraw.text(0.5, 0.3, "Read Lore (R)");
        StdDraw.show();
    }

    public static void moreMenu(String beingtyped) {
        moreMenu(beingtyped, StdDraw.GREEN, "-NEW GAME-", "Enter Seed #. Press 'S' when finished.");
    }

    public static void moreMenu(Color color, String message, String message2) {
        moreMenu("", color, message, message2);
    }

    public static void moreMenu(String beingtyped, Color color, String message, String message2) {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(color);
        Font font = new Font("Monaco", Font.BOLD, 20);
        Font font2 = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.9, "CS61B: The Game");
        StdDraw.setFont(font2);
        StdDraw.text(0.5, 0.55, message);
        StdDraw.text(0.5, 0.45, message2);
        StdDraw.text(0.5, 0.35, beingtyped);
        StdDraw.show();
    }

    public static void moMenu(Color color, String message, String message2) {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(color);
        Font font = new Font("Monaco", Font.BOLD, 20);
        Font font2 = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 3, "CS61B: The Game");
        StdDraw.setFont(font2);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, message);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) - 2, message2);
        StdDraw.show();
    }

    public static void lore() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.GRAY);
        StdDraw.setPenColor(StdDraw.WHITE);

        Font font = new Font("Arial", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(0.5, 0.92, "Hello, person in front of the screen! Welcome to CS61B: The Game!");
        StdDraw.text(0.5, 0.82, "You are an @ symbol in a randomly generated world in a game created by CJ");
        StdDraw.text(0.5, 0.72, "and Costas, who both have below average grades in this class rn (so do ur");
        StdDraw.text(0.5, 0.62, "best but if u lose blame it on the code). You don't know how you got here, but");
        StdDraw.text(0.5, 0.52, "you know that you just want to get out of 61B. One thing is for sure: there seems");
        StdDraw.text(0.5, 0.42, "to be no escape... or is there?? In this world, you communicate via carvings on");
        StdDraw.text(0.5, 0.32, "trees: you carve a tree and it will carve a tree in someone else's game. Beware of");
        StdDraw.text(0.5, 0.22, "violent golfers and sand! Also it's totally up to you, but pick some flowers, they");
        StdDraw.text(0.5, 0.12, "smell good! If your health hits 0, you will die. This is an allegory for capitalism.");

        StdDraw.setPenColor(StdDraw.PINK);
        Font fonty = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(fonty);
        StdDraw.text(0.5, 0.05, "Press 'm' to return to main menu");
        StdDraw.show();
    }
}
