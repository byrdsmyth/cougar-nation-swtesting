package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.TextBlock;

// classes without structure that declare long methods without parameters
// very large classes with very long methods
// Spaghetti Code is revealed by classes with no structure, declaring long methods with no parameters, and utilising global variables.
// measurable properties include the concepts of long methods, methods with no parameter, inheritance; its lexical properties 
// include the concepts of procedural names; its structural properties include the concepts of global variables, and 
// polymorphism
// all properties must be present to characterise a class as Spaghetti Code
// RULE:LongMethod RULE:NoParameter RULE:NoInheritance RULE:NoPolymorphism RULE:ProceduralName RULE:UseGlobalVariable { STRUCT USE GLOBAL VARIABLE };
// Attribution: based some code off of getMethodLengthCHeck on checkstyle's github page
public class SpaghettiCodeCheck extends AbstractCheck {
    
    /**
     * A key is pointing to the warning message text in "messages.properties"
     * file.
     */
    public static final String MSG_KEY = "spaghetti";

    /** Default maximum number of lines. */
    private static final int DEFAULT_MAX_LINES = 150;

    /** Control whether to count empty lines and single line comments of the form {@code //}. */
    private boolean countEmpty = true;

    /** Specify the maximum number of lines allowed. */
    private int max = DEFAULT_MAX_LINES;

    @Override
    public int[] getDefaultTokens() {
        // begin with ones for checking length of method
        return new int[] { TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF };
    }

    @Override
    public int[] getAcceptableTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }
    
    @Override
    public void visitToken(DetailAST ast) {
        final DetailAST openingBrace = ast.findFirstToken(TokenTypes.SLIST);
        if (openingBrace != null) {
            final DetailAST closingBrace = openingBrace.findFirstToken(TokenTypes.RCURLY);
            final int length = getLengthOfBlock(openingBrace, closingBrace);
            if (length > max) {
                log(ast, MSG_KEY, length, max);
            }
        }
    }
    
    /**
     * Returns length of code only without comments and blank lines.
     *
     * @param openingBrace block opening brace
     * @param closingBrace block closing brace
     * @return number of lines with code for current block
     */
    private int getLengthOfBlock(DetailAST openingBrace, DetailAST closingBrace) {
        int length = closingBrace.getLineNo() - openingBrace.getLineNo() + 1;

        if (!countEmpty) {
            final FileContents contents = getFileContents();
            final int lastLine = closingBrace.getLineNo();
            // lastLine - 1 is actual last line index. Last line is line with curly brace,
            // which is always not empty. So, we make it lastLine - 2 to cover last line that
            // actually may be empty.
            for (int i = openingBrace.getLineNo() - 1; i <= lastLine - 2; i++) {
                if (contents.lineIsBlank(i) || contents.lineIsComment(i)) {
                    length--;
                }
            }
        }
        return length;
    }

}
