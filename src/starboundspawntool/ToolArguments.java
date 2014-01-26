package starboundspawntool;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import java.io.File;

/**
 * Holds arguments from command line.
 * 
 * @author narks
 */
public class ToolArguments
{
	@Parameter(names = "-input", description = "Text file with item list",
			converter = FileConverter.class)
	private File mInputListFile;
	public File getInputListFile() { return mInputListFile; }
	
	@Parameter(names = "-output", description = "Output directory")
	private String mOutputDirectory;
	public String getOutputDirectory() { return mOutputDirectory; }
	
	@Parameter(names = "-category", 
			description = "Crafting category item goes into")
	private String mCraftingCategory;
	public String getCraftingCategory() { return mCraftingCategory; }
	
	@Parameter(names = "-crafttype",
			description = "Crafting table object name")
	private String mCraftingType;
	public String getCraftingType() { return mCraftingType; }
	
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
		
		return lValid;
	}
}
