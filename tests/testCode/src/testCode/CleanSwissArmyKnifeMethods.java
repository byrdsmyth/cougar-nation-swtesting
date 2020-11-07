package testCode;

/**
 * Test Class to ensure that Checkstyle will not flag for Swiss Army since even though there are too many
 * Interfaces, it does not meet the criteria for too many methods
 * @author ryanlodermeier
 */
public class CleanSwissArmyKnifeMethods implements OneInterface, TwoInterface, ThreeInterface, 
FourInterface, MockInterface1, MockInterface2{

	@Override
	public float m4() {
		// TODO Auto-generated method stub
		return 0;
	}

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
