
package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/**
 * Checkstyle looking for abusive OOP, switch statements, and AND/OR statements
 */
public class TypeCheckingCheck extends AbstractCheck{

      //Static data members used for max amount of logical tokens
      private static final int MAX_INSTANCEOF = 1;
      private static final int MAX_SWITCHES = 2;
      private static final long MAX_OR_ANDS = 5;
      private static final int MAX_CASES = 6;
      
      //Data Memebers to count the instances of logical tokens
      
      /**Data Member to keep track of how many "instanceof" Tokens were found*/
      private int instances = 0;
      
      /**Data Memeber to keep track of how many switch statements were found*/
      private int switches = 0;
      

      @Override
      /**{@inheritDoc}*/
      public int[] getAcceptableTokens() {
        return new int[] {TokenTypes.LITERAL_SWITCH, 
                TokenTypes.CASE_GROUP, TokenTypes.LITERAL_INSTANCEOF, TokenTypes.EXPR, TokenTypes.LITERAL_IF};
      }

      @Override
      /**{@inheritDoc}*/
      public int[] getRequiredTokens() {
        return new int[0];
      }
		
	  @Override
	  /**{@inheritDoc}*/
	  public int[] getDefaultTokens() {
	    return new int[] {TokenTypes.LITERAL_INSTANCEOF, TokenTypes.LITERAL_SWITCH, TokenTypes.CASE_GROUP, 
	    		TokenTypes.EXPR, TokenTypes.LITERAL_IF};
	  }


	  @Override
	  /**
	   * Method that will utilize tree walker to walk through all code.  This method will stop
	   * at the specified tokens and count the instances
	   */
	  public void visitToken(DetailAST ast) {
		  checkForInstances(ast);
		  checkForSwitchStatements(ast);
		  checkForLongLogic(ast);	  
	  }
	  
	  /**
	   * Method that will check for abusive uses of switches.  This check will look for 
	   * switches that have too many cases and classes with too many switch statements
	   * @param ast - DetaiST
	   */
	  private final void checkForSwitchStatements(final DetailAST ast) {
		  if(ast.getType() == TokenTypes.CASE_GROUP) {
			  //The children in the Tree should be the amount of "Case:" 
			  if(ast.getChildCount() >= MAX_CASES) {
				  log(ast.getLineNo(), "Type Check violation detected:  Too many cases inside a switch: " + ast.getChildCount());
			  }
			  
		  }
		  //Literal Switch equals "switch(value)"
		  if(ast.getType() == TokenTypes.LITERAL_SWITCH) {
			  switches++;
			  if(switches >= MAX_SWITCHES) {
				  log(ast.getLineNo(), "Type Check violation detected:  Too many switches: " + switches);
			  }
		  }
	  }
	  
	  /**
	   * Method to look for cases of instanceof which could be an abuse of OOP
	   * @param ast - DetailAST
	   */
	  private final void checkForInstances(final DetailAST ast) {
		  if (ast.getType() == TokenTypes.EXPR) {
			  //
			  final DetailAST firstChild = ast.getFirstChild();
			  final DetailAST lastChild = ast.getLastChild();
			  //Get both children to look for instanceOf
			  if(firstChild.getType() == TokenTypes.LITERAL_INSTANCEOF || lastChild.getType() == TokenTypes.LITERAL_INSTANCEOF) {
				  instances++;
				  if(instances >= MAX_INSTANCEOF) {
					  log(ast.getLineNo(), "Type Check violation detected:  Too many instances of instanceof: " + instances);
				  }
			  }
		  }
	  }
	  
	  /**
	   * Method that will look for excessive logical operations of && and || for an if statement
	   * @param ast - DetailAST
	   */
	  private final void checkForLongLogic(final DetailAST ast) {
		  if(ast.getType() == TokenTypes.LITERAL_IF) {
			  String values = ast.toStringList();
			  
			  int countOr, countAnd, total = 0;
			  countAnd = count(values, "&&");
			  countOr = count(values, "||");
			  total = countOr + countAnd;
			  if(total >= MAX_OR_ANDS) {
				  log(ast.getLineNo(), "Type Check violation detected: Too many logic operations per Literal IF: ");
			  }
		  }
		  
	  }
	  
	  /**
	   * Method to count the instance of a string within a string
	   * @param text - string to search in
	   * @param find - string to look for
	   * @return - the count of instances of find in text
	   */
	  public final int count(final String text, final String find) {
		  int lastIndex = 0;
		  int count = 0;

		  while((lastIndex = text.indexOf(find, lastIndex)) != -1) {
		       count++;
		       lastIndex += find.length() - 1;
		  }
	      return count;
	  }
}
