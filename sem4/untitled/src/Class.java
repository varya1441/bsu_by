import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Class {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        int rl=0;
        if(scanner.next().equals("R")){
            rl=-1;
        }else {
            rl=1;
        }
        String stroka="qwertyuiopasdfghjkl;zxcvbnm,./";


        char[] str=scanner.next().toCharArray();

        for (int i = 0; i <str.length ; i++) {
            int ind=stroka.indexOf(str[i]);
            System.out.print(stroka.charAt(ind+rl));
        }
    }



}
