package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class TypeCheckingCheck extends AbstractCheck{

	
	  private int max = 1;
	  
	  @Override
	  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.LITERAL_INSTANCEOF};
	  }

	  @Override
	  public int[] getRequiredTokens() {
	    return new int[0];
	  }

	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] {TokenTypes.LITERAL_INSTANCEOF};
	  }
	  
	  public void setMax(int limit) {
		    max = limit;
	  }
	  
	  @Override
	  public void visitToken(DetailAST ast) {
		  if (ast.getType() == TokenTypes.LITERAL_INSTANCEOF) {
			  log(ast.getLineNo(), "Type Check found", max);
		  }
	  }
}