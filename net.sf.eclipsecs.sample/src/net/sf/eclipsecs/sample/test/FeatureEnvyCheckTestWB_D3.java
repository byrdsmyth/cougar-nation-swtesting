package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;

import java.io.File;

import com.puppycrawl.tools.checkstyle.TreeWalker;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import net.sf.eclipsecs.sample.checks.FeatureEnvyCheck;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;

public class FeatureEnvyCheckTestWB_D3 {
    
    private FileText text;
    private FileText text1;
    private FileContents contents;
    private FileContents contents1;
    private DetailAST rootAST0;
    private DetailAST rootAST1;
    public int[] defaultTokens;
    public FeatureEnvyCheck tester;
    public FeatureEnvyCheck mockTester;
    public FeatureEnvyCheck spyTester;

    @Before
    public void setUp() throws Exception {
        text = new FileText(new File("/Users/byrdsmyth/git/net.sf.eclipsecs.sample/src/net/sf/eclipsecs/sample/test/FeatureEnvySample.java"),
                System.getProperty("file.encoding", "UTF-8"));
        contents = new FileContents(text);
        
        text1 = new FileText(new File("/Users/byrdsmyth/git/net.sf.eclipsecs.sample/src/net/sf/eclipsecs/sample/test/NonFeatureEnvySample.java"),
                System.getProperty("file.encoding", "UTF-8"));
        contents1 = new FileContents(text1);        
        
        rootAST0 = TreeWalker.parse(contents);
        rootAST1 = TreeWalker.parse(contents1);
        
        tester = new FeatureEnvyCheck();
        mockTester = mock(FeatureEnvyCheck.class);
        spyTester = spy(tester);
        
        defaultTokens = new int[] { TokenTypes.CLASS_DEF };
    }
    
    @Test
    public void testGetDefaultTokens() {
        assertEquals(defaultTokens, spyTester.getDefaultTokens());
        verify(spyTester, times(1)).getDefaultTokens();
    }
    
    @Test
    public void testVisitToken() {
        FeatureEnvyCheck tester = new FeatureEnvyCheck();
        FeatureEnvyCheck mockTester = mock(FeatureEnvyCheck.class);
        FeatureEnvyCheck spyTester = spy(tester);
        
        spyTester.visitToken(rootAST0);
    }

    @Test
    public void testBeginTreeAndVisitToken() throws Throwable {
        assertEquals(null, rootAST0.getParent());
        assertEquals(null, rootAST0.getNextSibling().getParent());
        assertEquals(rootAST0.getType(), TokenTypes.PACKAGE_DEF);

        DetailAST classToken = rootAST0.getNextSibling();
        assertEquals(classToken.getType(), TokenTypes.CLASS_DEF);
        
        DetailAST objToken = classToken.findFirstToken(TokenTypes.OBJBLOCK);
        assertEquals(objToken.getType(), TokenTypes.OBJBLOCK);
        
        DetailAST var = objToken.findFirstToken(TokenTypes.VARIABLE_DEF);
        assertEquals(var.getType(), TokenTypes.VARIABLE_DEF);
    }
    
    @Test
    public void whiteboxTest1() {
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());
        /* test 1 */
        spyCheck.visitToken(rootAST0.getNextSibling());
        verify(spyCheck, times(2)).detected();
    }
    
    @Test
    public void whiteboxTest2() {
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());
        
        /* test 2 */
        spyCheck.visitToken(rootAST1.getNextSibling());
        verify(spyCheck, times(0)).detected();
    }

}
