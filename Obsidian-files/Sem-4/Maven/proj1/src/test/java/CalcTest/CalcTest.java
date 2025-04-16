package CalcTest ;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.junit.Calc;

public class CalcTest {

    @Test
    public void test() {
        // fail("Not yet implemented");
        // assertEquals("Java", "Java");
        Calc c = new Calc() ;
        int actual = c.divide(10, 5);
        int Expected = 2;
        assertEquals(Expected, actual);
    }
}
