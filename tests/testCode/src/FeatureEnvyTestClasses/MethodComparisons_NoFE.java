package FeatureEnvyTestClasses;

import FeatureEnvyTestClasses.External_Class;

/* A class which tests comparing calls to methods
 * internal versus external, using this keyword and not
 * using this keyword
 */
public class MethodComparisons_NoFE {

	public String method1() {
		String firstString = "Hello";
		return firstString;
	}

	public String method2() {
		String secondString = "World";
		return secondString;
	}


	public void callMethods_NoFE_this() {
		// Create object of external class
		External_Class external = new External_Class();

		String newString1 = this.method1();
		String newString2 = this.method2();
		String newString3 = this.method1();
		String newString4 = this.method2();

		String newString5 = newString1 + newString2 + newString3 + newString4;

		external.replaceExternalString(newString5);
	}

	public void callMethods_NoFE() {
		// Create object of external class
		External_Class external = new External_Class();

		String newString1 = method1();
		String newString2 = method2();
		String newString3 = method1();
		String newString4 = method2();

		String newString5 = newString1 + newString2 + newString3 + newString4;

		external.replaceExternalString(newString5);
	}
}
