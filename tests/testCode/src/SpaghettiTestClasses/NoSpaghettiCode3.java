package SpaghettiTestClasses;

/*
 * Var < Max
 * Yes Inheritance
 * Class < Max
 * Method < Max
 * No Parameters
 */

/*
 * This class tests the case where spaghetti code 
 * should not be discovered with the following set:
 * Max Vars: 5
 * Max Class: 55
 * Max Method: 10
 */
public class NoSpaghettiCode3 {
	public int myInt1 = 1;
	public int myInt2 = 1;
	public int myInt3 = 1;
	public int myInt4 = 1;
	public int myInt5 = 1;

	public void myMethod1(int temp1, int temp2) {
		int newInt = myInt1 + myInt2;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
 		System.out.println(newInt);
	}
	
	public void myMethod2(int temp1, int temp2) {
		int newInt = myInt1 + myInt2;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
 		System.out.println(newInt);
	}
	
	public void myMethod3(int temp1, int temp2) {
		int newInt = myInt1 + myInt2;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
 		System.out.println(newInt);
	}
	
	public void myMethod4(int temp1, int temp2) {
		int newInt = myInt1 + myInt2;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
 		System.out.println(newInt);
	}
	
	public void myMethod5(int temp1, int temp2) {
		int newInt = myInt1 + myInt2;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
		newInt = newInt + 1;
 		System.out.println(newInt);
	}
	
}
