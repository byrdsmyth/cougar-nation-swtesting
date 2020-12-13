package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import com.puppycrawl.tools.checkstyle.TreeWalker;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.FeatureEnvyCheck;

public class FeatureEnvyBB_D3 {
    
    private FileText text;
    private FileContents contents;
    private DetailAST rootAST0;
    private DetailAST rootAST1;

    @Before
    public void setUp() throws Exception {
        text = new FileText(new File("/Users/byrdsmyth/git/net.sf.eclipsecs.sample/src/net/sf/eclipsecs/sample/test/FeatureEnvySample.java"),
                System.getProperty("file.encoding", "UTF-8"));
        contents = new FileContents(text);
        
        FileText text1 = new FileText(new File("/Users/byrdsmyth/git/net.sf.eclipsecs.sample/src/net/sf/eclipsecs/sample/test/NonFeatureEnvySample.java"),
                System.getProperty("file.encoding", "UTF-8"));
        FileContents contents1 = new FileContents(text1);        
        
        rootAST0 = TreeWalker.parse(contents);
        rootAST1 = TreeWalker.parse(contents1);
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
    /**
     * Test case 1: A->true, B->true
     */
    public void blackboxTestcauseEffective1() {
        
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());
        FeatureEnvyCheck spyCheck1 = PowerMockito.spy(new FeatureEnvyCheck());
        /* test 1 */
        spyCheck.visitToken(rootAST0.getNextSibling());
        verify(spyCheck, times(2)).detected();
        
        /* test 2 */
        spyCheck1.visitToken(rootAST1.getNextSibling());
        verify(spyCheck1, times(0)).detected();
    }
    
    @Test
    /**
     * Test case 1: A->false, B->don't care
     */
    public void blackboxTestcauseEffectiv2() {
        
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());
        spyCheck.visitToken(null);
        verify(spyCheck, times(0)).detected();
    }
    
    @Test
    /**
     * Test case 1: A->true, B->false
     */
    public void blackboxTestcauseEffective3() {
        
        FeatureEnvyCheck spyCheck1 = PowerMockito.spy(new FeatureEnvyCheck());

        spyCheck1.visitToken(rootAST1.getNextSibling());
        verify(spyCheck1, times(0)).detected();
    }

}
