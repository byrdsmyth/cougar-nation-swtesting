package net.sf.eclipsecs.sample.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.Before;
import org.junit.Test;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;


import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Method; 
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor; 
import antlr.CommonHiddenStreamToken;

import static com.puppycrawl.tools.checkstyle.checks.naming.AbstractNameCheck.MSG_INVALID_PATTERN;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.Configuration;
import com.puppycrawl.tools.checkstyle.api.Context;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.checks.blocks.LeftCurlyCheck;
import com.puppycrawl.tools.checkstyle.checks.coding.EmptyStatementCheck;
import com.puppycrawl.tools.checkstyle.checks.coding.HiddenFieldCheck;
import com.puppycrawl.tools.checkstyle.checks.indentation.CommentsIndentationCheck;
import com.puppycrawl.tools.checkstyle.checks.javadoc.JavadocPackageCheck;
import com.puppycrawl.tools.checkstyle.checks.javadoc.JavadocParagraphCheck;
import com.puppycrawl.tools.checkstyle.checks.naming.ConstantNameCheck;
import com.puppycrawl.tools.checkstyle.checks.naming.MemberNameCheck;
import com.puppycrawl.tools.checkstyle.checks.naming.TypeNameCheck;
import com.puppycrawl.tools.checkstyle.filters.SuppressWithNearbyCommentFilter;
import com.puppycrawl.tools.checkstyle.filters.SuppressionCommentFilter;
import com.puppycrawl.tools.checkstyle.filters.SuppressionXpathFilter;

import java.util.ArrayList;

import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.FeatureEnvyCheck;
import net.sf.eclipsecs.sample.checks.TypeCheckingCheck;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

import net.sf.eclipsecs.sample.checks.FeatureEnvyCheck;

public class FeatureEnvyCheckTest {

    public DetailAST tree;
    public FeatureEnvyCheck tester;
    public FeatureEnvyCheck mockTester;
    public FeatureEnvyCheck spyTester;
    public int[] acceptableTokens;
    public int[] defaultTokens;
    public int[] unacceptableTokens;
    public int[] requiredTokens;
    ArrayList<Integer> tokenArr;

    /**
     * Set up a mock instance of the Spaghetti Code Checker Including the acceptable
     * and required tokens, saved to arrays
     */
    @Before
    public void setUp() throws Exception {
        acceptableTokens = new int[0];
        defaultTokens = new int[] { TokenTypes.METHOD_DEF };
        unacceptableTokens = new int[] { TokenTypes.ABSTRACT, TokenTypes.ANNOTATION, TokenTypes.ANNOTATION_ARRAY_INIT,
                TokenTypes.ANNOTATION_DEF, TokenTypes.ANNOTATION_FIELD_DEF, TokenTypes.ANNOTATION_MEMBER_VALUE_PAIR,
                TokenTypes.ANNOTATIONS, TokenTypes.ARRAY_DECLARATOR, TokenTypes.ARRAY_INIT, TokenTypes.ASSIGN,
                TokenTypes.AT };
        requiredTokens = new int[0];

        tree = new DetailAST();

        // What is this?
        // tree.setLineNo(42);

        tester = new FeatureEnvyCheck();
        mockTester = mock(FeatureEnvyCheck.class);
        spyTester = spy(tester);

        tokenArr = new ArrayList<Integer>();
        tokenArr.add(TokenTypes.METHOD_DEF);
    }

    /**
     * This section contains basic unit tests for the getters and setters in the
     * AbstractCheck Class
     */
    @Test
    public void testGetDefaultTokens() {
        assertArrayEquals(defaultTokens, spyTester.getDefaultTokens());
        verify(spyTester, times(1)).getDefaultTokens();
    }

    @Test
    public void testGetNotDefaultTokens() {
        assertNotEquals(unacceptableTokens, spyTester.getDefaultTokens());
        verify(spyTester, times(1)).getDefaultTokens();
    }

    @Test
    public void testGetNullDefaultTokens() {
        assertNotEquals(null, spyTester.getDefaultTokens());
        verify(spyTester, times(1)).getDefaultTokens();
    }

    @Test
    public void testGetDefaultTokensMock() {
        when(mockTester.getDefaultTokens()).thenReturn(new int[] { TokenTypes.METHOD_DEF });
        assertArrayEquals(defaultTokens, mockTester.getDefaultTokens());
        verify(mockTester, times(1)).getDefaultTokens();
    }

