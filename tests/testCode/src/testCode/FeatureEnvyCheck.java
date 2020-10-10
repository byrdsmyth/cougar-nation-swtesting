package testCode;

import testCode.BicycleForFeatureEnvy;
import testCode.BikeForFeatureEnvy;

class FeatureEnvyCheck {

	public static void main(String[] args) {

		// creating an inatance of Bicycle
		// doing some operations
		BicycleForFeatureEnvy bicycle = new BicycleForFeatureEnvy();
		bicycle.changeGear(2);
		bicycle.speedUp(3);
		bicycle.applyBrakes(1);

		System.out.println("Bicycle present state :");
		bicycle.printStates();

		// creating instance of the bike.
		BikeForFeatureEnvy bike = new BikeForFeatureEnvy();
		bike.changeGear(1);
		bike.speedUp(4);
		bike.applyBrakes(3);

		System.out.println("Bike present state :");
		bike.printStates();

		int arr[] = new int[3];
	}
}
