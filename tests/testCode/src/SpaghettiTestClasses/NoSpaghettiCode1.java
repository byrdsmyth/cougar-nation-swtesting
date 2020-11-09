package SpaghettiTestClasses;

interface OneInterface
{
    int m1();
}

/*
 * This class tests the case where spaghetti code 
 * should not be discovered with the following set:
 * Max Vars: 5
 * Max Class: 55
 * Max Method: 10
 */
public class NoSpaghettiCode1 implements OneInterface {
	
	public int myInt1 = 1;
	public int myInt2 = 1;
	public int myInt3 = 1;
	public int myInt4 = 1;
	public int myInt5 = 1;
	public int myInt6 = 1;
	
	@Override
	public int m1() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void myMethod1() {
		int newInt = myInt1 + myInt2;
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
	}
	
	public void myMethod2() {
		int newInt = myInt1 + myInt2;
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
	}
	
	public void myMethod3() {
		int newInt = myInt1 + myInt2;
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
	}
	
	public void myMethod4() {
		int newInt = myInt1 + myInt2;
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
	}
	
	public void myMethod5() {
		int newInt = myInt1 + myInt2;
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
		System.out.println(newInt);
	}
}