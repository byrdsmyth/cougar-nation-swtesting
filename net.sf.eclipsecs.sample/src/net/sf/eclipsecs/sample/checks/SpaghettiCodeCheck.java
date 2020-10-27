package net.sf.eclipsecs.sample.checks;


import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.FileContents;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.TextBlock;

/**
 * <p>
 * Checks for potential locations of Spaghetti Code code smells.
 * </p>
 * <p>
 * This check looks for long classes with no structure, declaring 
 * long methods with no parameters, and utilising global variables.
 * </p>
 * * <ul>
 * <li>
 * Property {@code maxGlobalVars} - Most variables per class which
 * can be globals
 * Type is {@code integer}.
 * Default value is {@code 5}.
 * </li>
 * <li>
 * Property {@code maxClassLength} - Size class can be before getting
 * flagged for being too long
 * Default value is {@code 1000}.
 * </li>
 * <li>
 * Property {@code maxMethodLength} - Size methods can be before getting
 * flagged for being too long
 * Default value is {@code 150}.
 * </li>
 * </ul>
 * <p>
 * Parent is {@code com.puppycrawl.tools.checkstyle.TreeWalker}
 * </p>
 */

// Attribution: based some code off of getMethodLengthCHeck on checkstyle's github page: https://github.com/checkstyle/checkstyle/blob/master/src/main/java/com/puppycrawl/tools/checkstyle/checks/sizes/MethodLengthCheck.java
public class SpaghettiCodeCheck extends AbstractCheck {
    
    private int maxGlobalVars = 5;
    private int maxClassLength = 1000;
    private int maxMethodLength = 100;
    
    /**
     * Sets max number of global variables.
     * @param maxMethods
     */
    public void setMaxGlobalVars(int maxGlobalVars) {
        System.out.println("Setting maxGlobalVars");
        this.maxGlobalVars = maxGlobalVars;
    }
    
    /**
     * Gets max number of global variables.
     * @param maxMethods
     */
    public int getMaxGlobalVars() {
        return maxGlobalVars;
    }
    
    /**
     * Sets max length of any one class.
     * @param maxMethods
     */
    public void setMaxClassLength(int maxClassLength) {
        System.out.println("Setting class length");
        this.maxClassLength = maxClassLength;
    }
    
    /**
     * Gets max length of any one class.
     * @param maxMethods
     */
    public int getMaxClassLength() {
        return this.maxClassLength;
    }
    
    /**
     * Sets max number of lines in any one method.
     * @param maxMethods
     */
    public void setMaxLines(int maxMethodLength) {
        System.out.println("Setting method length");
        this.maxMethodLength = maxMethodLength;
    }
    
    /**
     * Sets max number of lines in any one method.
     * @param maxMethods
     */
    public int getMaxLines() {
        return this.maxMethodLength;
    }
    
    private int currentGlobalsCount = 0;
    
    /** Vars for local use only **/
    private boolean TOO_MANY_GLOBALS = false;
    private boolean INHERITANCE = false;

    /**
    /* returns a set of TokenTypes which are processed in visitToken() method by default.*/
    @Override
    public int[] getDefaultTokens() {
        // begin with ones for checking length of method
        return new int[] {TokenTypes.METHOD_DEF, TokenTypes.CTOR_DEF, TokenTypes.IMPLEMENTS_CLAUSE, 
                TokenTypes.EXTENDS_CLAUSE, TokenTypes.CLASS_DEF};
    }

    /* returns a set, which contains all the TokenTypes that can be processed by the check. 
     * Both DefaultTokens and RequiredTokens and any custom set of TokenTypes are subsets 
     * of AcceptableTokens. */
    @Override
    public int[] getAcceptableTokens() {
        return getRequiredTokens();
    }

    /* returns a set of TokenTypes which Check must be subscribed to for a valid execution. 
     * If the user wants to specify a custom set of TokenTypes then this set must contain 
     * all the TokenTypes from RequiredTokens. */
    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }
    
    /**
     * Sets max number of methods.
     * @param maxMethods
     */
    @Override
    public void visitToken(DetailAST ast) {
        System.out.println("token is found " + ast.getText());
        // to check inheritance look for Implements and Extends
        if (ast.getType() == TokenTypes.IMPLEMENTS_CLAUSE || ast.getType() == TokenTypes.EXTENDS_CLAUSE) {
            System.out.println("Inheritance is " + this.INHERITANCE);
            this.INHERITANCE = true;
            System.out.println("Inheritance now is " + this.INHERITANCE);
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
                    System.out.println("Method length: " + length + "Max length: " + this.maxMethodLength);
                    if (length > maxMethodLength) {
                        System.out.println("Method length too long: " + this.maxMethodLength);
                        log(ast.getLineNo(), "potential spaghetti code with long methods or too many globals", maxMethodLength);
                    }
                }
            }
        }
        // Look for long classes using the get length function
        if (ast.getType() == TokenTypes.CLASS_DEF) {
            this.INHERITANCE = false;
            System.out.println("Class found ");
            currentGlobalsCount = 0;
            DetailAST block = ast.findFirstToken(TokenTypes.OBJBLOCK);
            // to find global variables: search for public variable at top-level of class
            if (block != null) {
                System.out.println("OBJBLOCK found ");
                final DetailAST openingBrace = block.findFirstToken(TokenTypes.LCURLY);
                if (openingBrace != null) {
                    final DetailAST closingBrace = block.findFirstToken(TokenTypes.RCURLY);
                    final int length = getLengthOfBlock(openingBrace, closingBrace);
                    System.out.println("Class length: " + length + "max class length: " + this.maxClassLength);
                    if (length > this.maxClassLength && this.INHERITANCE == false && this.TOO_MANY_GLOBALS == true) {
                        System.out.println("Class length too long: " + maxClassLength);
                        log(ast.getLineNo(), "potential spaghetti code with long methods or too many globals", maxClassLength);
                    }
                }
                // look for Var defs that are immediate children of objblock
                DetailAST blockChild = openingBrace.getNextSibling();
                while (blockChild != null) {
                    if(blockChild.getType() != TokenTypes.VARIABLE_DEF) {
                        blockChild = blockChild.getNextSibling();
                    }
                    else {
                        DetailAST childType = blockChild.findFirstToken(TokenTypes.MODIFIERS);
                        if(childType.getFirstChild() != null) {
                            if (childType.getFirstChild().getType() == TokenTypes.LITERAL_PUBLIC) {
                                this.currentGlobalsCount++;
                                System.out.println("found public var: " + this.currentGlobalsCount);
                                if (this.currentGlobalsCount > maxGlobalVars && this.INHERITANCE == false) {
                                    System.out.println("found too many public var: " + this.currentGlobalsCount);
                                    this.TOO_MANY_GLOBALS = true;
                                }
                            }
                        }
                    }
                    if(blockChild != null && blockChild.getNextSibling() != null) {
                        blockChild = blockChild.getNextSibling();
                    }
                    else {
                        blockChild = null;
                    }
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

//        if (!countEmpty) {
//            final FileContents contents = getFileContents();
//            final int lastLine = closingBrace.getLineNo();
//            // lastLine - 1 is actual last line index. Last line is line with curly brace,
//            // which is always not empty. So, we make it lastLine - 2 to cover last line that
//            // actually may be empty.
//            for (int i = openingBrace.getLineNo() - 1; i <= lastLine - 2; i++) {
//                if (contents.lineIsBlank(i) || contents.lineIsComment(i)) {
//                    length--;
//                }
//            }
//        }
        return length;
    }

}
