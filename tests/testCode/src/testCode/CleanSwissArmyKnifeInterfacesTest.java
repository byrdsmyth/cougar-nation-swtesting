package testCode;

/**
 * Test Class to ensure that Checkstyle will not flag for Swiss Army since even though there are too many
 * methods, it does not meet the criteria for too many interfaces
 * @author ryanlodermeier
 */
public class CleanSwissArmyKnifeInterfacesTest implements OneInterface, TwoInterface, ThreeInterface, 
FourInterface{

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
	
	public int m5() {
		return 0;
	}
	
	public int m6() {
		return 0;
	}

}
