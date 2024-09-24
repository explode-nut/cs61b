import java.util.ArrayList;
import java.util.List;

public class HuffmanDecoder {
    public static void main(String[] args) {
        ObjectReader or = new ObjectReader(args[0]);

        Object o1 = or.readObject();
        Object o2 = or.readObject();

        BinaryTrie decoder = (BinaryTrie) o1;
        BitSequence original = (BitSequence) o2;
        List<Character> characterList = new ArrayList<>();

        while (original.length() != 0) {
            Match match = decoder.longestPrefixMatch(original);
            characterList.add(match.getSymbol());
            original = new BitSequence(original.toString().substring(match.getSequence().length()));
        }

        char[] chars = new char[characterList.size()];
        int i = 0;
        for (Character c : characterList) {
            chars[i] = c;
            i++;
        }

        FileUtils.writeCharArray(args[1], chars);
    }
}
