package wordle;

import java.util.ArrayList;
import java.util.List;

public class WordleState {

    private int rowLength;
    private int attemptsRemaining;
    private int attempts;
    private String word;
    List<String> guesses;

    public WordleState(String word) {
        this.word = word;
        rowLength = this.word.length();
        attempts = 6;
        attemptsRemaining = attempts;
        guesses = new ArrayList<String>();
        System.out.println(word);
    }

    public int getRowLength() {
        return rowLength;
    }

    public void setRowLength(int rowLength) {
        this.rowLength = rowLength;
    }

    public int getAttemptsRemaining() {
        return attemptsRemaining;
    }

    public void setAttemptsRemaining(int attemptsRemaining) {
        this.attemptsRemaining = attemptsRemaining;
    }

    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getGuesses() {
        return guesses;
    }

    public void setGuesses(List<String> guesses) {
        this.guesses = guesses;
    }

    public boolean isGameOver () {
        return attemptsRemaining == 0 || didWin();
    }

    public boolean didWin() {
        return guesses.contains(word);
    }

    public void guess(String str) {
        if (str.length() == word.length()) {
            guesses.add(str);
            attemptsRemaining--;
        }
    }
}