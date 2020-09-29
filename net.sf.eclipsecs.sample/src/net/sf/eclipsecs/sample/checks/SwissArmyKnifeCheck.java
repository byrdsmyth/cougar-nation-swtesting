package net.sf.eclipsecs.sample.checks;
//
//import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.TextBlock;
//import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

//public class SwissArmyKnifeCheck extends AbstractCheck {

// long method, large class, no inheritance, long parameter list
// number of interfaces too high - how to measure?
// combine semantics with software complexity


/**
     * A key is pointing to the warning message text in "messages.properties"
     * file.
     */
//    public static final String MSG_KEY = "army.knife";

//    
//    /**
//     * Counts of descendant tokens. Indexed by (token ID - 1) for performance.
//     */
//    private int[] counts = CommonUtil.EMPTY_INT_ARRAY;
//    
    /* returns a set of TokenTypes which are processed in visitToken() method by default.*/
//    @Override
//    public int[] getDefaultTokens() {
//        return getRequiredTokens();
//    }
//
//    /* returns a set, which contains all the TokenTypes that can be processed by the check. 
//     * Both DefaultTokens and RequiredTokens and any custom set of TokenTypes are subsets 
//     * of AcceptableTokens. */
//    @Override
//    public int[] getAcceptableTokens() {
//        return getRequiredTokens();
//    }
//
//    /* returns a set of TokenTypes which Check must be subscribed to for a valid execution. 
//     * If the user wants to specify a custom set of TokenTypes then this set must contain 
//     * all the TokenTypes from RequiredTokens. */
//    @Override
//    public int[] getRequiredTokens() {
//        return new int[] { TokenTypes.ARRAY_DECLARATOR };
//    }
//
//    @Override
//    public void visitToken(DetailAST ast) {
//        final DetailAST typeAST = ast.getParent();
//        if (typeAST.getType() == TokenTypes.TYPE) {
//            final DetailAST variableAST = typeAST.getNextSibling();
//            if (variableAST != null) {
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
//    }
//
//}
