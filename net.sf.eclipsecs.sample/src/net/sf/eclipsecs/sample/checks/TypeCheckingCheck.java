
package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class TypeCheckingCheck extends AbstractCheck{

<<<<<<< HEAD
	
	  private static int MAX_INSTANCEOF = 1;
	  private static int MAX_SWITCHES = 10;
	  private static int MAX_ORS = 20;
	  private static int MAX_ANDS = 20;
	  private int instances = 0;
	  private int switches = 0;
	  private int ors = 0;
	  private int ands = 0;
	  
	  @Override
	  public int[] getAcceptableTokens() {
	    return new int[] { TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_SWITCH, 
	    		TokenTypes.LOR, TokenTypes.LAND, };
	  }
=======
    
      private static int MAX_INSTANCEOF = 1;
      private static int MAX_SWITCHES = 10;
      private static int MAX_ORS = 20;
      private static int MAX_ANDS = 20;
      private int instances = 0;
      private int switches = 0;
      private int ors = 0;
      private int ands = 0;
      
      @Override
      public int[] getAcceptableTokens() {
        return new int[] { TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_SWITCH, 
                TokenTypes.LOR, TokenTypes.LAND, };
      }
>>>>>>> BrittCheckFixes

      @Override
      public int[] getRequiredTokens() {
        return new int[0];
      }

<<<<<<< HEAD
	  @Override
	  public int[] getDefaultTokens() {
	    return new int[] {TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_SWITCH};
	  }
	  
	  
	  @Override
	  public void visitToken(DetailAST ast) {
		  if (ast.getType() == TokenTypes.LITERAL_INSTANCEOF) {
			  instances++;
		  }
		  if(ast.getType() == TokenTypes.LITERAL_SWITCH) {
			  switches++;
		  }
		  if(ast.getType() == TokenTypes.LOR) {
			  ors++;
		  }
		  if(ast.getType() == TokenTypes.LAND) {
			  ands++;
		  }
		  
		  if(instances >= MAX_INSTANCEOF || switches >= MAX_SWITCHES ||
				  ors >= MAX_ORS || ands >= MAX_ANDS) {
			  log(ast.getLineNo(), "Type Check violation detected");
		  }
	  }
}
=======
      @Override
      public int[] getDefaultTokens() {
        return new int[] {TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_SWITCH};
      }
      
      
      @Override
      public void visitToken(DetailAST ast) {
          if (ast.getType() == TokenTypes.LITERAL_INSTANCEOF) {
              instances++;
          }
          if(ast.getType() == TokenTypes.LITERAL_SWITCH) {
              switches++;
          }
          if(ast.getType() == TokenTypes.LOR) {
              ors++;
          }
          if(ast.getType() == TokenTypes.LAND) {
              ands++;
          }
          
          if(instances >= MAX_INSTANCEOF || switches >= MAX_SWITCHES ||
                  ors >= MAX_ORS || ands >= MAX_ANDS) {
              log(ast.getLineNo(), "Type Check violation detected");
          }
      }
<<<<<<< HEAD
}
>>>>>>> BrittCheckFixes
=======
}
>>>>>>> BrittCheckFixes
