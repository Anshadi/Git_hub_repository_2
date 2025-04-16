package main ;

import java.util.Arrays;

    public int[] sorted( int[] arr)
    {
        for(int i=0;i<1000000;i++)
        Arrays.sort(arr);
        
        return arr ;
    }
}