import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Engine {
	
	ArrayList<String> txt = new ArrayList<String>();
	
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
             txt.add(line);
         }
    }
    
    //Method responsible for searching data structure for
    //most relevant results based on input 'i'
    public String[] getSearch(String i)
    {
    	
    	String[] output = {"first", "second", "third"};
    	return output;
    }
    
    //return title at specific index
    public String getIndex(int i)
    {
    	return txt.get(i);
    }
    
    //return whole list
    public ArrayList getList()
    {
    	return txt;
    }
}
