package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.SwissArmyKnifeCheckD3;
import net.sf.eclipsecs.sample.checks.TypeCheckD3;
import net.sf.eclipsecs.sample.checks.TypeCheckingCheck;

import org.powermock.api.mockito.PowerMockito;
import org.powermock.api.mockito.verification.PrivateMethodVerification;
import org.powermock.core.classloader.annotations.PrepareForTest;

//These are needed to use PowerMock/EasyMocks
@PrepareForTest(SwissArmyCheckKnifeCheckD3Test.class)
public class SwissArmyCheckKnifeCheckD3Test {

	@Test
    public void testSetMax() {
		SwissArmyKnifeCheckD3 check = new SwissArmyKnifeCheckD3();
		check.setMax(2);
		assertEquals(check.getMax(), 2);
		assertNotEquals(check.getMax(), 3);
    }

	@Test
    public void testGetMax() {
		SwissArmyKnifeCheckD3 check = new SwissArmyKnifeCheckD3();
		assertEquals(check.getMax(), 3);
		assertNotEquals(check.getMax(), 2);
    }

    @Test
    public void testGetDefaultTokens() {
    	SwissArmyKnifeCheckD3 tc = PowerMockito.spy(new SwissArmyKnifeCheckD3());
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
			if(tokens[i] == TokenTypes.CLASS_DEF) {
				assertTrue(true);
			} else {
				fail();
			}
		}
    	
    }

    @Test
    public void testGetAcceptableTokens() {
    	SwissArmyKnifeCheckD3 check = new SwissArmyKnifeCheckD3();
    	int[] tokens = check.getDefaultTokens();
    	for(int i = 0; i < tokens.length; i++) {
    		if(tokens[i] == TokenTypes.CLASS_DEF) {
    			assertTrue(true);
    		} else {
    			fail();
    		}
    	}
    	
    }

    @Test
    public void testGetRequiredTokens() {
    	SwissArmyKnifeCheckD3 tc = PowerMockito.spy(new SwissArmyKnifeCheckD3());
  		int [] tokens = tc.getRequiredTokens();
		try {
			//Verify that the inner method is called
			PrivateMethodVerification privateMethodInvocation = PowerMockito.verifyPrivate(tc);
			privateMethodInvocation.invoke("getAcceptableTokens");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = 0; i < tokens.length; i++) {
			if(tokens[i] == TokenTypes.CLASS_DEF) {
				assertTrue(true);
			} else {
				fail();
			}
		}
 
    }

    @Test
    public void visitToken() {
    	SwissArmyKnifeCheckD3 check = new SwissArmyKnifeCheckD3();
    	DetailAST rootAST = new DetailAST();
    	DetailAST child1AST = new DetailAST();
    	DetailAST child2AST = new DetailAST();
    	
    	rootAST.setType(TokenTypes.IMPLEMENTS_CLAUSE);
    	child1AST.setType(TokenTypes.IDENT);
    	child2AST.setType(TokenTypes.IDENT);
    	
    	rootAST.addChild(child1AST);
    	rootAST.addChild(child2AST);
    	
    	check.visitToken(rootAST);
    	
    }
}
