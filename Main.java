
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
        int results = 0;
        
        //declare the scanner to read user input
        Scanner sc = new Scanner(System.in);
        
        //Start the user input loop
        do
        {
            //first prompt to user
            System.out.println("Hello, what would you like to search for(-1 to exit): \n");
            input = sc.nextLine();
            
            //sort the library for most relevant results
            en.setResults(input);
            
            //validate input for the amount of results to show from user input
            boolean valid = false;
            while(!valid)
            {
            	try
            	{
                    System.out.println("How many relevant results would you like?: \n");
                    results = Integer.parseInt(sc.nextLine());
                    
                    if(results < 1)
                    	throw new Exception();
                    
                    valid = true;
            	}
            	catch(Exception e)
            	{
            		System.out.println("Please input a valid Integer > 0");
            	}

            }
            
            
            //get the amount of results requested by user
        	String[] output = en.getResults(results);
        	//print the responses
        	if(output != null)
        	{
        		if(output.length < results)
        		{
        			System.out.println("There were only " + output.length + " relevant results.");
        		}
        		
        	
            	System.out.println("The most relevant responses are:");
            	for(String x:output)
            		if(x != null)
            			System.out.println("\t"+ x);
            	
            	System.out.println();
        	}
        	else
        	{
        		System.out.println("No valid Respnses");
        	}

        }while(!input.equals("-1"));
        
        System.out.println("System Closing...");
    }
}