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
	
	ArrayList<String> txt = new ArrayList<String>();
	ArrayList<String> normalized = new ArrayList<String>();
	
	//when object created, initialize index list
	//find text file, initialize data structure to hold
	//all of the elements of the text file
    public Engine() throws FileNotFoundException
    {
    	 File file = new File("titles.txt");
         Scanner sc = new Scanner(file);

         //iterate through the text file
         String line = "";
         while(sc.hasNextLine())
         {
             line = sc.nextLine();
             normalized.add(line.toLowerCase());
             txt.add(line);
         }
    }
    
    //Method responsible for searching data structure for
    //most relevant results based on input 'i'
    public String[] getSearch(String input)
    {
    	
    	input = input.toLowerCase();
    	String[] str = input.split(" ", 20);

    		
    	HashMap<String, Double> hm = new HashMap<String, Double>();
    	
    	//fill hashmap with titles
    	for(String x : txt)
    		hm.put(x, 0.0);
    	
    	//this hash set contains many prepositions and articles that
    	//will be valued less when checking for relevance in titles
    	HashSet<String> set = new HashSet<>(Arrays.asList("an", "a", "the", "of", "and", "in", "at", "for", "to", "on"));
    	
    	//iterate through all of the titles
    	//check the first index of input
    	for(int i = 0; i < normalized.size(); i++)
    	{
    		double count = 0;
    		String[] split_title = normalized.get(i).split(" ", 50);
    		for(int j = 0; j < str.length; j++)
    		{
    			String temp = str[j];
    			for(int k = 0; k < split_title.length; k++)
    			{
    				if(set.contains(temp) && temp.equals(split_title[k]))
    				{
    					count += .1;
    				}
    				else if(temp.equals(split_title[k]))
    				{
    					count++;
    				}
    			}
    		}
    		hm.put(txt.get(i), count);
    	}

    	
    	
    	double max = -1;
    	String title= "";

    	
    	LinkedList<Title> temptop = new LinkedList<Title>();
    	
    	//print the hashmap
        for (Map.Entry mapElement : hm.entrySet()) {
            String key = (String)mapElement.getKey();
 
            // Adding some bonus marks to all the students
            double value = (double) (mapElement.getValue());
            
            Title t = new Title(key, value);
            
            if(t.freq >=max)
            {
            	max = t.freq;
            	if(max >= 1)
            	{
            		if(!temptop.isEmpty() && temptop.size()>=3)
            		{
                		for(int i = 0; i < 3; i++)
                		{
                			if(temptop.get(i).freq < t.freq)
                			{
                				temptop.add(i, t);
                				break;
                			}
                			else if(temptop.get(i).freq == t.freq)
                			{
                				temptop.add(i+1, t);
                			}
                		}
                		if(temptop.size() > 3)
                		{
                			temptop.removeLast();
                		}
            		}
            		else
            		{
            			temptop.add(t);
            		}
            	}
            }        			
        }
        
        String[] output = null; 
        
        if(temptop.size() >= 3)
        {
        	String[] tempoutput = {temptop.get(0).title, temptop.get(1).title, temptop.get(2).title};
        	output = tempoutput;
        }
        else if(temptop.size() == 2)
        {
        	String[] tempout = {temptop.get(0).title, temptop.get(1).title};
        	output = tempout;
        }
        else if(temptop.size() == 1)
        {
        	String[] tempout = {temptop.get(0).title};
        	output = tempout;
        }
        
        
    	return output;
    }
    
}




class Title
{
	public String title;
	public Double freq;
	
	public Title(String title, double freq) {
		this.title = title;
		this.freq = freq;
	}
}
