package starboundspawntool;

import com.beust.jcommander.JCommander;

/**
 * Generates .recipe and a partial player.config file for use in creating
 * Starbound cheaty mods.
 * 
 * Takes either a list of items, or a directory, an output directory and some
 * arguments, and saves time and wrists.
 * 
 * Syntax:
 * filelist 
 * 
 * @author narks
 */
public class StarboundSpawnTool
{
	/**
	 * @param aArgs the command line arguments
	 */
	public static void main(String[] aArgs)
	{
		// Parse command line arguments
		ToolArguments lToolArgs = new ToolArguments();
		JCommander lCLParser = new JCommander(lToolArgs, aArgs);
		
		

	}
}
