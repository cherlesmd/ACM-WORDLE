package gui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class State {
    Statistics stats;
    String correct_word;
    boolean[] char_stack;

    public State() {
        stats = loadStats();
        String[] words = {"SHAKE", "SHARE", "PANIC", "AMUSE", "SHADE"};
        int wIndex = (int)(Math.random() * words.length);
        correct_word = words[wIndex];
        char_stack = new boolean[]{false,false,false,false,false};
    }

    public Statistics getStatsObject() {
        return stats;
    }

    private Statistics loadStats() {
        Statistics result;
        ObjectInputStream statsLoader = null;

        try {
            // open reader for game save file
            statsLoader = new ObjectInputStream(new FileInputStream("./stats.dat"));

            // deserialize (decode) data from the save file as a stats object
            result = (Statistics)statsLoader.readObject();
        } catch (IOException ioEx) {
            // on errors, make default empty stats!
            result = new Statistics(0, 0);
            System.err.println(ioEx);
        } catch (ClassNotFoundException classEx) {
            result = new Statistics(0, 0);
            System.err.println(classEx);
        } finally {
            try {
                if (statsLoader != null) {
                    statsLoader.close();
                }
            } catch (IOException ioEx) {}
        }

        // get the stats
        return result;
    }

    public void saveStats() {
        ObjectOutputStream statsSaver = null;

        try {
            statsSaver = new ObjectOutputStream(new FileOutputStream("./stats.dat"));

            // save stats object to save file
            statsSaver.writeObject(stats);
        } catch (IOException ioEx) {
            System.err.println(ioEx);
        } finally {
            try {
                if (statsSaver != null) {
                    statsSaver.close();
                }
            } catch (IOException closeEx) {}
        }
    }

    public int checkCharacter(String c, int index) {
        char character = c.charAt(0);
        if(correct_word.charAt(index) == character) {
            char_stack[index] = true;
            return 1;
        }
        else if (contains(character) == true) {
            char_stack[index] = true;
            return 2;
        }
        return 0;
    }

    private boolean contains(char c) {
        for (int i = 0; i < correct_word.length(); i++) {
            if(c == correct_word.charAt(i) && char_stack[i] == false )
                return true;
        }
        return false;
    }

    public void resetStack() {
        for(int i = 0; i < char_stack.length; i ++) {
            char_stack[i] = false;
        }
    }

    public void gameOver(boolean won) {
        // update win or loss, save stats
        if (won == true) {
            stats.setRoundsWon(stats.getRoundsWon() + 1);
        } else {
            stats.setRoundsLost(stats.getRoundsLost() + 1);
        }

        // save updated stats
        saveStats();
    }
}