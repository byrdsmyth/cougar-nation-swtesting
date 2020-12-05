package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * <p>
 * Checks if class contains Swiss Army Knife anti-pattern.
 * </p>
 * <p>
 * Rationale: A class affected by the Swiss Army Knife is a class that provides answer to a large range of needs.
 * Generally, this anti-pattern arises when a class has many methods with high complexity and the class has a high
 * number of interfaces. The rule card characterizes the anti-pattern is the Number of Interfaces metric, which is able
 * to identify the number of services provided by a class.
 * </p>
 * 
 * @author Maggie Ma
 */

public class SwissArmyKnifeCheckD3 extends AbstractCheck {

    /** Maximum allowed number of interface. */
    private int max = 3;

    public void setMax(int limit) {
        max = limit;
    }

    public int getMax() {
        return max;
    }

    @Override
    public int[] getDefaultTokens() {
        return getAcceptableTokens();
    }

    @Override
    public int[] getAcceptableTokens() {
        return new int[] { TokenTypes.CLASS_DEF };
    }

    @Override
    public int[] getRequiredTokens() {
        return getAcceptableTokens();
    }

    @Override
    public void visitToken(DetailAST ast) {
        DetailAST implementsClause = ast.findFirstToken(TokenTypes.IMPLEMENTS_CLAUSE);
        if (implementsClause != null) {
            int interfaces = implementsClause.getChildCount(TokenTypes.IDENT);
            if (interfaces > max) {
                log(ast.getLineNo(), "SwissArmyKnife", max);
            }
        }
    }
}
