import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

// IN ORDER FOR THIS CODE TO PROPERLY RUN YOU MUST CHANGE YOUR CONSOLE BUFFER SIZE. 
// TO DO THIS GO TO WINDOWS -> PREFERENCES -> RUN/DEBUG -> CONSOLE -> CHANGE CONSOLE BUFFER SIZE TO 1000000


public class RedditReader {

	public RedditReader(String inputFile) {
		
		//Variable and Object Initialization
		File fileOne = new File(inputFile); //File Object
		FileReader FR = null; //File Reader Object
		BufferedReader BR = null; //Buffered Reader Object
		String[] words; //A temporary array that stores words from the line being analyzed.   
		Map<String, Integer> wordMap = new HashMap<String, Integer>(); //Hash Table utilized to keep track of various words mentioned and the number of times they're mentioned.
		String k; //A temporary value that contains a key value from the Hash Table.
		Integer v;//A temporary value that contains the list value for the corresponding key value (variable k).
		
		//Try Catch Utilized for File Reading and Buffered Reading...
		try {
			
			FR = new FileReader(fileOne);
			BR = new BufferedReader(FR);
			
			while (BR.ready()) { //BR.ready checks to see if there is another line that can be read below. Returns Boolean Type.
				String TempVar = BR.readLine(); //Takes the line and saves it to temporary variable "TempVar".
				TempVar = TempVar.toLowerCase(); //Turns the line saved in the TempVar to all lower case.
				words = TempVar.split(" "); //Breaks down the line into an array of words. 
				for (int i=0; i<words.length; i++) //For loop used to analyze each individual word in the array.
				{
					String w = words[i]; //Another temporary variable that saves the individual word out of the array.
					w = w.replaceAll("[-+.^:,?]",""); //This replaces any of the special characters listed with nothing or in other words "deletes them".					
					if (wordMap.get(w)==null) //This if statement processes the word and determines whether the word already exists in the Hash Table or if a new key/list pair needs to be created.
					{
						wordMap.put(w, 1); //If the word doesn't exist in the Hash Table, this creates a new entry.
					}
					else
					{
						wordMap.put(w, wordMap.get(w)+1); //If the word exists already, add one to the pre-existing list value.					
					}
				}
			}

			BR.close(); //Closes out of the file.
			
			
			Set<String> keyList = wordMap.keySet(); //This variable is now given the list of keys from the Hash Table.
			
			while (keyList.isEmpty() != true) //While loop that checks whether the list of keys contains a value.
			{
				v = new Integer(0); //Wrapper Class, set to zero.
				k=null;
				for(String key: keyList){ //For loop runs through the list of keys one by one.
					if (v < wordMap.get(key)) // If statement that compares the previous key list value to the current value of the for loop. This determines which value is the greatest in the key list.
					{
						k = key; //The key entry that is being processed by the loop.
						v = wordMap.get(key); //The corresponding value to the key.
					}
				}
				System.out.println( k + " - " + v); //After comparing all the key list values. The largest values are printed here.
				keyList.remove(k); //This removes the greatest key entry out of the list of keys.
			}
			

		}

		catch (Exception e) { //In case an error is thrown, output the following statement below...
			System.out.println("Something went wrong. Recheck your code!\n"+e);
		}
	}

}










