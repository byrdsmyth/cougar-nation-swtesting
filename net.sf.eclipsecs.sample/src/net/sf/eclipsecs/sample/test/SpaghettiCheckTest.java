package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;
import org.junit.Test;

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

import net.sf.eclipsecs.sample.checks.SpaghettiCodeCheck;
import net.sf.eclipsecs.sample.checks.TypeCheckingCheck;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

//These are needed to use PowerMock/EasyMocks
@PrepareForTest(SpaghettiCodeCheck.class)
public class SpaghettiCheckTest {
    
    public DetailAST tree;
    public SpaghettiCodeCheck tester;
    public SpaghettiCodeCheck mockTester;
    public SpaghettiCodeCheck spyTester;
    public int[] acceptableTokens;
    public int[] defaultTokens;
    public int[] unacceptableTokens;
    public int[] requiredTokens;
    ArrayList<Integer> tokenArr;
    
    /** 
     * Set up a mock instance of the Spaghetti Code Checker
     * Including the acceptable and required tokens, 
     * saved to arrays
     */
    @Before
    public void setUp() throws Exception {
      acceptableTokens = new int[0];
      defaultTokens = new int[] {TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF, TokenTypes.IMPLEMENTS_CLAUSE, 
              TokenTypes.EXTENDS_CLAUSE, TokenTypes.CLASS_DEF};
      unacceptableTokens = new int[] { TokenTypes.ABSTRACT, TokenTypes.ANNOTATION, TokenTypes.ANNOTATION_ARRAY_INIT, 
              TokenTypes.ANNOTATION_DEF,TokenTypes.ANNOTATION_FIELD_DEF,TokenTypes.ANNOTATION_MEMBER_VALUE_PAIR,
              TokenTypes.ANNOTATIONS, TokenTypes.ARRAY_DECLARATOR, TokenTypes.ARRAY_INIT, TokenTypes.ASSIGN, 
              TokenTypes.AT };
      requiredTokens = new int[0];
      
      tree = new DetailAST();
      
      // What is this?
      //tree.setLineNo(42);
     
      tester = new SpaghettiCodeCheck();
      mockTester = mock(SpaghettiCodeCheck.class);
      spyTester = spy(tester);  
      
      tokenArr = new ArrayList<Integer>();
      tokenArr.add(TokenTypes.METHOD_DEF);
      tokenArr.add(TokenTypes.CTOR_DEF);
      tokenArr.add(TokenTypes.IMPLEMENTS_CLAUSE);
      tokenArr.add(TokenTypes.EXTENDS_CLAUSE);
      tokenArr.add(TokenTypes.CLASS_DEF);
    }

    
    /** 
     * This section contains basic unit tests for the 
     * getters and setters outside of the default ones
     * in the AbstractCheck Class
     */
    @Test
    public void testSetMaxGlobalVars() {
        SpaghettiCodeCheck sCheck = new SpaghettiCodeCheck();
        sCheck.setMaxGlobalVars(3);
        assertEquals(3, sCheck.getMaxGlobalVars());
        //assertNotEquals(2, sCheck.getMaxGlobalVars());
    }
    
    @Test
    public void testSetMaxClassLength() {
        SpaghettiCodeCheck sCheck = new SpaghettiCodeCheck();
        sCheck.setMaxClassLength(3);
        assertEquals(3, sCheck.getMaxClassLength());
        assertNotEquals(2, sCheck.getMaxClassLength());
    }
    
    @Test
    public void testSetMaxLines() {
        SpaghettiCodeCheck sCheck = new SpaghettiCodeCheck();
        sCheck.setMaxLines(3);
        assertEquals(3, sCheck.getMaxLines());
        assertNotEquals(2, sCheck.getMaxLines());
    }
    
