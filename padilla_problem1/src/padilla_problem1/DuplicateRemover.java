package padilla_problem1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.FileAlreadyExistsException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import javax.activation.UnsupportedDataTypeException;

public class DuplicateRemover {
	
	//Initialize Set collection uniqueWords
	Set<String> uniqueWords = new HashSet<String>();

	
	public void remove(String dataFile) {
		
		//Try-with-resources to clean/auto-close resources to prevent resource leaks
		try(Scanner scan = new Scanner(new FileReader(new File(dataFile)));) {
			while(scan.hasNext()) {
				//add words to the set in all lower-case to disregard differing capitalization stated in the assignment
				uniqueWords.add(scan.next().toLowerCase()); 
			}
		}
		
		//Likely Exceptions to be caught, see printed error messages
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("\n" + "The file was not found, try changing the path or checking the proper directory.");
			System.exit(0);
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("\n" + "Invalid/null/empty path used, please check the path. May also be a null scan.next()");
			System.exit(0);
		}
		//In case the Scanner is somehow closed when it attempts to scan
		catch(IllegalStateException e) {
			e.printStackTrace();
			System.out.println("\n" + "The Scanner was closed when it attempted to scan.next()");
			System.exit(0);
		}
	}
	
	
	public void write(String outputFile) {
		
		//Try-with-resources to clean/auto-close the resource to prevent resource leaks
		try(Writer fileWriter = new FileWriter(outputFile);) {
			//Use toString method to convert String Set to string for write method
			fileWriter.write(uniqueWords.toString());
		}
		
		//Various possible exceptions accounted for before the IOException
		//See printed error messages
		catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("\n" + "Invalid/null/empty path used, please check the path.");
			System.exit(0);
		}
		catch(FileAlreadyExistsException e) {
			e.printStackTrace();
			System.out.println("\n" + "FileWriter did not work properly, please address the issue.");
			System.exit(0);
		}
		catch(UnsupportedDataTypeException e) {
			e.printStackTrace();
			System.out.println("\n" + "Error in toString method, please address the issue.");
			System.exit(0);
		}
		//Another FileNotFound just in case
		catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("\n" + "FileWriter did not work properly, please address the issue.");
			System.exit(0);
				}
		catch(IOException ioe) {
			ioe.printStackTrace();
			System.out.println("\n" + "General IO Exception in FileWriter, please address the issue.");
			System.exit(0);
		}	
	}
}