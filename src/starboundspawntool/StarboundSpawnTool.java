package starboundspawntool;

import com.beust.jcommander.JCommander;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Path;
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
			File lPlayerConfigOutput = lToolArgs.getOutputDirectoryPath()
				.resolve("player.config").toFile();
			lPlayerConfigOutput.getParentFile().mkdirs();
			lPlayerConfigOutput.createNewFile();
			
			// Read input items, write to partial player.config
			Writer lConfigWriter = new BufferedWriter(
						new OutputStreamWriter(
						new FileOutputStream(lPlayerConfigOutput), "utf-8"));
			
			Scanner lScanner = new Scanner(lToolArgs.getInputListFile());
			while (lScanner.hasNext())
			{
				String lItemName = lScanner.next();
				lItemList.add(lItemName);
				lConfigWriter.write("				{ \"item\" : \"" 
						+ lItemName + "\" },\n");
			}
			lScanner.close();
			lConfigWriter.close();
			
			for (String lItem : lItemList)
			{
				Path lOutputPath =
						lToolArgs.getOutputDirectoryPath()
						.resolve(RECIPE_PREFIX + lItem + ".recipe");
				
				// Write output recipe
				Writer lWriter = new BufferedWriter(
						new OutputStreamWriter(
						new FileOutputStream(lOutputPath.toFile()), "utf-8"));
				
				lWriter.write(
						"{" + "\n"
						+ "\"input\" : [{ \"item\" : \"money\", \"count\" : 1 }]," + "\n"
						+ "\"output\" : { \"item\" : \"" + lItem + "\", \"count\" : 1}," + "\n"
						+ "\"groups\" : [ \"" 
						+ lToolArgs.getCraftingType() + "\", \"" 
						+ lToolArgs.getCraftingCategory() + "\", \"all\" ]" + "\n"
						+ "}" + "\n");
						
				lWriter.close();
			}
		}
		catch (Exception lException)
		{
			System.out.println("ERROR: " + lException.getMessage());
			lException.printStackTrace();
			return;
		}

	}
}
