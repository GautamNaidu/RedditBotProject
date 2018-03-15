import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class RedditReader {

	public RedditReader(String inputFile) {
		
		//Variable and Object Initialization
		File fileOne = new File(inputFile); //File Object
		FileReader FR = null; //File Reader Object
		BufferedReader BR = null; //Buffered Reader Object
		ArrayList<String> commentsArray = new ArrayList<String>(); //Array of all comments from .txt file.
		ArrayList<String> wordSpecificArray = new ArrayList<String>(); //Array of all comments that have the desired word in them.
		Scanner input = new Scanner(System.in); //Scanner Object 
		double percentage;
		String[] words;
		Map<String, Integer> wordMap = new HashMap<String, Integer>();

		//Try Catch Utilized for File Reading and Buffered Reading...
		try {
			
			FR = new FileReader(fileOne);
			BR = new BufferedReader(FR);
			
			while (BR.ready()) { //BR.ready checks to see if there is another line that can be read below. Returns Boolean Type.
				String TempVar = BR.readLine(); //Takes the line and saves it to temporary variable "TempVar".
				words = TempVar.split(" ");
				for (int i=0; i<words.length; i++) 
				{
					String w = words[i];
					w = w.replaceAll("[-+.^:,?]","");					
					if (wordMap.get(w)==null) 
					{
						wordMap.put(w, 1);
					}
					else
					{
						wordMap.put(w, wordMap.get(w)+1);						
					}
				}
				 
				commentsArray.add(TempVar); //Adds the line to the array list.

				TempVar = TempVar.toLowerCase(); //Turns the line saved in the TempVar to all lower case.

			}
			percentage = ((double)wordSpecificArray.size()/(double)commentsArray.size())*100;
			percentage = Math.round(percentage * 100.0)/100.0;

			BR.close(); //Closes out of the file.
			
			String k;
			Integer v;
			Set<String> keyList = wordMap.keySet();
			
			while (keyList.isEmpty() != true)
			{
				v = new Integer(0);
				k=null;
				for(String key: keyList){
					if (v < wordMap.get(key))
					{
						k = key;
						v = wordMap.get(key);
					}
				}
				System.out.println( k + " - " + v);
				keyList.remove(k);
			}
			

		}

		catch (Exception e) { //In case an error is thrown, output the following statement below...
			System.out.println("Something went wrong. Recheck your code!\n"+e);
		}
	}

}










