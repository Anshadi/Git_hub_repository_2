package com.proj4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class FigureTest {

    @Test
    public void ComputeSQAreaTest() {
        assertEquals(576.00, Shape.ComputeSQArea(24), 0.001); // Adjust the delta value based on your precision requirements
    }


    // @Test
    // public void ComputeCLAreaTest() {
    //     assertEquals("Not Came as expected ",125.00, Shape.ComputeCLArea(10), 0.001); // Adjust the delta value based on your precision requirements
    // }
    
    
    // Using Supplier Interface 
    
    //     @Test
    // public void ComputeCLAreaTest_Supplier() {
        //     assertEquals( "Not Came as expected" ,125.00, Shape.ComputeCLArea(10), 0.001); // Adjust the delta value based on your precision requirements
        // }
        
        // @Test
        // public void ComputeSQAreaTest_1() {
        //     assertNotEquals("Came as expected ",314.00, Shape.ComputeCLArea(10), 0.001); // Adjust the delta value based on your precision requirements
        // }
        
        
        // @Test
        // public void ComputeCLAreaTest_2(){
        //     assertNotEquals(574.00, Shape.ComputeSQArea(24), 0.001); // Adjust the delta value based on your precision requirements
        // }

    @Test
    public void assertTest ()
    {
    String a = "Hello";
    assertTrue(a.equals("HELLO"));
}

}
