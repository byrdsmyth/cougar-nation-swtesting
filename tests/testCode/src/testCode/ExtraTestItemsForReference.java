package testCode;

public class ExtraTestItemsForReference {

	public static void main(String[] args) {
		System.out.println("Hello, World!");
		printMessage();
	}

	public static void printMessage() {
		System.out.println("We'll make this dang thing work!");
	}

	public static void printGoodbye() {
		System.out.println("Bye Y'all!");
	}

	// A simple interface
	interface Player {
		final int id = 10;

		int move();

	}

}

//Defining the interface One 
interface One {
	void methodOne();
}

//Defining the interface Two 
interface Two {
	void methodTwo();
}

//Interface extending both the 
//defined interfaces 
interface Three extends One, Two {
}

//Java program to demonstrate working of instanceof 

//Creating sample classes with parent Child 
//relationship 
class Parent {
}

class Child extends Parent {
}

class Test {
	public static void main(String[] args) {
		Child cobj = new Child();

		// A simple case
		if (cobj instanceof Child)
			System.out.println("cobj is instance of Child");
		else
			System.out.println("cobj is NOT instance of Child");

		// instanceof returns true for Parent class also
		if (cobj instanceof Parent)
			System.out.println("cobj is instance of Parent");
		else
			System.out.println("cobj is NOT instance of Parent");

		// instanceof returns true for all ancestors (Note : Object
		// is ancestor of all classes in Java)
		if (cobj instanceof Object)
			System.out.println("cobj is instance of Object");
		else
			System.out.println("cobj is NOT instance of Object");
	}
}
