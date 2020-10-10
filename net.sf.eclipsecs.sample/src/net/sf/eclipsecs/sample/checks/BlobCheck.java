package net.sf.eclipsecs.sample.checks;

import java.io.File;

import com.puppycrawl.tools.checkstyle.api.AbstractFileSetCheck;
import com.puppycrawl.tools.checkstyle.StatelessCheck;
import com.puppycrawl.tools.checkstyle.api.FileText;

/*
 * The Blob, also named God Class, is a class implementing different responsibilities,
 * generally characterized by the presence of a high number of attributes and methods, which
 * implement different functionalities, and by many dependencies with data classes (i.e., classes
 * implementing only getter and setter methods) [24].
 */
@StatelessCheck
public class BlobCheck extends AbstractFileSetCheck {

	//FileLength FL = new FileLength();
	
	//class FileLength extends AbstractFileSetCheck {
		
		//Message to display in log file
		public static final String message = "Long Source File";
		
		private int max = 50; //Max number of lines allowed
		
		public void processFiltered(File file, FileText fText)
		{
			if(fText.size() > max)
			{
				log(1, message, fText.size(), max);
			}
		}
		public void setMax(int length) {
		        max = length;
		}
	//}
}
