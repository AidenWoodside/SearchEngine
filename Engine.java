import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Engine {
	
	//Initialize library to hold all of the books
	private LinkedList<Website> library = new LinkedList<Website>();
	
    public Engine() throws FileNotFoundException
    {
    	//initialize the text file to be read
		File file = new File("titles.txt");
		
		//initialize scanner to iterate through text
		//file
		Scanner sc = new Scanner(file);
		
		//iterate through the text file
		String url = "";
		String description = "";
		while(sc.hasNextLine())
		{
		    url = sc.nextLine();
		    description = sc.nextLine();
		    //create new book object
		    Website book = new Website(url, 0, description);
		    
		    //add new object to library
		    library.add(book);
		}
    }
    
    //Method responsible for searching data structure for
    //most relevant results based on input 'i'
    public void setResults(String input)
    {
    	//set the relevance for every book in the library according
    	//to the user's search input
    	setRelevance(input);
    	
    	//sort whole library based on relevance
    	sort(library);
    }
    
    //takes the amount of results that the user wants
    //and returns an array with the correct amount of 
    //books that are relevant in descending order
    public String[] getResults(int totalResults)
    {
    	String[] output = new String[totalResults];
    	for(int i = 0; i < totalResults; i++)
    	{
    		output[i] = library.get(i).getUrl();
    	}
    	
    	return output;
    }
    

	private void setRelevance(String input)
    {
    	String[] str = input.toLowerCase().split(" ", 20);
    	
    	//this hash set contains many prepositions and articles that
    	//will be valued less when checking for relevance in description
    	HashSet<String> set = new HashSet<>(Arrays.asList("an", "a", "the", "of", "and", "in", "at", "for", "to", "on"));
    	
    	//iterate through all of the descriptions
    	//check the first index of input
    	for(int i = 0; i < library.size(); i++)
    	{
    		//initialize the relevancy value that a book will have
    		//with the user's search input
    		int relevance = 0;
    		
    		//get description to check for relevance
    		//to check against the input which has also bee normalized
    		HashSet<String> description = library.get(i).getHashedDescription();
    		
    		//iterate through the input array to check individual strings of 
    		//the input to all of the titles in the library
    		for(int j = 0; j < str.length; j++)
    		{
    			//initialize temporary string that will hold the value of the
    			//input string currently being checked against book title
    			String temp = str[j];
    			
    			//check if description contains word within input and set 
    			//proper relevance based on if article or preposition
    			if(description.contains(temp))
    			{
    				if(set.contains(temp))
    				{
    					relevance += 1;
    				}
    				else
    				{
    					relevance += 10;
    				}
    			}
    		}
    		//set the relevance of the book
    		library.get(i).setRelevance(relevance);
    	}
    }


    public static void sort(LinkedList<Website> list) 
    {
        list.sort((o1, o2) -> Integer.compare(o2.getRelevance(), o1.getRelevance()));
    }

}


class Website
{
	private String url;
	private String description;
	private int relevance;
	
	private HashSet<String> desc = new HashSet<String>();
	
	public Website(String title, int relevance, String description) {
		super();
		
		//initialize all of the internal attributes of webpage object
		this.url = title;
		this.relevance = relevance;
		this.description = description;
		String[] split = description.split(" ", 100);
		
		//create hashSet containing all the individual words of
		//description so that checking for relevance is easier
		for(String x : split) {
			x = x.toLowerCase();
			if(!desc.contains(x))
				desc.add(x);
		}
	}
	
	public String getUrl()
	{
		return this.url;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
	public HashSet<String> getHashedDescription()
	{
		return desc;
	}
	
	public int getRelevance()
	{
		return this.relevance;
	}
	
	public void setRelevance(int i)
	{
		this.relevance = i;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
}
