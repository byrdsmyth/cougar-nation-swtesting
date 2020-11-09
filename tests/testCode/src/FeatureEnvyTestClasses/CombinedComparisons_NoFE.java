package FeatureEnvyTestClasses;

import FeatureEnvyTestClasses.External_Class;

/* A class which tests comparing calls to methods
 * internal versus external, using this keyword and not
 * using this keyword
 */
public class CombinedComparisons_NoFE {

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

	public void NoFE_this() {
		// Create object of external class
		External_Class external = new External_Class();

		// call internal methods with this keyword 4 times
		String newString1 = this.method1();
		String newString2 = this.method2();
		String newString3 = this.method1();
		String newString4 = this.method2();

		// get internal variables with this keyword 3 times
		// for a total of 7 internal references
		String newString5 = this.myString1 + this.myString2 + this.myString3 + newString1 + newString2 + newString3
				+ newString4;
		System.out.println(newString5);
		// refer to external method 1 time
		external.replaceExternalString(newString5);
		//refer to external variable 1 time
		// for a total of 2 external references
		System.out.println(external.ExternalString);
	}

	public void NoFE() {
		// Create object of external class
		External_Class external = new External_Class();

		// call internal methods without this keyword 4 times
		String newString1 = method1();
		String newString2 = method2();
		String newString3 = method1();
		String newString4 = method2();

		// get internal variables without this keyword 3 times
		// for a total of 7 internal references
		String newString5 = myString1 + myString2 + myString3 + newString1 + newString2 + newString3
				+ newString4;
		System.out.println(newString5);
		// refer to external method 1 time
		external.replaceExternalString(newString5);
		//refer to external variable 1 time
		// for a total of 2 external references
		System.out.println(external.ExternalString);
	}
	
	public void NoFE_EqualThis() {
		// Create object of external class
		External_Class external = new External_Class();

		// call internal methods with this keyword 2 times
		String newString1 = this.method1();
		String newString2 = this.method2();

		// get internal variables with this keyword 1 times
		// for a total of 3 internal references
		String newString5 = this.myString1 + newString1 + newString2;
		System.out.println(newString5);
		// refer to external method 1 time
		external.replaceExternalString(newString5);
		//refer to external variable 2 time
		// for a total of 3 external references
		System.out.println(external.ExternalString);
		System.out.println(external.ExternalBoolean);
	}
	
	public void NoFE_Equal() {
		// Create object of external class
		External_Class external = new External_Class();

		// call internal methods without this keyword 2 times
		String newString1 = method1();
		String newString2 = method2();

		// get internal variables without this keyword 1 times
		// for a total of 3 internal references
		String newString5 = myString1 + newString1 + newString2;
		System.out.println(newString5);
		// refer to external method 1 time
		external.replaceExternalString(newString5);
		//refer to external variable 2 time
		// for a total of 3 external references
		System.out.println(external.ExternalString);
		System.out.println(external.ExternalBoolean);
	}
}
