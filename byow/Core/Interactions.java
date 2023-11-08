package byow.Core;

/* written by cj */

import InputDemo.InputSource;
import byow.InputDemo.KeyboardInputSource;
import edu.princeton.cs.introcs.StdDraw;
import edu.princeton.cs.introcs.StdRandom;

import java.awt.*;

import static byow.Core.Engine.HEIGHT;
import static byow.Core.Engine.WIDTH;
import static edu.princeton.cs.introcs.StdRandom.uniform;

public class Interactions {
    private static String[] QUESTIONS = {"What is the capital of Lithuania?", "What?", "How lon England's shortest-" +
            "reigning monarch's reign?", "What is the capital of Togo?", "What is snow in Spanish?",
            "Who was the first president of Colombia?"};
    private static String[] ANSWERS = {"Vilnius", "correct", "12 days", "Lome", "Nieve", " Simón Bolívar"};
    private static String[] WRONG1 = {"Corsica", "wrong", "6 weeks", "Harare", "Frio", "José de la Riva Agüero"};
    private static String[] WRONG2 = {"Ljubljana", "wrong", "95 days", "Abulug", "Congelador", "Mateo de Toro y Zambrano"};

    private static String[] MESSAGES = {"Help! I'm trapped in the CS major!", "The key was inside you all along.",
            "AUTOMATIC LOSE 4 HEALTH! haha jk", "Follow @cj.hines on instagram.",
            "This tree is dying :(", "Don't find the key! It's all a game!", "Lady Jane Grey ruled England for 12 days",
            "Do you wanna build a nieveman?"};

    private int CORRECT;
    private String p1;
    private String p2;
    private String p3;

    public void lockedDoor() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.ORANGE);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 5, "Oh no! This door is locked!");
        StdDraw.text(WIDTH / 2, (HEIGHT / 2), ":( You don't have a key!");
        Font font2 = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font2);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) - 5, "You can purchase a key for $5K USD by venmoing @cjneedsmoney");
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) - 8, "OR press '!' to return to the game to ~search~ for a key");
        StdDraw.show();
    }

    public void treeMessage() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.PRINCETON_ORANGE);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        int x = StdRandom.uniform(MESSAGES.length);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 10, "There's a message for you on this tree:");

        Font carving = new Font("SansSerif", Font.PLAIN, 55);
        StdDraw.setFont(carving);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2), MESSAGES[x]);

        Font font2 = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font2);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) - 10, "Carve a new message for the next adventurer!");
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) - 15, "Press '@' to start and '!' when you're done.");
        StdDraw.show();
    }

    public void treeCarved(String message) {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.PRINCETON_ORANGE);
        StdDraw.setPenColor(StdDraw.GREEN);
        Font font = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, HEIGHT / 2, message);
        StdDraw.show();
    }

    public void sandTrap() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.GREEN);
        //StdDraw.filledSquare();
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 7, "You've been spotted by a wild golfer in the grass!");
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 5, "Answer their question correctly or they will attack you.");
        int x = StdRandom.uniform(QUESTIONS.length);

        StdDraw.text(WIDTH / 2, (HEIGHT / 2), QUESTIONS[x]);
        Font font2 = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font2);

        setAnswers(x);

        StdDraw.text(WIDTH / 2, 20, "(Press 1) " + p1);
        StdDraw.text(WIDTH / 2, 15, "(Press 2) " + p2);
        StdDraw.text(WIDTH / 2, 10, "(Press 3) " + p3);
        StdDraw.show();
    }

    public void grassAttack() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.GREEN);
        //StdDraw.filledSquare();
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 25);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 7, "You've been spotted by a wild golfer in the grass!");

        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 5, "Answer their question correctly or they will attack you.");
        int x = StdRandom.uniform(QUESTIONS.length);
        Font fant = new Font("Monaco", Font.BOLD, 35);
        StdDraw.setFont(fant);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2), QUESTIONS[x]);
        Font font2 = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(font2);

        setAnswers(x);

        StdDraw.text(WIDTH / 2, 20, "(Press 1) " + p1);
        StdDraw.text(WIDTH / 2, 15, "(Press 2) " + p2);
        StdDraw.text(WIDTH / 2, 10, "(Press 3) " + p3);
        StdDraw.show();
    }

    private void setAnswers(int x) {
        CORRECT = StdRandom.uniform(1, 4);
        if (CORRECT == 1) {
            p1 = ANSWERS[x];
            p2 = WRONG1[x];
            p3 = WRONG2[x];
        } else if (CORRECT == 2) {
            p1 = WRONG1[x];
            p2 = ANSWERS[x];
            p3 = WRONG2[x];
        } else if (CORRECT == 3) {
            p1 = WRONG1[x];
            p2 = WRONG2[x];
            p3 = ANSWERS[x];
        }
    }

    public boolean response() {
        InputSource ps = new KeyboardInputSource();
        char num = ps.getNextKey();
        boolean x = false;
        if (num == '1') {
            x = (CORRECT == 1);
        } else if (num == '2') {
            x = (CORRECT == 2);
        } else if (num == '3') {
            x = (CORRECT == 3);
        }
        return x;
    }

    public void wrongAnswer() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BOOK_RED);
        StdDraw.setPenColor(StdDraw.WHITE);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 2, "Wrong answer :/ maybe study more.");
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) - 2, "You've lost 1 health.");
        StdDraw.show();
        StdDraw.pause(2000);
    }

    public void correctAnswer() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.GREEN);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 2, "Correct answer!!!");
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) - 2, "ur a nerd tho why did u know that :/");
        StdDraw.show();
        StdDraw.pause(2000);
    }

    public void gameOver() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.BOOK_RED);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 2, "**************** GAME OVER ****************");
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) - 2, "You're out of health.");
        StdDraw.show();
        StdDraw.pause(3000);
        Engine en = new Engine();
        en.interactWithKeyboard();
    }

    public void gameWin() {
        StdDraw.enableDoubleBuffering();
        StdDraw.clear(StdDraw.GREEN);
        StdDraw.setPenColor(StdDraw.BLACK);
        Font font = new Font("Monaco", Font.BOLD, 50);
        StdDraw.setFont(font);
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) + 2, "**************** YOU WIN ****************");
        StdDraw.text(WIDTH / 2, (HEIGHT / 2) - 2, "You found the key and escaped!");
        StdDraw.show();
        StdDraw.pause(3000);
        Engine en = new Engine();
        en.interactWithKeyboard();
    }
}
