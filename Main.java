
//Will be the main class for the document
//This main class will be used to iterate through
//the user interaction. Takes user input in loop

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{

        Engine en = new Engine();

        //construct the index file and file readedr
        File file = new File("titles.txt");
        Scanner sc = new Scanner(file);

        //iterate through the text file
        String line = "";
        while(sc.hasNextLine())
        {
            line = sc.nextLine();

            System.out.println(line);
        }

        
    }
}