/**
 * 
 */
package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.powermock.api.mockito.PowerMockito;
import static org.mockito.Mockito.when;

import java.io.File;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
//import org.powermock.core.classloader.annotations.PrepareForTest;
//import org.powermock.modules.junit4.PowerMockRunner;

import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.TreeWalker;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;

import net.sf.eclipsecs.sample.checks.FeatureEnvyCheck;

/**
 * @author byrdsmyth
 *
 */
public class FeatureEnvyCheckTestD3 {
    
    private FileText text;
    private FileContents contents;
    private DetailAST rootAST;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        text = new FileText(new File("/Users/byrdsmyth/git/net.sf.eclipsecs.sample/src/net/sf/eclipsecs/sample/test/FeatureEnvySample.java"),
                System.getProperty("file.encoding", "UTF-8"));
        contents = new FileContents(text);
        rootAST = TreeWalker.parse(contents);   
    }

    /**
     * Test method for {@link net.sf.eclipsecs.sample.checks.FeatureEnvyCheck#getDefaultTokens()}.
     */
    @Test
    public final void testGetDefaultTokens() {
        int[] haha = new int[] {TokenTypes.CLASS_DEF};
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());
        assertEquals(haha[0], spyCheck.getDefaultTokens()[0]);
    }

    /**
     * Test method for {@link net.sf.eclipsecs.sample.checks.FeatureEnvyCheck#getAcceptableTokens()}.
     */
    @Test
    public final void testGetAcceptableTokens() {
        int[] haha = new int[] {TokenTypes.CLASS_DEF};
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());
        when(spyCheck.getDefaultTokens()).thenReturn(haha);
        assertEquals(haha, spyCheck.getAcceptableTokens());    
    }

    /**
     * Test method for {@link net.sf.eclipsecs.sample.checks.FeatureEnvyCheck#getRequiredTokens()}.
     */
    @Test
    public final void testGetRequiredTokens() {
        int[] haha = new int[] {TokenTypes.CLASS_DEF};
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());
        when(spyCheck.getDefaultTokens()).thenReturn(haha);
        assertEquals(haha, spyCheck.getRequiredTokens());  
    }

    /**
     * Test method for {@link net.sf.eclipsecs.sample.checks.FeatureEnvyCheck#visitToken(com.puppycrawl.tools.checkstyle.api.DetailAST)}.
     */
    @Test
    public void testFindFeatureEnvy() throws Exception {
        DetailAST root = PowerMockito.mock(DetailAST.class);
        when(root.findFirstToken(TokenTypes.VARIABLE_DEF)).thenReturn(root);
        when(root.findFirstToken(TokenTypes.OBJBLOCK)).thenReturn(root);
        when(root.getFirstChild()).thenReturn(root);
        when(root.getType()).thenReturn(TokenTypes.VARIABLE_DEF);
        when(root.getText()).thenReturn("private");
        when(root.getNextSibling()).thenReturn(root,root,root, null);
        when(root.getLineNo()).thenReturn(5);
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());
        PowerMockito.doNothing().when(spyCheck, "log", Mockito.any(Integer.class), Mockito.any(String.class),
                Mockito.any(Object.class));
        spyCheck.visitToken(rootAST.getNextSibling());
        //verify(root, times(4)).getLineNo();
        verify(spyCheck, times(2)).detected();
    }

    /**
     * Test method for {@link net.sf.eclipsecs.sample.checks.FeatureEnvyCheck#detected()}.
     */
    @Test
    public void testNoFeatureEnvyFound() {
        DetailAST root = PowerMockito.mock(DetailAST.class);
        when(root.findFirstToken(TokenTypes.LCURLY)).thenReturn(root);
        when(root.getType()).thenReturn(TokenTypes.EXPR);
        when(root.getText()).thenReturn("private int x = 9;");
        when(root.getNextSibling()).thenReturn(root,root,root, null);
        when(root.getLineNo()).thenReturn(5);
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());
        spyCheck.visitToken(null);
        verify(spyCheck, times(0)).detected();
        //verify(root, times(0)).getLineNo();
    }

}
