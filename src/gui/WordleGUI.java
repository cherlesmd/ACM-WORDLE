package gui; // for VSCode package resolution

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WordleGUI extends JFrame {
    private JTextField[][] textRows;
    private int currRow;
    private State state;

    public WordleGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setTitle("Tiles GUI");

        // Create a panel for letter tiles
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 5));
        mainPanel.add(panel, BorderLayout.CENTER);

        // Create the input fields for holding guess letters
        textRows = new JTextField[6][5];
        createInputField(panel);

        // Create the submit button for guesses
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonClicked();
            }
        });
        mainPanel.add(submitButton, BorderLayout.SOUTH);

        // Attach the GUI to the window frame
        add(mainPanel);
        currRow = 0;
        state = new State();
    }

    private void submitButtonClicked() {
        int total = 0;
        for(int i = 0; i <= 4; i++) {
            int correct = state.checkCharacter(textRows[currRow][i].getText(), i);
            if(correct == 1) {
                textRows[currRow][i].setBackground(Color.GREEN);
                total += 1;
            } else if (correct == 2) {
                textRows[currRow][i].setBackground(Color.YELLOW);
            }
            textRows[currRow][i].setEditable(false);
            state.resetStack();
        }
        if(currRow == 5 || total == 5) {
            return;
        }

        textRows[currRow+1][0].requestFocus();
        currRow++;
        System.out.println(total);
    }

    private void createInputField(JPanel panel) {
        for(int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 4; j++) {
                JTextField inputField = new JTextField();
                inputField.setHorizontalAlignment(JTextField.CENTER);
                inputField.setFont(new Font("Arial", Font.BOLD, 35));
                inputField.addKeyListener(new TileKeyListener(i, j));
                panel.add(inputField);
                textRows[i][j] = inputField;
            }
        }
    }

    public void gameOver(boolean won) {
//        disable all rows, register and save if win or loss, save stats
    }

    private class TileKeyListener implements KeyListener {
        private int tileRow;
        private int tileColumn;

        public TileKeyListener(int tileRow, int tileColumn) {
            this.tileRow = tileRow;
            this.tileColumn = tileColumn;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int pos = textRows[tileRow][tileColumn].getCaretPosition();
            textRows[tileRow][tileColumn].setText(textRows[tileRow][tileColumn].getText().toUpperCase());
            textRows[tileRow][tileColumn].setCaretPosition(pos);

            // Accept letter keys only for keeping input appropriate
            char c = e.getKeyChar();
            if (Character.isLetter(c)) {
                // Move focus to the next input field
                if (tileColumn < 4) {
                    textRows[tileRow][tileColumn + 1].requestFocus();
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WordleGUI tilesGUI = new WordleGUI();
            tilesGUI.setVisible(true);
        });
    }
}