    @Test
    public void testGetNotDefaultTokensMock() {
        when(mockTester.getDefaultTokens())
                .thenReturn(new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN });
        assertNotEquals(unacceptableTokens, mockTester.getDefaultTokens());
        verify(mockTester, times(1)).getDefaultTokens();
    }

    @Test
    public void testGetNullDefaultTokensMock() {
        when(mockTester.getDefaultTokens())
                .thenReturn(new int[] { TokenTypes.SINGLE_LINE_COMMENT, TokenTypes.BLOCK_COMMENT_BEGIN });
        assertNotEquals(null, mockTester.getDefaultTokens());
        verify(mockTester, times(1)).getDefaultTokens();
    }

    @Test
    public void testGetRequiredTokens() {
        assertArrayEquals(requiredTokens, spyTester.getRequiredTokens());
        verify(spyTester, times(1)).getRequiredTokens();
    }

    @Test
    public void testGetUnrequiredTokens() {
        assertNotEquals(unacceptableTokens, spyTester.getRequiredTokens());
        verify(spyTester, times(1)).getRequiredTokens();
    }

    @Test
    public void testGetNullRequiredTokens() {
        assertNotEquals(null, spyTester.getRequiredTokens());
        verify(spyTester, times(1)).getRequiredTokens();
    }

    @Test
    public void testGetRequiredTokensMock() {
        when(mockTester.getRequiredTokens()).thenReturn(new int[0]);
        assertArrayEquals(requiredTokens, mockTester.getRequiredTokens());
        verify(mockTester, times(1)).getRequiredTokens();
    }

    @Test
    public void testGetUnrequiredTokensMock() {
        when(mockTester.getRequiredTokens()).thenReturn(new int[0]);
        assertNotEquals(unacceptableTokens, mockTester.getRequiredTokens());
        verify(mockTester, times(1)).getRequiredTokens();
    }

    @Test
    public void testGetNullRequiredTokensMock() {
        when(mockTester.getRequiredTokens()).thenReturn(new int[0]);
        assertNotEquals(null, mockTester.getRequiredTokens());
        verify(mockTester, times(1)).getRequiredTokens();
    }

    @Test
    public void testGetAcceptableTokens() {
        assertArrayEquals(acceptableTokens, spyTester.getAcceptableTokens());
        verify(spyTester, times(1)).getAcceptableTokens();
    }

    @Test
    public void testGetUnacceptableTokens() {
        assertNotEquals(unacceptableTokens, spyTester.getAcceptableTokens());
        verify(spyTester, times(1)).getAcceptableTokens();
    }

    @Test
    public void testGetNullAcceptableTokens() {
        assertNotEquals(null, spyTester.getAcceptableTokens());
        verify(spyTester, times(1)).getAcceptableTokens();
    }

    @Test
    public void testGetAcceptableTokensMock() {
        when(mockTester.getAcceptableTokens()).thenReturn(new int[0]);
        assertArrayEquals(acceptableTokens, mockTester.getAcceptableTokens());
        verify(mockTester, times(1)).getAcceptableTokens();
    }

    @Test
    public void testGetUnacceptableTokensMock() {
        when(mockTester.getAcceptableTokens()).thenReturn(new int[] { TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF,
                TokenTypes.IMPLEMENTS_CLAUSE, TokenTypes.EXTENDS_CLAUSE, TokenTypes.CLASS_DEF });
        assertNotEquals(unacceptableTokens, mockTester.getAcceptableTokens());
        verify(mockTester, times(1)).getAcceptableTokens();
    }

    @Test
    public void testGetNullAcceptableTokensMock() {
        when(mockTester.getAcceptableTokens()).thenReturn(new int[] { TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF,
                TokenTypes.IMPLEMENTS_CLAUSE, TokenTypes.EXTENDS_CLAUSE, TokenTypes.CLASS_DEF });
        assertNotEquals(null, mockTester.getAcceptableTokens());
        verify(mockTester, times(1)).getAcceptableTokens();
    }

    /**
     * This section contains tests of the more custom functions, specific to this
     * particular check
     */
    @Test
    public void testVisitToken() {
        //testGetChildCount()
        // Need to mock a call to checkSiblings - replace later
        // for integration testing
        PowerMock.mockStaticPartial(FeatureEnvyCheck.class, "checkSiblings");
        EasyMock.expect(FeatureEnvyCheck.checkSiblings(mockAST, 3)).andReturn();
        PowerMock.replayAll();
        assertFalse(Date.visitToken(29,02,2017));
        assertTrue(Date.visitToken(31,01,2017));
        assertFalse(Date.visitToken(31,06,2017));
    }
    
    @Test
    public void testGetChildCount() {
        FeatureEnvyCheck fe = new FeatureEnvyCheck();
        DetailAST methodAST = mockAST(TokenTypes.METHOD_DEF, null, null, 0, 0);
        // Need to mock a call to getChildCount - out of my control
        // Will use pre-created DetailAST 'tree'
        ReflectionTestUtils.setField(methodAST, "childCount", 3);
        assertEquals(3,methodAST.getChildCount());
    }
    
    @Test
    public void testCheckSiblings() {
        FeatureEnvyCheck fe = new FeatureEnvyCheck();
        DetailAST methodAST = mockAST(TokenTypes.METHOD_DEF, null, null, 0, 0);
        // Need to mock a call to countInstances
        Dictionary<String, Integer> classFeatures = new Hashtable<>();
        classFeatures.put("none", 1);
        
        ReflectionTestUtils.setField(fe, "switches", 1);
        ReflectionTestUtils.invokeMethod(tc,"checkForSwitchStatements", ast);   
        PowerMock.mockStaticPartial(FeatureEnvyCheck.class, "countInstances");
        EasyMock.expect(FeatureEnvyCheck.countInstances(mockAST, 3)).andReturn();
        PowerMock.replayAll();
        assertFalse(Date.validCombination(29,02,2017));
        assertTrue(Date.validCombination(31,01,2017));
        assertFalse(Date.validCombination(31,06,2017));
        //replace later
        // for integration testing
    }
    
    @Test
    public void testCountInstances() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        DetailAST childAST = mockAST(TokenTypes.EXPR, "testEXPR", "testString2", 0, 0);
        //let's create an empty dictionary
        Dictionary<String, Integer> tempDict = new Hashtable<>();
        // We need to add to the dict at least one "this"
        tempDict.put("this", 1);
        // also let's add two references to bicycle
        tempDict.put("bicycle", 2);
        
        // we need to mock up getLineNumber
        //doReturn(100).when(childAST).getLineNo();
        // and probably mock up log()
        //doReturn("feature envy found").when(spyTester).log(100, "feature envy found");
        
        // and see if we get to the log, so call fxn
        Method countInstancesMethod
        = FeatureEnvyCheck.class.getDeclaredMethod("countInstances", Hashtable.class, DetailAST.class);
   
        FeatureEnvyCheck operationsInstance = new FeatureEnvyCheck();
        Double result = (Double) countInstancesMethod.invoke(operationsInstance, tempDict, childAST);
    
        // now add two more ref to this
        tempDict.put("this", 2);
        // and call fxn again
    }
    
    @Test
    public void testCheckSLIST() {
        DetailAST exprAST = mockAST(TokenTypes.EXPR, "testEXPR", "testString2", 0, 0);
        DetailAST methodAST = mockAST(TokenTypes.METHOD_CALL, null, null, 0, 0);
        DetailAST identAST = mockAST(TokenTypes.IDENT, "bicycle", null, 0, 0);
        DetailAST dotAST = mockAST(TokenTypes.DOT, null, null, 0, 0);
        
        FeatureEnvyCheck fe = new FeatureEnvyCheck();
        exprAST.addChild(methodAST);
        System.out.println(exprAST.getType());
        System.out.println(methodAST.getType());
        System.out.println(exprAST.getFirstChild());
        assertEquals(TokenTypes.METHOD_CALL,methodAST.getType());
        
        methodAST.addChild(identAST);
        assertEquals(TokenTypes.IDENT,methodAST.getFirstChild().getType());
        
        assertEquals("this", spyTester.checkSLIST(exprAST));
        
        methodAST.setFirstChild(dotAST);
        assertEquals(TokenTypes.DOT,methodAST.getFirstChild().getType());
        
        dotAST.addChild(identAST);
        assertEquals(TokenTypes.IDENT, dotAST.getFirstChild().getType());
        assertEquals("bicycle", identAST.getText());
        
        assertEquals("bicycle", spyTester.checkSLIST(exprAST));
        
        // now we need to reach the "none" line
        assertEquals("none", spyTester.checkSLIST(dotAST));
    }
    
    @Test 
    public void testFindClassCalls() {
        DetailAST siblingAST = mockAST(TokenTypes.SLIST, "sibling", null, 0, 0);
        DetailAST siblingChildAST = mockAST(TokenTypes.EXPR, "firstChild", null, 0, 0);
        DetailAST siblingChild2AST = mockAST(TokenTypes.EXPR, "childSibling", null, 0, 0);
        Dictionary<String, Integer> classFeatures = new Hashtable<>();
        
        // need to mock up checking input AST for null
        assertFalse(siblingAST == null);
        // need to mock up checking that type = SLIST
        assertEquals(TokenTypes.SLIST, siblingAST.getType());       
        // need to add a child
        siblingAST.addChild(siblingChildAST);
        // need to mock up checking first child for null
        assertFalse(siblingChildAST == null);
        // need to mock up getting first child
        assertEquals(TokenTypes.EXPR,siblingAST.getFirstChild().getType());
        // Before Adding anythng to our node values...
        //let's create an empty dictionary
        Dictionary<String, Integer> emptyDict = new Hashtable<>();
        
        //let's create a dictionary with "none": 0, "this":1
        Dictionary<String, Integer> thisDict = new Hashtable<>();
        thisDict.put("this", 1);
        // need to mock up adding to dictionary
        doReturn("this").when(spyTester).checkSLIST(siblingChildAST);
        //let's call findclasscalls and assert expected results
        assertEquals(thisDict, spyTester.findClassCalls(siblingAST, emptyDict));
        
        //let's create a dictionary with "none":1, "this":0
        Dictionary<String, Integer> noneDict = new Hashtable<>();
        // need to mock up testing if a string is in the dictionary
        assertFalse(((Hashtable<String, Integer>) noneDict).containsKey("none"));
        //now add
        noneDict.put("none", 1);
        // and test again
        assertTrue(((Hashtable<String, Integer>) noneDict).containsKey("none"));
        // need to mock up adding to dictionary
        doReturn("none").when(spyTester).checkSLIST(siblingChildAST);
        //now try calling the function and see if we get noneDict
        //let's create an empty dictionary
        Dictionary<String, Integer> emptyDict2 = new Hashtable<>();
        assertEquals(noneDict, spyTester.findClassCalls(siblingAST, emptyDict2));
        // need to add a sibling
        siblingChildAST.addNextSibling(siblingChild2AST);
        // need to mock up getting next sibling
        assertEquals(TokenTypes.EXPR,siblingChildAST.getNextSibling().getType());
        
    }

