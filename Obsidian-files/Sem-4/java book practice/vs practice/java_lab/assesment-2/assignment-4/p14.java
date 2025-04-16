public class p14 {


    public static void main(String[] args) {
        int num =4676 ;
        String res="";
        while(num!=0)        {
            res+=Integer.toString(((num%10)+2)%10);    
            num/=10;
        }
        
        num = Integer.parseInt(res) ;

        while(num!=0)        {
            System.out.println(num%10);   
            num/=10;
        }
    }
}
