package Test;

import main.SortingArray;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestException_2 {

    @Test
    void testSortingArrayThrowsNullPointerException() {
        int[] unsorted = {2, 5, 3};
        SortingArray array = new SortingArray();
        assertThrows(NullPointerException.class, () -> array.sortingArray(unsorted));
    }
}