/////////// mocking method from test
    /**
     * A function borrowed from github user Ivanov-Alex: 
     * https://gist.github.com/ivanov-alex/e0cc14d3dc6fc1520283
     * Creates MOCK lexical token and returns AST node for this token
     * From Wikipedia: ANTLR can generate lexers, parsers, tree parsers, 
     * and combined lexer-parsers. Parsers can automatically generate 
     * parse trees or abstract syntax trees, which can be further processed 
     * with tree parsers.
     * @param tokenType     type of token
     * @param tokenText     text of token
     * @param tokenFileName file name of token
     * @param tokenRow      token position in a file (row)
     * @param tokenColumn   token position in a file (column)
     * @return AST node for the token
     */
    private static DetailAST mockAST(final int tokenType, final String tokenText, final String tokenFileName,
            final int tokenRow, final int tokenColumn) {
        CommonHiddenStreamToken tokenImportSemi = new CommonHiddenStreamToken();
        tokenImportSemi.setType(tokenType);
        tokenImportSemi.setText(tokenText);
        tokenImportSemi.setLine(tokenRow);
        tokenImportSemi.setColumn(tokenColumn);
        tokenImportSemi.setFilename(tokenFileName);
        DetailAST astSemi = new DetailAST();
        astSemi.initialize(tokenImportSemi);
        return astSemi;
    }

}
