import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> wordFrequency = new HashMap<>();
        for (char i : inputSymbols) {
            if (wordFrequency.containsKey(i)) {
                wordFrequency.put(i, wordFrequency.get(i) + 1);
            } else {
                wordFrequency.put(i, 1);
            }
        }
        return wordFrequency;
    }

    public static void main(String[] args) {
        // 1: Read the file as 8 bit symbols.
        char[] input = FileUtils.readFile(args[0]);
        // 2: Build frequency table.
        Map<Character, Integer> frequencyTable = buildFrequencyTable(input);
        // 3: Use frequency table to construct a binary decoding trie.
        BinaryTrie binaryTrie = new BinaryTrie(frequencyTable);
        // 4: Write the binary decoding trie to the .huf file.
        // 5: (optional: write the number of symbols to the .huf file)
        ObjectWriter ow = new ObjectWriter(args[0] + ".hug");
        ow.writeObject(binaryTrie);
//        ow.writeObject("the number of symbols is " + frequencyTable.size() + "\n");
        // 6: Use binary trie to create lookup table for encoding.
        Map<Character, BitSequence> sequenceMap = binaryTrie.buildLookupTable();
        // 7: Create a list of bitsequences.
        List<BitSequence> list = new ArrayList<>();
        /* 8: For each 8 bit symbol:
              Lookup that symbol in the lookup table.
              Add the appropriate bit sequence to the list of bitsequences.*/
        for (char c : input) {
            list.add(sequenceMap.get(c));
        }
        // 9: Assemble all bit sequences into one huge bit sequence.
        BitSequence assembled = BitSequence.assemble(list);
        // 10: Write the huge bit sequence to the .huf file.
        ow.writeObject(assembled);
    }
}
