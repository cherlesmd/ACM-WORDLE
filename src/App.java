/**
 * Main.java
 * @author Derek Tan, ??, ??
 * @version 0.0.1
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

class App extends JFrame implements ActionListener {
    public static final String APP_NAME = "Wordle";

    public static final int GAME_TRIES = 6;
    public static final int GAME_LETTER_COUNT = 5;
    public static final int TILE_GAP = 16;

    // TODO: add game classes for the board data, board logic, GUI objects, statistics class, and a game data loader / saver.

    private JMenuItem appExitItem;
    private JMenuItem statsShowItem;
    private JMenuItem statsClearItem;
    private JMenu appMenu;
    private JMenu statsMenu;
    private JMenuBar menubarComponent;

    private LetterTile[] tiles;

    public App() {
        super();

        appExitItem = new JMenuItem("Exit");
        statsShowItem = new JMenuItem("Show");
        statsClearItem = new JMenuItem("Clear");

        appMenu = new JMenu("App");
        statsMenu = new JMenu("Stats");

        menubarComponent = new JMenuBar();

        tiles = new LetterTile[GAME_TRIES * GAME_LETTER_COUNT];

        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new LetterTile();
        }
    }

    public void setupWindow(String windowTitle) {
        // Put menu controls with listeners set:
        appExitItem.addActionListener((e) -> {
            onExit();
        });

        statsShowItem.addActionListener((e) -> {
            // do nothing for now: implement later.
            displayStats();
        });

        statsClearItem.addActionListener((e) -> {
            // do nothing for now: implement later.
            deleteStats();
        });

        appMenu.add(appExitItem);
        statsMenu.add(statsShowItem);
        statsMenu.add(statsClearItem);

        menubarComponent.add(appMenu);
        menubarComponent.add(statsMenu);

        // Put board display:
        setJMenuBar(menubarComponent);
        setLayout(new GridLayout(GAME_TRIES, GAME_LETTER_COUNT, TILE_GAP, TILE_GAP));
        
        for (int i = 0; i < tiles.length; i++) {
            add(tiles[i]);
        }
        
        setTitle(windowTitle);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On close, this app should save game settings said in the README.

        int calcWidth = (LetterTile.TILE_SIDE_LEN * GAME_LETTER_COUNT) + TILE_GAP * (GAME_LETTER_COUNT - 1);
        int calcHeight = (LetterTile.TILE_SIDE_LEN * GAME_TRIES) + TILE_GAP * (GAME_TRIES - 1);

        setSize(calcWidth, calcHeight);
        setResizable(false);
        setVisible(true);
        repaint();
    }

    private void onExit() {
        // TODO: add logic to save game statistics. Use plain Java Serializer API.
        System.out.println("Exiting " + APP_NAME);
        System.exit(0);
    }

    private void displayStats() {
        // TODO: add logic to fetch stats data.
        JOptionPane.showMessageDialog(this, "Stats: ??", APP_NAME, JOptionPane.INFORMATION_MESSAGE, null);
    }

    private void deleteStats() {
        int clearOption = JOptionPane.showConfirmDialog(this, "This will clear your current stats. Are you sure?", APP_NAME, JOptionPane.YES_NO_CANCEL_OPTION);

        if (clearOption == JOptionPane.YES_OPTION) {
            // TODO: replace this print statement with logic to clear overall statistics.
            System.out.println("Dummy action: implement later.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Object target = e.getSource(); // event targeted component to check

        // TODO: implement checks for which controls are clicked or keypresses... keep track of whether the keypresses can be handled based on if the board is focused (clicked directly or not).

        System.out.println("Actions not implemented!");
    }

    public static void main(String[] args) {
        App wordleGame = new App();
        wordleGame.setupWindow(APP_NAME);
    }
}
