package starboundspawntool;

import com.beust.jcommander.JCommander;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Generates .recipe and a partial player.config file for use in creating
 * Starbound cheaty mods.
 * 
 * Takes a list of items, an output directory and some
 * arguments, and saves time and wrists.
 * 
 * Syntax:
 * filelist 
 * 
 * @author narks
 */
public class StarboundSpawnTool
{
	private static final String RECIPE_PREFIX = "ILC_";
	
	/**
	 * @param aArgs the command line arguments
	 */
	public static void main(String[] aArgs)
	{
		// Parse command line arguments
		ToolArguments lToolArgs = new ToolArguments();
		JCommander lCLParser;
		
		try { lCLParser = new JCommander(lToolArgs, aArgs); }
		catch (Exception lException)
		{
			System.out.println(lException.getMessage());
			new JCommander(lToolArgs).usage();
			return;
		}
		
		// Validate arguments
		if (lToolArgs.validate() == false)
		{
			lCLParser.usage();
			return;
		}

		
		
		// Make magic happen
		List<String> lItemList = new ArrayList<String>();
		try
		{
			// Read input items
			Scanner lScanner = new Scanner(lToolArgs.getInputListFile());
			while (lScanner.hasNext())
			{
				lItemList.add(lScanner.next());
			}
			lScanner.close();
			
			for (String lItem : lItemList)
			{
				// Write output recipe
				Writer lWriter = new BufferedWriter(
						new OutputStreamWriter(
						new FileOutputStream(
							lToolArgs.getOutputDirectoryPath().toAbsolutePath()
							+ RECIPE_PREFIX
							+ lItem
							), "utf-8"));
				
				lWriter.write("hello world");
				
				lWriter.close();
			}
		}
		catch (Exception lException)
		{
			System.out.println(lException.getMessage());
			return;
		}
		// Open input file
		
		
		// For each line in input file, create an output file
		// 
	}
}
