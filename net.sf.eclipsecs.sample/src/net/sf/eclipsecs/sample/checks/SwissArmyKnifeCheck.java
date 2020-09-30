package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class SwissArmyKnifeCheck extends AbstractCheck {

// long method, large class, no inheritance, long parameter list
// number of interfaces too high - how to measure?
// combine semantics with software complexity

    private int max = 0;
    
/**
     * A key is pointing to the warning message text in "messages.properties"
     * file.
     */
    public static final String MSG_KEY = "army.knife";

    /**
     * An array of tokens for interface modifiers.
     */
    private static final int[] TOKENS_FOR_INTERFACE_MODIFIERS = {
        TokenTypes.LITERAL_STATIC,
        TokenTypes.ABSTRACT,
    };
    
    /**
     * Counts of descendant tokens. Indexed by (token ID - 1) for performance.
     */
//    private int[] counts = CommonUtil.EMPTY_INT_ARRAY;
//    
    /* returns a set of TokenTypes which are processed in visitToken() method by default.*/
    @Override
    public int[] getDefaultTokens() {
        
        return getRequiredTokens();
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
        return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
    }
    
    @Override
    public void visitToken(DetailAST ast) {
        if (ast.getType() == TokenTypes.INTERFACE_DEF) {
            max++;
            System.out.println("Found interface");
        }
        else {
            System.out.println("No interface");
        }
            
            
//        final DetailAST typeAST = ast.getParent();
//        if (typeAST.getType() == TokenTypes.TYPE) {
//            String type_temp = typeAST.getText();
//            System.out.println(type_temp);
//            final DetailAST variableAST = typeAST.getNextSibling();
//            if (variableAST != null) {
//                type_temp = typeAST.getText();
//                System.out.println(type_temp);
//            }
//           
//            DetailAST interfaceOne = ast.findFirstToken(TokenTypes.INTERFACE_DEF);
//            if(interfaceOne != null) {
//                int numInterfaces = interfaceOne.getChildCount(TokenTypes.INTERFACE_DEF);
//                System.out.println("Found " + numInterfaces + "Interfaces");
//            }
//            
//            if (ast.getType() == TokenTypes.INTERFACE_DEF) {
//                final DetailAST modifiers =
//                        ast.findFirstToken(TokenTypes.MODIFIERS);
//
//                    for (final int tokenType : TOKENS_FOR_INTERFACE_MODIFIERS) {
//                        final DetailAST modifier =
//                                modifiers.findFirstToken(tokenType);
//                        if (modifier != null) {
//                            log(modifier, MSG_KEY, modifier.getText());
//                        }
//                    }
//
//            }

//                final boolean isMethod = typeAST.getParent().getType() == TokenTypes.METHOD_DEF;
//                final boolean isJavaStyle = variableAST.getLineNo() > ast.getLineNo()
//                    || variableAST.getColumnNo() - ast.getColumnNo() > -1;
//
//                // force all methods to be Java style (see note in top Javadoc)
//                final boolean isMethodViolation = isMethod && !isJavaStyle;
//                final boolean isVariableViolation = !isMethod && isJavaStyle != javaStyle;
//
//                if (isMethodViolation || isVariableViolation) {
//                    log(ast, MSG_KEY);
//                }
//            }
//        }
        }
//
}