    /** 
     * This section contains basic unit tests for the 
     * getters and setters in the AbstractCheck Class
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
      when(mockTester.getDefaultTokens())
        .thenReturn(new int[] { TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF, TokenTypes.IMPLEMENTS_CLAUSE, 
                TokenTypes.EXTENDS_CLAUSE, TokenTypes.CLASS_DEF });
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
      when(mockTester.getAcceptableTokens())
         .thenReturn(new int[0]);
      assertArrayEquals(acceptableTokens, mockTester.getAcceptableTokens());
      verify(mockTester, times(1)).getAcceptableTokens();
    }
    
    @Test
    public void testGetUnacceptableTokensMock() {
      when(mockTester.getAcceptableTokens())
        .thenReturn(new int[] { TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF, TokenTypes.IMPLEMENTS_CLAUSE, 
                TokenTypes.EXTENDS_CLAUSE, TokenTypes.CLASS_DEF });
      assertNotEquals(unacceptableTokens, mockTester.getAcceptableTokens());
      verify(mockTester, times(1)).getAcceptableTokens();
    }
    
    @Test
    public void testGetNullAcceptableTokensMock() {
      when(mockTester.getAcceptableTokens())
        .thenReturn(new int[] { TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF, TokenTypes.IMPLEMENTS_CLAUSE, 
                TokenTypes.EXTENDS_CLAUSE, TokenTypes.CLASS_DEF });
      assertNotEquals(null, mockTester.getAcceptableTokens());
      verify(mockTester, times(1)).getAcceptableTokens();
    }
    
    @Test
    public void testGetLengthOfBlock() {
        SpaghettiCodeCheck sCheck = new SpaghettiCodeCheck(); 
        DetailAST astOpeningBrace = new DetailAST();
        DetailAST astClosingBrace = new DetailAST();
        astOpeningBrace.setType(TokenTypes.LCURLY);
        astOpeningBrace.setType(TokenTypes.RCURLY);
        astOpeningBrace.addChild(new DetailAST());
        astOpeningBrace.addChild(new DetailAST());
        astOpeningBrace.addChild(new DetailAST());
        astOpeningBrace.addChild(new DetailAST());
        
//        ReflectionTestUtils.setField(sCheck, "switches", 1);
//        ReflectionTestUtils.invokeMethod(sCheck,"getLengthOfBlock", astOpeningBraces);   
    }


    /*
     * @Test public void avoidOnlyRelationalOperandsInCondition() throws Exception {
     * final DefaultConfiguration checkConfig =
     * createModuleConfig(SpaghettiCodeCheck.class);
     * checkConfig.addAttribute("applyOnlyToRelationalOperands", "true");
     * 
     * final String[] expected = { "7:24: " + getCheckMessage(MSG_KEY), "11:24: " +
     * getCheckMessage(MSG_KEY), "15:24: " + getCheckMessage(MSG_KEY), "19:21: " +
     * getCheckMessage(MSG_KEY), "23:24: " + getCheckMessage(MSG_KEY), "29:27: " +
     * getCheckMessage(MSG_KEY), "31:34: " + getCheckMessage(MSG_KEY), };
     * 
     * verify(checkConfig, getPath("InputAvoidConditionInversionCheck.java"),
     * expected); }
     * 
     * @Test public void testUnsupportedNode() { final DetailAstImpl sync = new
     * DetailAstImpl(); sync.setType(TokenTypes.LITERAL_SYNCHRONIZED);
     * 
     * try { final AvoidConditionInversionCheck check = new
     * AvoidConditionInversionCheck(); check.visitToken(sync);
     * 
     * fail("exception expected"); } catch (IllegalArgumentException ex) {
     * Assert.assertEquals("Found unsupported token: LITERAL_SYNCHRONIZED",
     * ex.getMessage()); } }
     * 
     * @Test public void testNormalWork() throws Exception { final
     * DefaultConfiguration checkConfig =
     * createModuleConfig(CauseParameterInExceptionCheck.class);
     * checkConfig.addAttribute("classNamesRegexp", ".+Exception");
     * checkConfig.addAttribute("ignoredClassNamesRegexp", null);
     * checkConfig.addAttribute("allowedCauseTypes", "Throwable, Exception");
     * 
     * final String[] expected = { "5:5: " + getCheckMessage(MSG_KEY,
     * "TestException"), };
     * 
     * verify(checkConfig, getPath("InputCauseParameterInExceptionCheck.java"),
     * expected); }
     * 
     * @Test public void testNormalWork2() throws Exception { final
     * DefaultConfiguration checkConfig =
     * createModuleConfig(CauseParameterInExceptionCheck.class);
     * checkConfig.addAttribute("classNamesRegexp", ".+Exception2");
     * checkConfig.addAttribute("ignoredClassNamesRegexp", null);
     * checkConfig.addAttribute("allowedCauseTypes", "Throwable, Exception");
     * 
     * final String[] expected = { "5:5: " + getCheckMessage(MSG_KEY,
     * "TestException2"), "16:5: " + getCheckMessage(MSG_KEY, "MyException2"), };
     * 
     * verify(checkConfig, getPath("InputCauseParameterInExceptionCheck2.java"),
     * expected); }
     * 
     * @Test public void testIgnorePattern() throws Exception { final
     * DefaultConfiguration checkConfig =
     * createModuleConfig(CauseParameterInExceptionCheck.class);
     * checkConfig.addAttribute("classNamesRegexp", ".+Exception2");
     * checkConfig.addAttribute("ignoredClassNamesRegexp", "Test.+");
     * checkConfig.addAttribute("allowedCauseTypes", "Throwable, Exception");
     * 
     * final String[] expected = { "16:5: " + getCheckMessage(MSG_KEY,
     * "MyException2"), };
     * 
     * verify(checkConfig, getPath("InputCauseParameterInExceptionCheck2.java"),
     * expected); }
     */
}
