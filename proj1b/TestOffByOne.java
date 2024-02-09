import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    //Uncomment this class once you've created your CharacterComparator interface and OffByOne class. *

    @Test
    public void TestOffByOne(){
        assertTrue("test failed when pass a and b expected true but got false", offByOne.equalChars('a', 'b'));
        assertTrue("test failed when pass a and b expected true but got false", offByOne.equalChars('b', 'a'));
        assertFalse("test failed when pass b and A expected false but got true", offByOne.equalChars('b', 'A'));
        assertFalse("test failed when pass ` and a expected false but got true", offByOne.equalChars('`', 'a'));
        assertFalse("test failed when pass @ and A expected false but got true", offByOne.equalChars('@', 'A'));
        assertFalse("test failed when pass A and @ expected false but got true", offByOne.equalChars('A', '@'));

        System.out.println("test passed!");
    }
}
