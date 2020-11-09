package FeatureEnvyTestClasses;
import FeatureEnvyTestClasses.External_Class;

/* A class which calls external variables 
 * more this than it calls internal 
 * variables using this keyword
 */
public class VariablesComparisons_NoFE {
	String myString1 = "This class calls";
	String myString2 = "its own variables using this";
	String myString3 = "More than it calls the external class.";
	
	public void callVariables_this() {
		// Create object of external class
		External_Class external = new External_Class();
		
		String newString = this.myString1 + this.myString2 + this.myString3;
		System.out.println(newString);
		
		System.out.println(external.ExternalString);
	}
	
	public void callVariables() {
		// Create object of external class
		External_Class external = new External_Class();
		
		String newString = myString1 + myString2 + myString3;
		System.out.println(newString);
		
		System.out.println(external.ExternalString);
	}
}