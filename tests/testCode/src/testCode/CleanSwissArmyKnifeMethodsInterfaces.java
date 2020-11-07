package testCode;

/**
 * Test Class to confirm that SwissArmyKnife will not detect a code smell since the class does not
 * meet the threshold for either interfaces or methods
 * @author ryanlodermeier
 */
public class CleanSwissArmyKnifeMethodsInterfaces implements OneInterface, TwoInterface, ThreeInterface{

	@Override
	public float m3() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float m2() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int m1() {
		// TODO Auto-generated method stub
		return 0;
	}

}
