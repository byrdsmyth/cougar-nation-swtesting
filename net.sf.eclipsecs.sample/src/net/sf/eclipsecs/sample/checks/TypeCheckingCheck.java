package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**Class to detect casting and heavy logical statements*/
public class TypeCheckingCheck extends AbstractCheck{

    
      private int max = 1;
      
      @Override
      public int[] getAcceptableTokens() {
        return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
      }

      @Override
      public int[] getRequiredTokens() {
        return new int[0];
      }

      @Override
      public int[] getDefaultTokens() {
        return new int[] { TokenTypes.EXPR};
      }
      
      public void setMax(int limit) {
            max = limit;
      }
      
      @Override
      public void visitToken(DetailAST ast) {
        //Find INSTANCEOF under neither
        DetailAST objBlock = ast.findFirstToken(TokenTypes.EXPR);
        int instances = objBlock.getChildCount(TokenTypes.LITERAL_INSTANCEOF);
        if(instances > max) {
            log(ast.getLineNo(), "instanceof", max);
        }
      }
}
