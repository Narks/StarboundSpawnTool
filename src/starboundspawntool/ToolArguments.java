package starboundspawntool;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Holds arguments from command line.
 * 
 * @author narks
 */
public class ToolArguments
{
	@Parameter(names = "-input", description = "Text file with item list",
			converter = FileConverter.class, required = true)
	private File mInputListFile;
	public File getInputListFile() { return mInputListFile; }
	
	@Parameter(names = "-output", description = "Output directory", required = true)
	private String mOutputDirectoryPath = "";
	public Path getOutputDirectoryPath() 
	{
		return Paths.get(mOutputDirectoryPath);
	}
	
	@Parameter(names = "-category", 
			description = "Crafting category item goes into", required = true)
	private String mCraftingCategory;
	public String getCraftingCategory() { return mCraftingCategory; }
	
	@Parameter(names = "-crafttype",
			description = "Crafting table object name", required = true)
	private String mCraftingType;
	public String getCraftingType() { return mCraftingType; }
	
	@Parameter(names = "-costdebug",
			description = "Makes the pixel cost of every item crafted different")
	private Boolean mCostDebug = false;
	public Boolean getCostDebug() { return mCostDebug; }
	
	/**
	 * Validates the arguments, printing any issues to System.out then returning 
	 * success or failure of validation.
	 * @return true if validation success, false otherwise.
	 */
	public boolean validate()
	{
		boolean lValid = true;
		
		if (getInputListFile().canRead() == false)
		{
			System.out.println("Cannot read input list file: \"" 
					+ getInputListFile().getAbsolutePath()
					+ "\"");
			lValid = false;
		}
		
		/*
		File lTestFile = new File(getOutputDirectoryPath());
		if (lTestFile.canWrite() == false)
		{
			System.out.println("Cannot write to output directory: \"" 
					+ lTestFile.getAbsolutePath()
					+ "\"");con
			lValid = false;
		}
		*/
		
		return lValid;
	}
}
