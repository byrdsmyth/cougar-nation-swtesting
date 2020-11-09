package SpaghettiTestClasses;

/*
 * Var > Max
 * No Inheritance
 * Class < Max
 * Method > Max
 * No Parameters
 */

/*
 * This class tests the case where spaghetti code 
 * should be discovered with the following set:
 * Max Vars: 5
 * Max Class: 55
 * Max Method: 10
 */
public class MethodSC1 {
	public int myInt1 = 1;
	public int myInt2 = 1;
	public int myInt3 = 1;
	public int myInt4 = 1;
	public int myInt5 = 1;
	public int myInt6 = 1;
	public int myInt7 = 1;
	public int myInt8 = 1;
	public int myInt9 = 1;
	public int myInt10 = 1;
	public void myMethod1() {
		int newInt = myInt1 + myInt2;
		newInt = newInt + 1;
 		System.out.println(newInt);
 		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
	}
}
