public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word){
        if (word.length() == 1 || word.isBlank()) {
            return true;
        }
        char first = word.charAt(0);
        char last = getLastChar(word);
        if (first == last) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() == 1 || word.isBlank()) {
            return false;
        }
        char first = word.charAt(0);
        char last = getLastChar(word);
        return cc.equalChars(first, last);
    }

    private char getLastChar(String word) {
        if (word.length() == 1) {
            return word.charAt(0);
        } else {
            return getLastChar(word.substring(1));
        }
    }
}
