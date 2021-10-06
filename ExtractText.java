/**
 * The ExtractText class takes a pdf file and converts it into a text document so that further analysis can be done elsewhere.
 * 
 *@author Spencer Childers, Drake Hovsepian
 *Version 1.0
 *Compiler Project 3
 *CS322 - Compiler Construction
 *Fall 2021
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class ExtractText{

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		
		//Creating a File object used to load the pdf file into a PDDocument object.
		File scheduleFile = new File("2021FA_Class_Schedule_Daily.pdf");
		PDDocument doc1 = PDDocument.load(scheduleFile);
				
				
		//Creating a PDFTextStripper object 
		PDFTextStripper stripper = new PDFTextStripper();
				
		//Converting doc1 file into text
		String scheduleText = stripper.getText(doc1);
		
		//Printing out the text
		System.out.println(scheduleText);
	
		//Saving the text into a text document
		PrintWriter out = new PrintWriter("2021FA_Schedule.txt");
		out.println(scheduleText);
		
		//Closing the PrintWriter object
		out.close();
	}
}

