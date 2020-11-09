package FeatureEnvyTestClasses;

import FeatureEnvyTestClasses.External_Class;

/* A class which tests comparing calls to methods
 * internal versus external, using this keyword and not
 * using this keyword
 */
public class MethodComparisonsFE {

	public String method1() {
		String firstString = "Hello";
		return firstString;
	}

	public String method2() {
		String secondString = "World";
		return secondString;
	}

	public void callMethodsFE_This() {
		// Create object of external class
		External_Class external = new External_Class();

		String newString1 = this.method1();
		String newString2 = this.method2();

		String newString3 = newString1 + newString2;

		external.printExternalString();
		external.replaceExternalString(newString3);
		external.printExternalString();
	}

	public void callMethodsFE() {
		// Create object of external class
		External_Class external = new External_Class();

		String newString1 = method1();
		String newString2 = method2();

		String newString3 = newString1 + newString2;

		external.printExternalString();
		external.replaceExternalString(newString3);
		external.printExternalString();
	}
}
