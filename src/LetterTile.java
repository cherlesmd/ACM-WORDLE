/**
 * LetterTile.java
 * @apiNote Encapsulates a Wordle letter tile within a row for a guessed word. Contains basic logic to update display of letter and color, but the letter checks should be done elsewhere.
 * @author Derek Tan
 */

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LetterTile extends JPanel {
    public static final int TILE_SIDE_LEN = 96;

    /**
     * Defines status codes for the letter tile. Corresponds to display colors.
     */
    public enum LetterTileStatus {
        MISSED,  // by gray color
        PRESENT, // by yellow color
        CORRECT, // by green color
    }

    private static final Color[] BG_COLOR_TABLE = {
        Color.DARK_GRAY, // for missed
        Color.YELLOW,    // for present
        Color.GREEN      // for correct
    };

    private LetterTileStatus tileStatus;
    private JLabel textElement;

    public LetterTile () {
        tileStatus = LetterTileStatus.MISSED;
        textElement = new JLabel("z");
        textElement.setAlignmentX(CENTER_ALIGNMENT);
        textElement.setAlignmentY(CENTER_ALIGNMENT);
        textElement.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        setLayout(new FlowLayout());
        add(textElement);

        setVisible(true);
        updateColoring(tileStatus);
    }

    public LetterTileStatus getStatus() {
        return tileStatus;
    }

    public void setStatus(LetterTileStatus status) {
        tileStatus = status;
        updateColoring(tileStatus);
    }

    public String getLetter() {
        return textElement.getText();
    }
    
    public void setLetter(String letter) {
        textElement.setText(letter);
    }

    private void updateColoring(LetterTileStatus status) {
        int statusCode = status.ordinal();  // get status as index for status color
    
        setBackground(BG_COLOR_TABLE[statusCode]);  // set color from lookup by index

        textElement.setForeground(Color.LIGHT_GRAY);  // set text color that contrasts with all 3 possible colors

        repaint();
    }
}
