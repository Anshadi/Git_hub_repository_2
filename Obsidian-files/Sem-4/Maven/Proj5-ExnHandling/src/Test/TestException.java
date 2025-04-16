package Test;

import static org.junit.Assert.fail;

import org.junit.Test;

import main.SortingArray;

public class TestException {

    @Test
    public void testSortingArrayy_Exception()
    {
        try {
            SortingArray array = new SortingArray();
            // int[] unsorted = {2,1,4};
            int[] unsorted = null ;
            int []sortedArray = array.sortingArray(unsorted);
            // for(int elem:sortedArray)
            // {
            //     System.out.println(elem);
            // }
            System.out.println("Statement below");
            fail();
            
        } catch (NullPointerException e) {
            System.out.println("Exception Generated");
        }
    }
    
}


// Here if the exception is not generating as we are expecting then the test case will be failed otherwise pass .
