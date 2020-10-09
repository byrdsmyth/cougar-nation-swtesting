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
// Attribution: based some code off of getMethodLengthCHeck on checkstyle's github page: https://github.com/checkstyle/checkstyle/blob/master/src/main/java/com/puppycrawl/tools/checkstyle/checks/sizes/MethodLengthCheck.java
public class SpaghettiCodeCheck extends AbstractCheck {
    
    /**
     * A key is pointing to the warning message text in "messages.properties"
     * file.
     */
    public static final String MSG_KEY = "spaghetti";

    /** Control whether to count empty lines and single line comments of the form {@code //}. */
    private boolean countEmpty = true;

    /** Specify the maximum number of lines allowed. */
    private int maxGlobalVars = 5;
    private int maxClassLength = 1500;
    private int maxMethodLength = 150;
    private int currentGlobalsCount = 0;
    
    /** Vars for local use only **/
    private boolean TOO_MANY_GLOBALS = false;
    private boolean INHERITANCE = false;
    
    /**
     * Sets if matches within comments should be ignored.
     * @param countEmpty True if comments should be ignored.
     */
    public void setCountEmpty(boolean countEmpty) {
        this.countEmpty = countEmpty;
    }
    
    /**
     * Sets if matches within comments should be ignored.
     * @param maxGlobalVars True if comments should be ignored.
     */
    public void setMaxGlobals(int maxGlobalVars) {
        this.maxGlobalVars = maxGlobalVars;
    }
    
    /**
     * Sets if matches within comments should be ignored.
     * @param maxClassLength True if comments should be ignored.
     */
    public void setmaxClassLength(int maxClassLength) {
        this.maxClassLength = maxClassLength;
    }
    
    /**
     * Sets if matches within comments should be ignored.
     * @param maxMethodLength True if comments should be ignored.
     */
    public void setMaxLines(int maxMethodLength) {
        this.maxMethodLength = maxMethodLength;
    }

    @Override
    public int[] getDefaultTokens() {
        // begin with ones for checking length of method
        return new int[] { TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF, TokenTypes.OBJBLOCK, 
                TokenTypes.IMPLEMENTS_CLAUSE, TokenTypes.EXTENDS_CLAUSE, TokenTypes.CLASS_DEF };
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
        // to check inheritance look for Implements and Extends
        if (ast.getType() == TokenTypes.IMPLEMENTS_CLAUSE || ast.getType() == TokenTypes.EXTENDS_CLAUSE) {
            this.INHERITANCE = true;
        }
        
        // to find global variables: search for public variable at top-level of class
        if (ast.getType() == TokenTypes.OBJBLOCK) {
            // look for Var defs that are immediate children
            DetailAST blockChild = ast.getFirstChild();
            while (blockChild != null) {
                if(blockChild.getType() != TokenTypes.VARIABLE_DEF) {
                    blockChild = blockChild.getNextSibling();
                }
                else {
                    DetailAST childType = blockChild.findFirstToken(TokenTypes.TYPE);
                    if (childType.getText() == "public") {
                        this.currentGlobalsCount++;
                        if (this.currentGlobalsCount > this.maxGlobalVars && this.INHERITANCE == false) {
                            this.TOO_MANY_GLOBALS = true;
                        }
                    }
                }
            }
        }
        
        // look for methods that break the rules
        if (ast.getType() == TokenTypes.METHOD_DEF || ast.getType() == TokenTypes.CTOR_DEF) {
            //look for long methods with no parameters
            int numberOfParameters = ast.findFirstToken(TokenTypes.PARAMETERS).getChildCount(TokenTypes.PARAMETER_DEF); // get num of parameters.
            if (numberOfParameters == 0 && this.INHERITANCE == false && this.TOO_MANY_GLOBALS == true) {
                final DetailAST openingBrace = ast.findFirstToken(TokenTypes.SLIST);
                if (openingBrace != null) {
                    final DetailAST closingBrace = openingBrace.findFirstToken(TokenTypes.RCURLY);
                    // make sure they are not too long
                    final int length = getLengthOfBlock(openingBrace, closingBrace);
                    if (length > maxMethodLength) {
                        log(ast.getLineNo(), MSG_KEY, "XX");
                    }
                }
            }
        }
        // Look for long classes using the get length function
        if (ast.getType() == TokenTypes.CLASS_DEF) {
            DetailAST block = ast.findFirstToken(TokenTypes.OBJBLOCK);
            final DetailAST openingBrace = block.findFirstToken(TokenTypes.LCURLY);
            if (openingBrace != null) {
                final DetailAST closingBrace = openingBrace.findFirstToken(TokenTypes.RCURLY);
                final int length = getLengthOfBlock(openingBrace, closingBrace);
                if (length > maxClassLength && this.INHERITANCE == false && this.TOO_MANY_GLOBALS == true) {
                    log(ast.getLineNo(), MSG_KEY, "XX");
                }
            }
        }
    }
    
    /**
     * Returns length of code only without comments and blank lines.
     *
     * @param openingBrace block opening brace
     * @param closingBrace block closing brace
     * @return number of lines with code for current block
     * Code for calculating length from getMethodLengthCHeck on checkstyle's github page
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
