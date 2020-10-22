package testCode;

/*
 * Test configuration assumes the max vairable and method count to 3
 */

public class Blob {

	public static void main(String[] args) {
		
		System.out.println("Hello, World!");
		printMessage();
		int x = 3;
		int y = 2;
		int z = x + y;
		System.out.println("z: " + z);
		System.out.println("In method go. x: " + x + " y: " + y);
		falseSwap(x,y);
		System.out.println("in method go. x: " + x + " y: " + y);
		moreParameters(x,y);
		System.out.println("in method go. x: " + x + " y: " + y);
	}
	
	public static void falseSwap(int x, int y)
	{	System.out.println("in method falseSwap. x: " + x + " y: " + y);
		int temp = x;
		x = y;
		y = temp;
		System.out.println("in method falseSwap. x: " + x + " y: " + y);
	}
	
	public static void moreParameters(int a, int b)
	{	System.out.println("in method moreParameters. a: " + a + " b: " + b);
		a = a * b;
		b = 12;
		System.out.println("in method moreParameters. a: " + a + " b: " + b);
		falseSwap(b,a);
		System.out.println("in method moreParameters. a: " + a + " b: " + b);	
	}
	 

	public static void printMessage() {
		System.out.println("We'll make this dang thing work!");
	}

	public static void printGoodbye() {
		System.out.println("Bye Y'all!");
	}

}

