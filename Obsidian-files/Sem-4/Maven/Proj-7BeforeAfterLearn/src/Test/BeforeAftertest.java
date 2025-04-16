package Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import main.Shapes;


@TestInstance(TestInstance.Lifecycle.PER_CLASS) 
public class BeforeAftertest {

    BeforeAftertest(){
        System.out.println("Object Is Created");
    }
    
    Shapes shape;

    @BeforeAll
    static void initAll() {
        System.out.println("Before All Test");
    }

    @AfterAll
    static void destroyAll() {
        System.out.println("After All Test"); 
    }

    @BeforeEach
    void init() {
        shape = new Shapes();
        System.out.println("Before Test");
    }

    @Test 
    void testSqArea() {
        assertEquals(25, shape.SquareArea(5));
    }

    @Test
    void testCircleArea() {
        assertEquals(314, shape.CircleArea(10));
    }

    @AfterEach
    void destroy() {
        System.out.println("Test Completed");
    }


}
