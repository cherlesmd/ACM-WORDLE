package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class WordleGUI extends JFrame {
    private JTextField[][] textRows;
    private int currRow;

    public WordleGUI() {
        setTitle("Tiles GUI");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);

        // Create a panel to hold the components
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 5));
        mainPanel.add(panel, BorderLayout.CENTER);
        // Create the input fields
        textRows = new JTextField[6][5];
        createInputField(panel);

        // Create the submit button
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitButtonClicked();
            }
        });
        mainPanel.add(submitButton, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(mainPanel);
        currRow = 0;
    }

    private void submitButtonClicked() {
        for(int i = 0; i <= 4; i++) {
            textRows[currRow][i].setEditable(false);
        }
        if(currRow == 5) {
            return;
        }
        textRows[currRow+1][0].requestFocus();
        currRow++;
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