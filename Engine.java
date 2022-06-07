import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Engine {
	
	//Initialize library to hold all of the books
	private ArrayList<Book> library = new ArrayList<Book>();
	
    public Engine() throws FileNotFoundException
    {
    	//initialize the text file to be read
		File file = new File("titles.txt");
		
		//initialize scanner to iterate through text
		//file
		Scanner sc = new Scanner(file);
		
		//iterate through the text file
		String line = "";
		while(sc.hasNextLine())
		{
		    line = sc.nextLine();
		    
		    //create new book object
		    Book book = new Book(line, 0);
		    
		    //add new object to library
		    library.add(book);
		}
    }
    
    //Method responsible for searching data structure for
    //most relevant results based on input 'i'
    public String[] getResults(String input)
    {
    	//set the relevance for every book in the library according
    	//to the user's search input
    	setRelevance(input);
    	
    	//sort whole library based on relevance
    	sort(library);
    	
    	//format output
    	String[] output = {library.get(0).getTitle(), library.get(1).getTitle(), library.get(2).getTitle()};
        
        //return formatted results
    	return output;
    }
    

	private void setRelevance(String input)
    {
    	String[] str = input.toLowerCase().split(" ", 20);
    	
    	//this hash set contains many prepositions and articles that
    	//will be valued less when checking for relevance in titles
    	HashSet<String> set = new HashSet<>(Arrays.asList("an", "a", "the", "of", "and", "in", "at", "for", "to", "on"));
    	
    	//iterate through all of the titles
    	//check the first index of input
    	for(int i = 0; i < library.size(); i++)
    	{
    		//initialize the relevancy value that a book will have
    		//with the user's search input
    		int relevance = 0;
    		
    		//get title to check for relevance and normalize it to lowercase
    		//to check against the input which has also bee normalized
    		String title = library.get(i).getTitle().toLowerCase();
    		
    		//split title into array so it is easier to check for the relevance
    		String[] split_title = title.split(" ", 50);
    		
    		//iterate through the input array to check individual strings of 
    		//the input to all of the titles in the library
    		for(int j = 0; j < str.length; j++)
    		{
    			
    			//initialize temporary string that will hold the value of the
    			//input string currently being checked against book title
    			String temp = str[j];
    			
    			//iterate through every individual word within the title and
    			//check to see if the current input value is within the title
    			for(int k = 0; k < split_title.length; k++)
    			{
    				
    				//this checks to see if the input value is an article or
    				//a preposition and assigns a lower relevancy value if true
    				if(set.contains(temp) && temp.equals(split_title[k]))
    				{
    					relevance += 1;
    				}
    				else if(temp.equals(split_title[k]))
    				{
    					relevance+=10;
    				}
    			}
    		}
    		
    		//set the relevance of the book
    		library.get(i).setRelevance(relevance);
    	}
    }


    public static void sort(ArrayList<Book> list) 
    {
        list.sort((o1, o2) -> Integer.compare(o2.getRelevance(), o1.getRelevance()));
    }

}


class Book
{
	private String title;
	private int relevance;
	
	public Book(String title, int relevance) {
		super();
		this.title = title;
		this.relevance = relevance;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	public int getRelevance()
	{
		return this.relevance;
	}
	
	public void setRelevance(int i)
	{
		this.relevance = i;
	}
}
