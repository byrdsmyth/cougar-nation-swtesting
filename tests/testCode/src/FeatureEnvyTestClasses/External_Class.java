package FeatureEnvyTestClasses;

public class External_Class {
	
	String ExternalString = "ABCDEFG";
	int ExternalInt = 1;
	boolean ExternalBoolean = false;
	
	public boolean returnExternalBoolean() {
		return ExternalBoolean;
	}
	
	public String replaceExternalString(String newString) {
		return ExternalString = newString;
	}
	
	public void printExternalString() {
		System.out.println(ExternalString);
	}

}
