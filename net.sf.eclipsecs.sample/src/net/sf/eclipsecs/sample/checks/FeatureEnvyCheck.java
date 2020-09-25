package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TextBlock;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.api.CommonUtil;

public class FeatureEnvyCheck extends AbstractCheck {

    /** Specify the minimum depth for descendant counts. */
    private int minimumDepth;
    /** Specify the maximum depth for descendant counts. */
    private int maximumDepth = Integer.MAX_VALUE;
    /** Specify a minimum count for descendants. */
    private int minimumNumber;
    /** Specify a maximum count for descendants. */
    private int maximumNumber = Integer.MAX_VALUE;
    /**
     * Control whether the number of tokens found should be calculated from the sum
     * of the individual token counts.
     */
    private boolean sumTokenCounts;
    /** Specify set of tokens with limited occurrences as descendants. */
    private int[] limitedTokens = CommonUtil.EMPTY_INT_ARRAY;
    /** Define the violation message when the minimum count is not reached. */
    private String minimumMessage;
    /** Define the violation message when the maximum count is exceeded. */
    private String maximumMessage;

    /**
     * Counts of descendant tokens. Indexed by (token ID - 1) for performance.
     */
    private int[] counts = CommonUtil.EMPTY_INT_ARRAY;
    
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
        return new int[] { TokenTypes.ARRAY_DECLARATOR };
    }

    @Override
    public void visitToken(DetailAST ast) {
        final DetailAST typeAST = ast.getParent();
        if (typeAST.getType() == TokenTypes.TYPE) {
            final DetailAST variableAST = typeAST.getNextSibling();
            if (variableAST != null) {
                final boolean isMethod = typeAST.getParent().getType() == TokenTypes.METHOD_DEF;
                final boolean isJavaStyle = variableAST.getLineNo() > ast.getLineNo()
                    || variableAST.getColumnNo() - ast.getColumnNo() > -1;

                // force all methods to be Java style (see note in top Javadoc)
                final boolean isMethodViolation = isMethod && !isJavaStyle;
                final boolean isVariableViolation = !isMethod && isJavaStyle != javaStyle;

                if (isMethodViolation || isVariableViolation) {
                    log(ast, MSG_KEY);
                }
            }
        }
    }


    /**
     * Counts the number of occurrences of descendant tokens.
     *
     * @param ast   the root token for descendants.
     * @param depth the maximum depth of the counted descendants.
     */
    private void countTokens(DetailAST ast, int depth) {
        if (depth <= maximumDepth) {
            // update count
            if (depth >= minimumDepth) {
                final int type = ast.getType();
                if (type <= counts.length) {
                    counts[type - 1]++;
                }
            }
            DetailAST child = ast.getFirstChild();
            final int nextDepth = depth + 1;
            while (child != null) {
                countTokens(child, nextDepth);
                child = child.getNextSibling();
            }
        }
    }

    /**
     * Setter to specify set of tokens with limited occurrences as descendants.
     *
     * @param limitedTokensParam - list of tokens to ignore.
     */
    public void setLimitedTokens(String... limitedTokensParam) {
        limitedTokens = new int[limitedTokensParam.length];

        int maxToken = 0;
        for (int i = 0; i < limitedTokensParam.length; i++) {
            limitedTokens[i] = TokenUtil.getTokenId(limitedTokensParam[i]);
            if (limitedTokens[i] >= maxToken + 1) {
                maxToken = limitedTokens[i];
            }
        }
        counts = new int[maxToken];
    }

    /**
     * Setter to specify the minimum depth for descendant counts.
     * to make something configurable, just add a setter method
     * @param minimumDepth the minimum depth for descendant counts.
     */
    public void setMinimumDepth(int minimumDepth) {
        this.minimumDepth = minimumDepth;
    }

    /**
     * Setter to specify the maximum depth for descendant counts.
     * to make something configurable, just add a setter method
     * @param maximumDepth the maximum depth for descendant counts.
     */
    public void setMaximumDepth(int maximumDepth) {
        this.maximumDepth = maximumDepth;
    }

    /**
     * Setter to specify a minimum count for descendants.
     * to make something configurable, just add a setter method
     * @param minimumNumber the minimum count for descendants.
     */
    public void setMinimumNumber(int minimumNumber) {
        this.minimumNumber = minimumNumber;
    }

    /**
     * Setter to specify a maximum count for descendants.
     * to make something configurable, just add a setter method
     * @param maximumNumber the maximum count for descendants.
     */
    public void setMaximumNumber(int maximumNumber) {
        this.maximumNumber = maximumNumber;
    }

}
