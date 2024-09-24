import edu.princeton.cs.algs4.MinPQ;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {
    private final Node root; // 根节点
    private final Map<Character, BitSequence> result; // buildLookupTable返回结果

    private class Node implements Comparable<Node>, Serializable {
        private final char ch;
        private final int frequency;
        private final Node left, right;

        public Node(char ch, int frequency, Node left, Node right) {
            this.ch = ch;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }

        public boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        @Override
        public int compareTo(Node o) {
            return this.frequency - o.frequency;
        }
    }

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        MinPQ<Node> pq = new MinPQ<>();
        result = new HashMap<>();
        frequencyTable.forEach((k, v) -> {
            pq.insert(new Node(k, v, null, null));
        });

        while (pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.frequency + right.frequency, left, right);
            pq.insert(parent);
        }
        root = pq.delMin();
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        Node dummy = root;
        BitSequence resultSequence = new BitSequence();
        for (int i = 0; i < querySequence.length(); i++) {
            if (dummy.isLeaf()) {
                return new Match(resultSequence, dummy.ch);
            }
            int bit = querySequence.bitAt(i);
            if (bit == 0) {
                dummy = dummy.left;
            } else if (bit == 1) {
                dummy = dummy.right;
            } else {
                return null;
            }
            resultSequence = resultSequence.appended(bit);
        }
        if (dummy.isLeaf()) {
            return new Match(resultSequence, dummy.ch);
        }
        return null;
    }

    public Map<Character, BitSequence> buildLookupTable() {
        Node dummy = root;
        dfs(dummy, null);
        return result;
    }

    // 遍历二叉树所有叶子节点
    private void dfs(Node dummy, BitSequence bitSequence) {
        if (dummy.isLeaf()) {
            result.put(dummy.ch, bitSequence);
            return;
        }
        if (bitSequence == null) {
            dfs(dummy.left, new BitSequence("0"));
            dfs(dummy.right, new BitSequence("1"));
        } else {
            dfs(dummy.left, new BitSequence(bitSequence.toString() + "0"));
            dfs(dummy.right, new BitSequence(bitSequence.toString() + "1"));
        }
    }
}
