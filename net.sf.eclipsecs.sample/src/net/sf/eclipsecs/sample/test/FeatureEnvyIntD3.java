package net.sf.eclipsecs.sample.test;

import static org.junit.Assert.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import com.puppycrawl.tools.checkstyle.TreeWalker;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.FileText;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import net.sf.eclipsecs.sample.checks.FeatureEnvyCheck;

import org.junit.Before;
import org.junit.Test;

public class FeatureEnvyIntD3 {
    
    private FileText text;
    private FileContents contents;
    private DetailAST rootAST;

    @Before
    public void setUp() throws Exception {
        text = new FileText(new File("/Users/byrdsmyth/git/net.sf.eclipsecs.sample/src/net/sf/eclipsecs/sample/test/FeatureEnvySample.java"),
                System.getProperty("file.encoding", "UTF-8"));
        contents = new FileContents(text);
        rootAST = TreeWalker.parse(contents);
    }

    @Test
    public void testBeginTreeAndVisitToken() throws Throwable {
        assertEquals(null, rootAST.getParent());
        assertEquals(null, rootAST.getNextSibling().getParent());
        assertEquals(rootAST.getType(), TokenTypes.PACKAGE_DEF);

        DetailAST classToken = rootAST.getNextSibling();
        assertEquals(classToken.getType(), TokenTypes.CLASS_DEF);
        
        DetailAST objToken = classToken.findFirstToken(TokenTypes.OBJBLOCK);
        assertEquals(objToken.getType(), TokenTypes.OBJBLOCK);
        
        DetailAST var = objToken.findFirstToken(TokenTypes.VARIABLE_DEF);
        assertEquals(var.getType(), TokenTypes.VARIABLE_DEF);   
    }
    
    @Test
    public void testFindFeatureEnvy() throws Exception {
        FeatureEnvyCheck spyCheck = PowerMockito.spy(new FeatureEnvyCheck());

        spyCheck.visitToken(rootAST.getNextSibling());
        verify(spyCheck, times(2)).detected();      
    }

}
