public class State {
    String correct_word;
    boolean[] char_stack;
    public State() {
        String[] words = {"SHAKE", "SHARE", "PANIC", "AMUSE", "SHADE"};
        int wIndex = (int)(Math.random() * words.length);
        correct_word = words[wIndex];
        char_stack = new boolean[]{false,false,false,false,false};
    }

    public int checkCharacter(String c, int index) {
        char character = c.charAt(0);
        if(correct_word.charAt(index) == character) {
            System.out.println(character + " green");
            char_stack[index] = true;
            return 1;
        }
        else if (contains(character) == true) {
            System.out.println(character + " yellow");
            char_stack[index] = true;
            return 2;
        }
        System.out.println(character + " grey");
        return 0;
//        for(int i = 0; i < correct_word.length(); i++) {
//            if(correct_word.charAt(i) == character) {
//                System.out.println(character + " green");
//                char_stack[i] = true;
//                return 1;
//            }
//            else if (contains(character) == true) {
//                System.out.println(character + " yellow");
//                char_stack[i] = true;
//                return 2;
//            }
//            else {
//                System.out.println(character + " grey");
//                return 0;
//            }
//        }
//        return -1;
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
}