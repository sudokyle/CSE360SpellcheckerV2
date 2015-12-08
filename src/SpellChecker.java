import java.io.*;
import java.util.Scanner;

/**
 * Created by CptAmerica on 12/8/15.
 */
public class SpellChecker {

    public static void main(String[] args) throws IOException {

        Dictionary dict = new Dictionary();
        /*dict.insertWord("A");
        dict.insertWord("D");
        dict.insertWord("F");
        dict.insertWord("H");
        dict.insertWord("B");
        dict.insertWord("C");
        dict.insertWord("E");
        dict.insertWord("G");
        dict.insertWord("L");
        dict.insertWord("M");
        dict.insertWord("O");
        dict.insertWord("N");
        dict.insertWord("Q");
        dict.insertWord("R");
        dict.insertWord("I");
        dict.insertWord("J");
        dict.insertWord("K");
        dict.insertWord("P");
        dict.insertWord("T");
        dict.insertWord("S");
        dict.insertWord("W");
        dict.insertWord("U");
        dict.insertWord("V");
        dict.insertWord("X");
        dict.insertWord("Y");
        dict.insertWord("Z");*/
        /*dict.insertWord("hey");
        dict.insertWord("ice");
        dict.insertWord("like");
        dict.insertWord("man");
        dict.insertWord("what");
        dict.insertWord("up");*/
        System.out.println(dict.toString());
        Scanner scan = new Scanner(System.in);
        String in;
        System.out.println(" would you like to enter a file? [y/n]");
        in  = scan.nextLine();
        while(in.equalsIgnoreCase("y")) {
            dict.clearArrays();
            String filename = scan.nextLine();
            File file = new File(filename);
            FileReader read = new FileReader(file);
            BufferedReader readB = new BufferedReader(read);

            String line;
            while ((line = readB.readLine()) != null) {
                boolean verifyLetter = true;
                char[] wordChar = line.toCharArray();
                for (char c : wordChar) {
                    if (!Character.isLetter(c)) {
                        verifyLetter = false;
                    }
                }

                if (verifyLetter) {  //Disregard if verifyLetter is false as Line does not just contain letters
                    int correct = dict.searchWord(dict.getDictionary(), "dict", line);
                    if (correct == -1) {
                        dict.insertIncWord(line);
                    }
                }
            }
            System.out.println("Incorrect Words:");
            System.out.println(dict.incorrectWordsString());
            System.out.println("Would you like to add any of these?");
            String input = scan.nextLine();
            if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                int size = dict.getIncSize();
                for (int i = 0; i < dict.getIncSize(); i++) {
                    System.out.println("Would you like to add: " + dict.getIncorrectWords()[i] + " ? enter: [y/n]");
                    input = scan.nextLine();
                    if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
                        dict.insertWord(dict.getIncorrectWords()[i]);
                        dict.insertAdWord(dict.getIncorrectWords()[i]);
                        dict.delete(dict.getIncorrectWords(), dict.getIncorrectWords()[i], "inc");
                        i -= 1;
                    }
                }
            }
            System.out.println(dict.dictToFile());
            System.out.println(dict.addedToFile(null));
            System.out.println(dict.incToFile(null));

            System.out.println(" would you like to enter another file? [y/n]");
            in  = scan.nextLine();
        }
        System.exit(0);
    }



}