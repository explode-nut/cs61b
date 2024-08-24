/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static void main(String[] args) {
        String[] asciis = {"cat", "abc", "duc", "tba", "bbb", "bac", "cab"};
        String[] sorted = sort(asciis);
        for (String i : sorted) {
            System.out.println(i);
        }
    }

    public static String[] sort(String[] asciis) {
        if (asciis.length == 0 || asciis.length == 1) {
            return asciis;
        }
        int[] buckets = new int[256];
        int[] starts = new int[256];
        String[] result = new String[asciis.length];
        result = asciis.clone();

        for (int i = asciis[0].length() - 1; i >= 0; i--) {
            for (String e : result) {
                char t = e.charAt(i);
                buckets[(int) t]++;
            }
            int position = 0;
            for (int j = 0; j < buckets.length; j++) {
                starts[j] = position;
                position += buckets[j];
            }
            String[] tmp = new String[asciis.length];
            for (int k = 0; k < asciis.length; k++) {
                char t = result[k].charAt(i);
                tmp[starts[(int) t]] = result[k];
                starts[(int) t]++;
            }
            buckets = new int[256];
            starts = new int[256];
            result = tmp;
        }

        return result;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        return;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
}
