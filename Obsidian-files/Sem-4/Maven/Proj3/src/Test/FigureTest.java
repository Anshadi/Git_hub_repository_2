package Test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import main.Shape;

public class FigureTest {

    @Test
    public void ComputeSQAreaTest() {
        assertEquals(576.00, Shape.ComputeSQArea(24), 0.001); // Adjust the delta value based on your precision requirements
    }

    @Test
    public void ComputeCLAreaTest() {
        assertEquals("Not Came as expected ",125.00, Shape.ComputeCLArea(10), 0.001); // Adjust the delta value based on your precision requirements
    }
}
