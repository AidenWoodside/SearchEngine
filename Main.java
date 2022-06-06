
//Will be the main class for the document
//This main class will be used to iterate through
//the user interaction. Takes user input in loop

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException{
    	
    	//declare engine object
        Engine en = new Engine();
        
        //empty string that will hold user input
        String input = "";
        
        //declare the scanner to read user input
        Scanner sc = new Scanner(System.in);
        
        //first prompt to user
        System.out.println("Hello, what would you like to search for: \n");
        input = sc.nextLine();
        
        //declare empty array that will hold the three
        //most relevant results based on search
        String[] output = null;

        
        //Start the user input loop
        while(!input.equals("-1"))
        {
        	//search for user input
        	output = en.getSearch(input);
        	
        	//print the responses
        	if(output != null)
        	{
            	System.out.println("The most relevant responses are:");
            	for(String x:output)
            		System.out.println("\t"+ x);
//            	System.out.println("The most relevant response was: \n" + en.getSearch(input));
        	}
        	else
        	{
        		System.out.println("No valid Respnses");
        	}

        	
        	//prompt user for another search query 
            System.out.println("\nwhat would you like to search for (type -1 to exit): \n");
            input = sc.next();
        }
        
        System.out.println("System Closing...");
    }
}