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
    private JPanel panel;

    public WordleGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setTitle("Tiles GUI");

        // Create a panel for letter tiles
        JPanel mainPanel = new JPanel(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new GridLayout(6, 5));
        mainPanel.add(panel, BorderLayout.CENTER);

        // Create the input fields for holding guess letters
        textRows = new JTextField[6][5];
        createInputField();

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
        if(currRow == 5 && total != 5) {
            JOptionPane.showMessageDialog(this, "Better luck next time : (", "MESSAGE", JOptionPane.PLAIN_MESSAGE);
            state.gameOver(false);
            resetGame();
            return;
        } else if (total == 5) {
            JOptionPane.showMessageDialog(this, "You Won : )", "MESSAGE", JOptionPane.PLAIN_MESSAGE);
            state.gameOver(true);
            resetGame();
            return;
        }

        textRows[currRow+1][0].requestFocus();
        currRow++;
        System.out.println(total);
    }

    private void createInputField() {
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

    public void resetGame() {
        resetInputFields();
        currRow = 0;
        state = new State();
    }

    private void resetInputFields() {
        for(int i = 0; i <= 5; i++) {
            for (int j = 0; j <= 4; j++) {
                textRows[i][j].setEditable(true);
                textRows[i][j].setText("");
                textRows[i][j].setBackground(Color.WHITE);
            }
        }
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