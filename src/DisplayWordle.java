import java.util.Scanner;

public class DisplayWordle {
    WordleState state;
    Scanner input;
    public static final String RESET = "\u001B[0m";
    public static final String BG_GREEN = "\u001B[32m";
    public static final String BG_YELLOW = "\u001B[33m";

    public DisplayWordle(WordleState state, Scanner input) {
        this.state = state;
        this.input = input;
    }

    // Prints out the board
    // Prints out previous guesses and remaining attempts
    public void print() {
        printGuesses();
        printBlanks();
        clear();
    }

    // Clears the terminal
    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // Prompts user to type in a guess from the terminal
    // Will only take in words of an appropriate length
    public String promptGuess() {

        while (true) {
            System.out.println(RESET + "Please guess. >" + RESET);

            String guess = input.nextLine().toUpperCase();
            if (guess.length() != state.getWord().length()) {
                System.out.println("Please enter word of length " + state.getWord().length());
            } else {
                state.guess(guess);
                return guess;
            }
        }
    }

    // Prints out previous guesses on the board with appropriate feedback
    // Prints out characters of the guesses seperated by "|"
    public void printGuesses() {

        StringBuilder b = new StringBuilder();
//        List<guess> = state.getGuesses();
        //

        for (String s : state.guesses) {

            for (int i = 0; i < s.length(); i++) {
                String answer = state.getWord();
//                String letter = s[i];
                char c = s.charAt(i);

                // letter is in correct place
                if (answer.charAt(i) == c) {
                //if(answer[i] == s[i]) {
                    b.append(BG_GREEN + c + RESET);
                    // letter is contained in answer
                }
                //else if (answer.contains(Character.toString(c))) {

                //Issues so far: it sometimes prints out 4 letters out of the 5 letter word
                //               (this issue is kind of random at times)
                //             -> second issue is it prints out "[0" at the end
                //             -> third issue is that depending on how the word is formatted it can highlight everything

                //this is what i added
                //we input the contains class in the else statement
                else if (contains(answer, c, i)) {
                    b.append(BG_YELLOW + c + RESET);
                }
                else {
                    b.append(c);
                }
//                b.append("|");
            }

            b.setLength((b.length() - 1));
            System.out.println(b.toString());
            b = new StringBuilder();

        //}
    }

       }

    // prints out spaces for remaining attempts
    public void printBlanks() {
        StringBuilder b = new StringBuilder();

        for (int i = 0; i < state.getAttemptsRemaining(); i++) {
            for (int j = 0; j < state.getWord().length(); j++) {
                b.append("_");
            }
            b.setLength(b.length() - 1);
            System.out.println(b.toString());
            b = new StringBuilder();
        }
    }

    //we add a string to check its length
    //we add the char c to compare it to the string
    //we add int i to check current positions of loop
    private boolean contains(String answer, char c, int i) {
        //loops through the correct word
        for(int index = 0; index < answer.length(); index++) {
            //checks if the "s" and "answer" have equal chars in the current position
            //and also checks if the index is not equal to the current position of the loop
            if(index != i && c == answer.charAt(i)) {
                //returns true if both are satisfied
                return true;
            }
        }
        //if not satisfied return false
        return false;

    }

}