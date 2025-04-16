package Test;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class ArraysTest {

    @Test
    void testArray ()
    {
        int []expected = {2,4,6};
        int []actual = {4,6,2};

        Arrays.sort(actual);
        assertArrayEquals(expected,actual);
    }
    
}
