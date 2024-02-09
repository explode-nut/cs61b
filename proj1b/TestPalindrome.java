import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    //Uncomment this class once you've created your Palindrome class.

    @Test
    public void TestPalindrome(){
        String noon = "noon";
        String a = "a";
        String empty = "";
        String Noon = "Noon";
        String moon = "moon";
        String Moon = "Moon";
        String noom = "noom";
        String other1 = "@abcA";
        String other2 = "Aabc@";

        System.out.println("test common method start.....");
        assertTrue("test failed when pass a value \"" + noon + "\" expected true but got false", palindrome.isPalindrome(noon));
        assertTrue("test failed when pass a value \"\" expected true but got false", palindrome.isPalindrome(empty));
        assertTrue("test failed when pass a value \"" + a + "\" expected true but got false", palindrome.isPalindrome(a));
        assertFalse("test failed when pass a value \"" + Noon + "\" expected false but got true", palindrome.isPalindrome(Noon));
        assertFalse("test failed when pass a value \"" + moon + "\" expected false but got true", palindrome.isPalindrome(moon));
        System.out.println("test passed!");

        System.out.println("test overload method start.....");
        OffByOne offByOne = new OffByOne();
        assertTrue("test failed when pass a value \"" + moon + "\" expected true but got false", palindrome.isPalindrome(moon, offByOne));
        assertTrue("test failed when pass a value \"" + noom + "\" expected true but got false", palindrome.isPalindrome(noom, offByOne));
        assertFalse("test failed when pass a value \"" + Moon + "\" expected false but got true", palindrome.isPalindrome(Moon, offByOne));
        assertFalse("test failed when pass a value \"" + other1 + "\" expected false but got true", palindrome.isPalindrome(other1, offByOne));
        assertFalse("test failed when pass a value \"" + other2 + "\" expected false but got true", palindrome.isPalindrome(other2, offByOne));
        assertFalse("test failed when pass a value \"" + a + "\" expected false but got true", palindrome.isPalindrome(a, offByOne));
        assertFalse("test failed when pass a value \"" + empty + "\" expected false but got true", palindrome.isPalindrome(empty, offByOne));
        System.out.println("test passed!");

        System.out.println("all tests passed!");
    }
}
