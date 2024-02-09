public class OffByN implements CharacterComparator {
    private int n;

    public OffByN(int n) {
        this.n = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')) &&
                ((y >= 'a' && y <= 'z') || (y >= 'A' && y <= 'Z'))) {
            return (x - y == n) || (y - x == n);
        } else {
            return false;
        }
    }
}
