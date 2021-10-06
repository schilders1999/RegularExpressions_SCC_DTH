/**
 * The ProcessSchedule class takes a text file containing the fall class schedule for Bellarmine university and searches
 * various regular expressions in the text.
 * 
 *@author Spencer Childers, Drake Hovsepian
 *Version 1.0
 *Compiler Project 3
 *CS322 - Compiler Construction
 *Fall 2021
 */


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.HashMap;

public class ProcessSchedule{


	//Method that extracts the course number and their descriptions from the 2021FA_Schedule.txt file, placing this new information into a 2021_Classes.txt file.
	public static void partA() throws IOException {
		String filePath = "C:\\eclipse-workspace\\Regular Expressions\\2021FA_Schedule.txt";
		
		//Converting the text file into a single string.
		String contents = Files.readString(Path.of(filePath));
		
		//Pattern for picking out classes and their information.
		String RegEx0 = "[A-Z]{2,4}-\\d{3}[A-Z]?-[\\d|A-Z]{2}.*\r\n.*((AM|PM)(.*(\\.))?)";
		
		//Creating pattern object r for RegEx0
		Pattern r = Pattern.compile(RegEx0);
		
		//Creating matcher object that finds r patterns in the contents string 
		Matcher m = r.matcher(contents);
		
		//Creating file with only the classes and their info.
		PrintWriter out = new PrintWriter("2021_Classes.txt");
		
		//while the matcher continues to find matches, these matches are printed in the "2021_Classes.txt" file.
		while (m.find())
		{
			out.println(m.group() + "\n");
		}	
		out.close();
	}
	
	
	
	//Method that extracts the section number and whether or not the class is open or closed along with the seating numbers from the 2021_Classes.txt file, writing this information
	//into the new 2021_ClassAvailability.txt file.
	public static void partB() throws IOException {
		String filePath = "C:\\eclipse-workspace\\Regular Expressions\\2021_Classes.txt";
		
		//Converting the text file into a single string.
		String contents = Files.readString(Path.of(filePath));
		
		String RegEx1 = "[A-Z]{2,4}-\\d{3}[A-Z]?-[\\d|A-Z]{2}";    //Pattern for finding class ID
		String RegEx2 = "(Open|CLOSED) \\d{1,2} \\d{1,2}";         //Pattern for finding open vs closed class and number of seats

		//Creating pattern objects
		Pattern r = Pattern.compile(RegEx1); 
		Pattern o = Pattern.compile(RegEx2);
		
		//Creating matcher objects
		Matcher m = r.matcher(contents);
		Matcher n = o.matcher(contents);
		
		//Creating file with only the classes and their info.
		PrintWriter out = new PrintWriter("2021_ClassAvailability.txt");
		
		//while the matcher continues to find matches, these matches are printed and a counter is tallied up.
		while (m.find()&&n.find())
		{
			out.println(m.group() + " " + n.group() + "\n");
		}		
		out.close();
	}	
	
	
	//Method for counting the number of unique classes in the 2021_ClassAvailability.txt file. 
	public static void partC() throws IOException {
		String filePath = "C:\\eclipse-workspace\\Regular Expressions\\2021_ClassAvailability.txt";
		
		//Converting the text file into a single string.
		String contents = Files.readString(Path.of(filePath));
		
		String RegEx1 = "[A-Z]{2,4}-\\d{3}[A-Z]?-[\\d|A-Z]1";    //Pattern for finding class ID

		//Creating pattern object r
		Pattern r = Pattern.compile(RegEx1);
		
		//Creating matcher object m
		Matcher m = r.matcher(contents);
				
		//Creating an array list used to store the various pattern matches found by the RegEx
		ArrayList<String> uniqueClasses = new ArrayList<String>();
		
		//while the matcher continues to find matches, these matches are printed and a counter is tallied up.
		while (m.find())
		{
			uniqueClasses.add(m.group());
			
		}	
//		System.out.println(uniqueClasses); //Used to print out the list of all unique classes
		
		
		//Converting the array list uniqueClasses into a string so that RegEx can be used on it
		String listString = String.join(",", uniqueClasses);
	
		String RegEx3 = "[A-Z]{2,4}"; //RegEx for finding the class type
		
		//Creating pattern object o
		Pattern o = Pattern.compile(RegEx3);
		
		//Creating matcher object p
		Matcher p = o.matcher(listString);
		
		//Creating hashmap for storing the unique classes
		HashMap<String, Integer> uniqueClassCounter = new HashMap<String, Integer>();
		
		//While the matcher continues to find matching patterns in the listString string...
		while (p.find())
		{
//			System.out.println(p.group()); //Prints out the type of each unique class found in the array list above
			
			//checking the hashmap to determine if the key (class department code) being checked already exists
			if (uniqueClassCounter.containsKey(p.group())) {
                int uniqueClassCount = (int)uniqueClassCounter.get(p.group());
                uniqueClassCount++;//increases the value stored with the department code key to keep track of the number of times that department key is found 
                uniqueClassCounter.put(p.group(), uniqueClassCount );
            } else {
                uniqueClassCounter.put(p.group(), 1);//Stores new department code keys
            }//end if else
		}//end while
		
		
		//for loop used to print out the hashmap.
		for (HashMap.Entry<String, Integer> entry :uniqueClassCounter.entrySet()) {
            String uniqueClassString = entry.getKey();
            int uniqueClassInt = entry.getValue();
            System.out.println(uniqueClassString + " : " + uniqueClassInt);
        }//end for loop
		System.out.println();
		
	}
}