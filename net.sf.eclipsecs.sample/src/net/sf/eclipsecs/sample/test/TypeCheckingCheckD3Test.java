package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CheckUtils;

import net.sf.eclipsecs.sample.checks.TypeCheckD3;
import net.sf.eclipsecs.sample.checks.TypeCheckingCheck;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.verification.PrivateMethodVerification;
import org.powermock.core.classloader.annotations.PrepareForTest;

//These are needed to use PowerMock/EasyMocks
@PrepareForTest(TypeCheckingCheckD3Test.class)
public class TypeCheckingCheckD3Test {
	//Max is set to 3 for type checking

	@Test
    public void testSetMax() {
		TypeCheckD3 check = new TypeCheckD3();
		check.setMax(3);
		assertEquals(check.getMax(), 3);
		assertNotEquals(check.getMax(), 2);
		ReflectionTestUtils.setField(check, "max", 1);
		assertEquals(check.getMax(), 1);
		assertNotEquals(check.getMax(), 2);
    }
	
	@Test
    public void TestGetMax() {
		TypeCheckD3 check = new TypeCheckD3();
		assertEquals(check.getMax(), 3);
    }

	@Test
    public void TestSetDepth() {
		//This test test's both the setter and getter of the depth data member
		TypeCheckD3 check = new TypeCheckD3();
		check.setDepth(4);
		assertEquals(check.getDepth(), 4);
		assertNotEquals(check.getDepth(), 2);
		
		ReflectionTestUtils.setField(check, "depth", 1);
		assertEquals(check.getDepth(), 1);
		assertNotEquals(check.getDepth(), 2);
		
    }

    @Test
    public void getDefaultTokens() {
    	TypeCheckD3 tc = PowerMockito.spy(new TypeCheckD3());
  		int [] tokens = tc.getDefaultTokens();
		try {
			//Verify that the inner method is called
			PrivateMethodVerification privateMethodInvocation = PowerMockito.verifyPrivate(tc);
			privateMethodInvocation.invoke("getAcceptableTokens");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < tokens.length; i++) {
			if(tokens[i] == TokenTypes.LITERAL_SWITCH || tokens[i] == TokenTypes.LITERAL_IF) {
				assertTrue(true);
			} else {
				fail();
			}
		}
    	
    }

    @Test
    public void getAcceptableTokens() {
    	TypeCheckD3 check = new TypeCheckD3();
    	int [] tokens = check.getDefaultTokens();
    	for (int i = 0; i < tokens.length; i++) {
			if(tokens[i] == TokenTypes.LITERAL_SWITCH || tokens[i] == TokenTypes.LITERAL_IF) {
				assertTrue(true);
			} else {
				fail();
			}
		}
    }

    @Test
    public void getRequiredTokens() {
    	TypeCheckD3 tc = PowerMockito.spy(new TypeCheckD3());
  		int [] tokens = tc.getRequiredTokens();
		try {
			//Verify that the inner method is called
			PrivateMethodVerification privateMethodInvocation = PowerMockito.verifyPrivate(tc);
			privateMethodInvocation.invoke("getRequiredTokens");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < tokens.length; i++) {
			if(tokens[i] == TokenTypes.LITERAL_SWITCH || tokens[i] == TokenTypes.LITERAL_IF) {
				assertTrue(true);
			} else {
				fail();
			}
		}
    }

    @Test
    public void beginTree() {
    	TypeCheckD3 check = new TypeCheckD3();
    	DetailAST root = new DetailAST();
    	check.beginTree(root);
    	assertEquals(check.getDepth(), 0);
    	assertNotEquals(check.getDepth(), 1);
    	
    }

    @Test
    public void visitToken() {
    	TypeCheckD3 check = new TypeCheckD3();
    	
    	DetailAST switchAST = new DetailAST();
    	switchAST.setType(TokenTypes.LITERAL_SWITCH);
    	check.visitToken(switchAST);
    	
    	DetailAST ifAST = new DetailAST();
    	ifAST.setType(TokenTypes.LITERAL_IF);
    	check.visitToken(ifAST);
    }

    @Test
    public void leaveToken() {
    	TypeCheckD3 check = new TypeCheckD3();
    	check.setDepth(1);
    	
    	DetailAST ifAST = new DetailAST();
    	ifAST.setType(TokenTypes.LITERAL_IF);
    	check.leaveToken(ifAST);
    	
    	assertEquals(check.getDepth(), 0);
    }
	
}
