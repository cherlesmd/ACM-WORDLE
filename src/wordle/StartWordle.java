package wordle;

import java.awt.*;
import java.util.Random;
import java.util.Scanner;

public class StartWordle {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("WORDLE!");

        String[] words = {"SHAKE", "SHARE", "PANIC", "AMUSE", "SHADE"};

        int wIndex = (int)(Math.random() * words.length);
        String correct = words[wIndex];

        WordleState state = new WordleState(correct);

        DisplayWordle display = new DisplayWordle(state, input);
        while (!state.isGameOver()) {
            display.print();
            display.promptGuess();
        }
        display.print();
        if (state.didWin()) {
            System.out.println("Correct! You win!");
        } else {
            System.out.println("You did not guess correctly");
            System.out.println("Wrong! The correct word is " + state.getWord());
        }
        input.close();
    }
}