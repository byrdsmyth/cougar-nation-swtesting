package net.sf.eclipsecs.sample.checks;
//
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.TextBlock;
//import com.puppycrawl.tools.checkstyle.utils.CommonUtil;

public class SwissArmyKnifeCheck extends AbstractCheck {
     
    private int maxMethods = 5;
    private int maxInterfaces = 5;
    
    private int interfaceCount = 0;
    private int methodCount = 0;
    
 // long method, large class, no inheritance, long parameter list
 // number of interfaces too high - how to measure?
 // combine semantics with software complexity

     // definition from PDF: Generally, this anti-pattern arises when a class has many methods with high
     // complexity and the class has a high number of interfaces
    // This can easily be observed by not just having a large number of methods, but
    // in particularly implementing too many interfaces and/or using multiple inheritance.
    // so: implements, extends, and count of method? source: https://arxiv.org/pdf/1703.10882.pdf
    
/**
     * A key is pointing to the warning message text in "messages.properties"
     * file.
     */
    public static final String MSG_KEY = "army.knife";
    

    /**
     * Sets if matches within comments should be ignored.
     * @param ignoreComments True if comments should be ignored.
     */
    public void setMaxMethods(int maxMethods) {
        this.maxMethods = maxMethods;
    }

    
    /**
     * Sets if matches within comments should be ignored.
     * @param ignoreComments True if comments should be ignored.
     */
    public void setMaxInterfaces(int maxInterfaces) {
        this.maxInterfaces = maxInterfaces;
    }

    
    /**
     * Counts of descendant tokens. Indexed by (token ID - 1) for performance.
     */
//    private int[] counts = CommonUtil.EMPTY_INT_ARRAY;
    
    /* returns a set of TokenTypes which are processed in visitToken() method by default.*/
    @Override
    public int[] getDefaultTokens() {
        return new int[] { TokenTypes.METHOD_DEF, TokenTypes.INTERFACE_DEF };
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

    @Override
    public void visitToken(DetailAST ast) {
        System.out.println("Max Interfaces " + maxInterfaces );
        System.out.println("Max Methods " + maxMethods );
        if (ast.getType() == TokenTypes.INTERFACE_DEF) {
            interfaceCount++;
            System.out.println("Now have found " + interfaceCount + " Interfaces");
            checkViolations(ast);
        }
        else {
            if (ast.getType() == TokenTypes.METHOD_DEF) {
                methodCount++;
                System.out.println("Now have found " + methodCount + " Methods");
                checkViolations(ast);
            }
            System.out.println("No interface");
        }
    }
    
    public void checkViolations(DetailAST ast) {
        if (interfaceCount > maxInterfaces && methodCount > maxMethods) {
            System.out.println("Logging");
            log(ast.getLineNo(), MSG_KEY, "XX");
          }
    }
}
