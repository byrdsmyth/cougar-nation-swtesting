package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.utils.CheckUtils;

/**
 * <p>
 * Checks if class contains Type-Checking anti-pattern.
 * </p>
 * <p>
 * Rationale: Generally, type-checking code is introduced in order to select a variation of an algorithm that should be
 * executed, depending on the value of an attribute. The rule card characterizes the anti-pattern is the Number of depth
 * of switch statement or nested if-else statement.
 * </p>
 * 
 * @author Maggie Ma
 */

public class TypeCheckD3 extends AbstractCheck {

    /** Maximum allowed nesting depth. */
    private int max = 3;

    /** Current nesting depth. */
    private int depth;

    public void setMax(int limit) {
        max = limit;
    }

    public int getMax() {
        return max;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] { TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_IF };
    }

    @Override
    public int[] getRequiredTokens() {
        return getAcceptableTokens();
    }

    @Override
    public void beginTree(DetailAST rootAST) {
        depth = 0;
    }

    @Override
    public void visitToken(DetailAST ast) {
        if (ast.getType() == TokenTypes.LITERAL_SWITCH) {
            depth = 0;
            final DetailAST firstCaseGroupAst = ast.findFirstToken(TokenTypes.CASE_GROUP);
            DetailAST nextAst = firstCaseGroupAst;
            while (nextAst != null && nextAst.getType() != TokenTypes.RCURLY) {
                if (depth++ > max) {
                    log(ast.getLineNo(), "TypeCheck", max);
                    break;
                }
                nextAst = nextAst.getNextSibling();
            }
        } else if (ast.getType() == TokenTypes.LITERAL_IF) {
            if (CheckUtils.isElseIf(ast)) {
                if (++depth >= max) {
                    log(ast.getLineNo(), "TypeCheck", max);
                }
            }
        }
    }

    @Override
    public void leaveToken(DetailAST ast) {
        if (ast.getType() == TokenTypes.LITERAL_IF) {
            if (CheckUtils.isElseIf(ast)) {
                --depth;
            }
        }
    }

}
