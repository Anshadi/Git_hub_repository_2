import java.util.Scanner;

public class program1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); 
        sc.close();
        
        String st = pusher(n, "");
        
        System.out.println(st);
    }

    static String pusher(int n, String st) {
       if(n <= 15)
           return Condition(n);
       else {
           return pusher(n / 16, Condition(n % 16) + st);
       }
    }

    static String Condition(int n) {
        switch (n) {
            case 10:
                return "A";
            case 11:
                return "B";
            case 12:
                return "C";
            case 13:
                return "D";
            case 14:
                return "E";
            case 15:
                return "F";
            default:
                return Integer.toString(n);
        }
    }
}



// import java.util.Scanner;


// public class program1 {
//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int n = sc.nextInt(); 
//         sc.close();
//         for(int i=0;i<st.size();i++)
//         System.err.println(st.get(i);
//         return;
//     }

//     String pusher(int n,String st){
//        if(n<=15)
//        st += Condition(n) ;
//        return st;
    
//        st += Condition(n);
       
//     }

//     String Condition(int n) {
//         switch (n) {
//             case 10:
//                 return "A";
//             case 11:
//                 return "B";
//             case 12:
//                 return "C";
//             case 13:
//                 return "D";
//             case 14:
//                 return "E";
//             case 15:
//                 return "F";
//             default:
//                 return Integer.toString(n);
//         }
//     }
    
