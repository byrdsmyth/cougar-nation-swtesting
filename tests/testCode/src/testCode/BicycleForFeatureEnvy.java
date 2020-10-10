package testCode;

interface Vehicle {

	// all are the abstract methods.
	void changeGear(int a);

	void speedUp(int a);

	void applyBrakes(int a);
}

class BicycleForFeatureEnvy implements Vehicle {

	public int speed;
	public int gear;

	// to change gear
	@Override
	public void changeGear(int newGear) {

		gear = newGear;
	}

	// to increase speed
	@Override
	public void speedUp(int increment) {

		speed = speed + increment;
	}

	// to decrease speed
	@Override
	public void applyBrakes(int decrement) {

		speed = speed - decrement;
	}

	public void printStates() {
		System.out.println("speed: " + speed + " gear: " + gear);
	}
}
