/**
* The TextProcessor class asks for the user to give the full path of a file and a regular expression that they would like to search the file for.
* Once both are given, all valid patterns that follow the RegEx formula are returned and counted.
* 
* @author Spencer Childers, Drake Hovsepian
* @version 1.0
* Compiler Project 3
* CS322 - Compiler Construction
* Fall 2021
*/

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextProcessor {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//Creating a scanner object
		Scanner scan = new Scanner(System.in);
		
		String RegEx = null;//String for regular expression
		String answer;//String for storing user's answer
		String filePath;//String of file path
		int counter;//Counts the number of matching patterns
		boolean program = true;//boolean value used to repeat the body of the code until user wishes to stop
		
		
		
		System.out.println("Type the path of your file.");
		//Example Path: C:\eclipse-workspace\Regular Expressions\DraculaFullText - Copy.txt
			filePath = scan.nextLine();
			
			
		//Converting text file in to a single string.
		String contents = Files.readString(Path.of(filePath)); 

		
		//while boolean program is true, regular expressions can be tested
		while(program == true)
		{
		
			// Reinitializing the counter to zero.
			counter = 0;
			
			System.out.println("Type in your regular expression.\n\nExample: Phrases that contain the word ‘Transylvania == (([ a-zA-Z?!:,;]+)?Transylvania([ a-zA-Z:!?,;]+)?(\\\\.)?)\n");
				RegEx = scan.nextLine();	
				
				// List of required Regular Expressions
				// A. Match the articles “a”, “an” or “the" == "( (A|a) )|( (An|an) )|( (The|the) )"
	            // B. The phrase ‘Mina Harker’ or ‘Mrs. Harker’ == "(Mina Harker)|(Mrs. Harker)"
	            // C. Phrases that contain the word ‘Transylvania’ == "(([ a-zA-Z:,;]+)?Transylvania([ a-zA-Z:,;]+)?)"
	            // D. Infinitive phrases (e.g. ‘to run’, ‘to hide’, etc.) including the word to == " to \\w*"
	            // E. Words that end with ‘ing’ except Godalming and Helsing == "\\w*(?<!Hels)(?<!Godalm)(ing)"
	           
			//Creating a pattern object from the expression given by the user
			Pattern r = Pattern.compile(RegEx);
			
			//Creating a matcher object that will be used to find all of the patterns (r) within the contents string.
			Matcher m = r.matcher(contents);
			
			//while the matcher continues to find matches, these matches are printed and a counter is tallied up.
			while (m.find())
			{
				System.out.println(counter + ") " + m.group() + "\n");
				counter++;
			}
			System.out.println("===============================================================\nThere are a total of " + counter 
					+ " phrases that match your search.\n===============================================================\nWould you like to perform another search (Y or N)?"
					+ "\n===============================================================");
				answer = scan.nextLine();
				
			//Determines if user wishes to run another regular expression.
			if(answer.equalsIgnoreCase("N"))
			{
				program = false;
			}
				
		}
		scan.close();
	}
}
