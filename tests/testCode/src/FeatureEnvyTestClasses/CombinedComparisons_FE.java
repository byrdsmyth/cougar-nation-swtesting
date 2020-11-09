package FeatureEnvyTestClasses;

import FeatureEnvyTestClasses.External_Class;

/* A class which tests comparing calls to methods
 * internal versus external, using this keyword and not
 * using this keyword
 */
public class CombinedComparisons_FE {

	String myString1 = "This class calls";
	String myString2 = "its own variables using this";
	String myString3 = "More than it calls the external class.";

	public String method1() {
		String firstString = "Hello";
		return firstString;
	}

	public String method2() {
		String secondString = "World";
		return secondString;
	}

	public void FE_this() {
		// Create object of external class
		External_Class external = new External_Class();

		// call internal methods with this keyword 2 times
		String newString1 = this.method1();
		String newString2 = this.method2();

		// get internal variables with this keyword 1 times
		// for a total of 3 internal references
		String newString5 = this.myString1 + newString1 + newString2;
		System.out.println(newString5);
		
		// Call external methods 3 times
		boolean temp = external.returnExternalBoolean();
		external.replaceExternalString(newString5);
		external.printExternalString();
		// Use external Variables 2 times
		// for a total of 5 external references
		System.out.println(external.ExternalString);
		System.out.println(external.ExternalInt);
		System.out.println(temp);
	}

	public void FE() {
		// Create object of external class
		External_Class external = new External_Class();

		// call internal methods without this keyword 2 times
		String newString1 = method1();
		String newString2 = method2();

		// get internal variables without this keyword 1 times
		// for a total of 3 internal references
		String newString5 = myString1 + newString1 + newString2;
		System.out.println(newString5);
		
		// Call external methods 3 times
		boolean temp = external.returnExternalBoolean();
		external.replaceExternalString(newString5);
		external.printExternalString();
		// Use external Variables 2 times
		// for a total of 5 external references
		System.out.println(external.ExternalString);
		System.out.println(external.ExternalInt);
		System.out.println(temp);
	}
}
