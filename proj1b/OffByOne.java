public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')) &&
                ((y >= 'a' && y <= 'z') || (y >= 'A' && y <= 'Z'))) {
            return (x - y == 1) || (y - x == 1);
        } else {
            return false;
        }
    }

    /*if ((x >= 'a' && x <= 'z') || (x >= 'A' && x <= 'Z')) {
        return (x - y == 1) || (y - x == 1);
    } else {
        return false;
    }*/
}
