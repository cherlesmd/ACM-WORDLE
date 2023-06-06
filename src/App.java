/**
 * Main.java
 * @author Derek Tan, ??, ??
 * @version 0.0.1
 */

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class App extends JFrame implements ActionListener {
    public static final String APP_NAME = "Wordle";
    public static final int WINDOW_WIDTH = 480;
    public static final int WINDOW_HEIGHT = 480;

    // TODO: add game classes for the board, GUI listeners, etc.

    public App(String windowTitle, int windowWidth, int windowHeight) {
        super();

        // TODO: initialize GUI and set listeners.

        setTitle(windowTitle);
        setSize(windowWidth, windowHeight);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On close, this app should save game settings said in the README.
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Object target = e.getSource();

        // TODO: implement checks for which controls are clicked or keypresses... keep track of whether the keypresses can be captured based on if the board is focused (clicked directly or not).

        System.out.println("Actions not implemented!");
    }

    public static void main(String[] args) {
        App wordleGame = new App(APP_NAME, WINDOW_WIDTH, WINDOW_HEIGHT);
    }
}
