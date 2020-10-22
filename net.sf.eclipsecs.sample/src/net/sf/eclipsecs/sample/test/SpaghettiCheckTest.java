package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.fail;

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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.mockito.Mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class SpaghettiCheckTest {
    
    public DetailAST tree;
    public CommentCountCheck tester;
    public CommentCountCheck mockTester;
    public CommentCountCheck spyTester;
    public int[] acceptableTokens;
    public int[] unacceptableTokens;
    public int[] requiredTokens;
    ArrayList<Integer> tokenArr;


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
