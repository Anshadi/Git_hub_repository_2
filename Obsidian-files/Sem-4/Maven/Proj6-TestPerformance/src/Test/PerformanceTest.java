package Test;
import main.Performance;

import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.time.Duration;

import org.junit.jupiter.api.Test;

public class PerformanceTest {

    @Test
    public void test()
    {
        int[] unsorted ={5,3,6,1};
        Performance pf = new Performance();

        assertTimeout(Duration.ofMillis(28), ()->pf.sorted(unsorted) );
    } 
}
