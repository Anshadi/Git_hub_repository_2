package Test;

import main.RevString;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class RevStringTest {
    
    @Test
    public void testReverseString() {
        String input1 = "Hello, World!";
        String expected1 = "!dlroW ,olleH";
        String result1 = RevString.reverseString(input1);
        
        assertEquals(expected1, result1);

        String input2 = "abc";
        String expected2 = "cba";
        String result2 = RevString.reverseString(input2);
        
        assertEquals(expected2, result2);
    }
}
