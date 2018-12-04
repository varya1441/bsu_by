import java.util.Enumeration;
import java.util.Hashtable;

public class Main {

    public static void main(String[] arg) {

        Hashtable<Character, Integer> hTable = new Hashtable<>();
        Enumeration letter;
        Character key;
        int tempCount = 0;
        StringBuilder args = new StringBuilder();


        for (String a : arg) {
            args.append(a);
        }
        int argsLen = args.length();
        for (int j = 0; j < argsLen; j++) {
            if (hTable.containsKey(args.charAt(j))) {
                tempCount = hTable.get(args.charAt(j)) + 1;
                hTable.put((args.charAt(j)), tempCount);
            } else {
                hTable.put((args.charAt(j)), 1);
            }
        }
        letter = hTable.keys();
        while (letter.hasMoreElements()) {
            key = (Character) letter.nextElement();
            System.out.println("letter: " + key + "  exists " + hTable.get(key) + " times");
        }
    }
}
