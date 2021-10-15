package three;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.HashMap;

/**
 * Class that accepts the name of a file and a display option.
 * Displays information about a series of log files, including the number of lines in the file,
 * the number of IPV4 addresses attempting to access the file, and the number of users tied to those addresses.
 * Also displays the IP addresses and users and how many times they were found in the log
 * @author Spencer Childers, Drake Hovsepian
 *Version 1.0
 *Compiler Project 3
 *CS322 - Compiler Construction
 *Fall 2021
 */

public class LogFile {
	//= "C:\\Users\\dhovs\\eclipse-workspace\\RegexProject\\bin\\auth.log.1.csv" my file location, 
	//We couldn't get these variables to work as arguments. This program is completely functional, but the file location 
	//and expected output need to be entered here to not receive null as a result
	public static String fileName;//global variable for file to be processed. If fileName is set to path of file, code will run.
	public static String stringInt;//Unused string, intended to have been used in argument line.
	public static int output;//global variable to view output information. If output is set to 0, 1, or 2, code will run.
		
		public static void testMethod (String fileName, int output) {
			//method supposed to use arguments passed in at command line
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
			
			String line; //string used to feed buffered reader
			int lineCount = 0; //count of lines read in file
			
			Pattern ip = Pattern.compile("\\d*[.]\\d*[.]\\d*[.]\\d*"); //pattern for IPV4 addresses
			Pattern user = Pattern.compile("user[ ]\\w+"); //pattern for usernames
			
			HashMap<String, Integer> IPLog = new HashMap<String, Integer>(); //hashmap containing captured ip's from file
			HashMap<String, Integer> UserLog = new HashMap<String, Integer>();//hashmap containing captured user names from file
			
			while((line = br.readLine()) != null) {
				//reading the file with a buffered reader one line at a time
				lineCount++; //incrementing a count to keep track of how many lines have been read
				Matcher m = ip.matcher(line);
				Matcher i =user.matcher(line);
				
				if (m.find()) {
					//finding ip addresses and storing them to hashmap
					if (IPLog.containsKey(m.group())) {
						//looks to see if ip address is already in hashmap
						int IPCount = (int)IPLog.get(m.group());
						IPCount++;//increases value key by 1, keeping track of how many times this ip address has been found
						IPLog.put(m.group(), IPCount ); //stores the ip address again with updated count
					} else {
						IPLog.put(m.group(), 1); //stores as a new ip address, has only found 1 so far
					}//end if else	
				}//end if
				
				if(i.find()) {
					//same structure as ip address, but for usernames
					if(UserLog.containsKey(i.group())) {
						int userCount = (int)UserLog.get(i.group());
						userCount++;
						UserLog.put(i.group(), userCount);
					} else {
						UserLog.put(i.group(), 1);	
					}//end if else
				}//end if
				
			}//end while
			
			switch (output) {
			//switch statement controlling what information users sees about processed file
			case 0: 
				System.out.println(lineCount + " lines in the log file were parsed");
				System.out.println("There are " + IPLog.size() + " unique IP addresses in the log");
				System.out.println("There are " + UserLog.size() + " unique users in the log");
				break;
			case 1:
				System.out.println(lineCount + " lines in the log file were parsed");
				System.out.println("There are " + IPLog.size() + " unique IP addresses in the log");
				System.out.println("There are " + UserLog.size() + " unique users in the log");
				for (HashMap.Entry<String, Integer> entry :IPLog.entrySet()) {
					String IPString = entry.getKey();
					int IPInt = entry.getValue();
					System.out.println(IPString + " : " + IPInt); 
					//for looping printing each hashmap location as a neat Key : Value pair
				}//end for loop
				break;
			case 2:
				System.out.println(lineCount + " lines in the log file were parsed");
				System.out.println("There are " + IPLog.size() + " unique IP addresses in the log");
				System.out.println("There are " + UserLog.size() + " unique users in the log");
				for (HashMap.Entry<String, Integer> entry :UserLog.entrySet()) {
					String UserString = entry.getKey();
					int UserInt = entry.getValue();
					System.out.println(UserString + " : " + UserInt);
				}//end for loop
			}//end switch statement
			
		}//end try
		catch (Exception e) {
			System.err.println(e.getMessage());
		}//end catch
		
	}//end method
		public static void main (String[] args){
//			args[0] = fileName;
//			args[1] = stringInt;
//			output = Integer.parseInt(stringInt);
//			
			testMethod(fileName, output); //calling method supposed to use arguments passed in at command line
 }//end main 
		
}//end class
