/**
 * The scheduleMain class is the application program used to run the various methods of the ProcessSchedule class.
 * 
 *@author Spencer Childers, Drake Hovsepian
 *Version 1.0
 *Compiler Project 3
 *CS322 - Compiler Construction
 *Fall 2021
 */
import java.io.IOException;

public class scheduleMain extends ProcessSchedule{

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub		
		
		System.out.println("Running Part A.\n");
		partA();
		System.out.println("Part A Finished.\n");
		System.out.println("Running Part B. \n");
		partB();
		System.out.println("Part B Finished.\n");
		System.out.println("Running Part C. \n\nNumber of unique classes offered by each department:\n");
		partC();
		System.out.println("Part C Finished.");
		
	}

}

