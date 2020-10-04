package net.sf.eclipsecs.sample.checks;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

// A method making too many calls to methods of [5][8][48][57] another class to obtain data and/or functionality.
// package net.sf.eclipsecs.sample.checks;
//
import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TextBlock;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
//import com.puppycrawl.tools.checkstyle.api.CommonUtil;
//import com.puppycrawl.tools.checkstyle.utils.TokenUtil;

// Feature envy is when a method uses data and methods from another class
// more than it uses data and methods from its own class

public class FeatureEnvyCheck extends AbstractCheck {
    
    @Override
    public int[] getDefaultTokens() {
        // begin with ones for checking length of method
        return new int[] { TokenTypes.METHOD_DEF };
    }

    /* returns a set, which contains all the TokenTypes that can be processed by the check. 
     * Both DefaultTokens and RequiredTokens and any custom set of TokenTypes are subsets 
     * of AcceptableTokens. */
    @Override
    public int[] getAcceptableTokens() {
        return getRequiredTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return new int[0];
    }

    @Override
    public void visitToken(DetailAST ast) {
        System.out.println("Hi!");
        int count = ast.getChildCount();
        // call function in each method_def to count all references to classes
        checkSiblings(ast.getFirstChild(), count);
    }

    public void checkSiblings(DetailAST child, int count) {
        // Initializing a Dictionary
        Dictionary<String, Integer> classFeatures = new Hashtable<>();
        
        System.out.println("Child: " + child.getText());
        DetailAST sibling = child.getNextSibling();
        System.out.println("Sibling: " + sibling.getText());
        if (sibling != null) {
            System.out.println("sibling is : " + sibling.getText());
            // while sibling is not SLIST or null
            // get next sibling
            while (sibling != null) {
                System.out.println("Sibling while looking for SLIST: " + sibling.getText());
                    if (sibling.getType() == TokenTypes.SLIST) {
                        System.out.println("SLIST FOUND");
                        // Pass to a function to count the number of variables and method calls
                        DetailAST s_list_child = sibling.getFirstChild();
                        while (s_list_child != null) {
                            // Cycle through children looking for method calls
                            String class_called = checkSLIST(s_list_child);
                            if (((Hashtable<String, Integer>) classFeatures).containsKey(class_called)) {
                                classFeatures.put(class_called, classFeatures.get(class_called) + 1);
                            } else {
                                classFeatures.put(class_called, 1);
                            }
                            s_list_child = s_list_child.getNextSibling();
                        }
                    }
                    sibling = sibling.getNextSibling();
                }
            }
        System.out.println("Classes: " + classFeatures);
        int thisClassCount = 0;
        if (classFeatures.get("this") != null) {
            thisClassCount = classFeatures.get("this");
        }
        Enumeration<String> e = classFeatures.keys();
        while(e.hasMoreElements()) {
            String k = e.nextElement();
            if (k != "none" && classFeatures.get(k) > thisClassCount) {
                log(child.getLineNo(), "Feature Envy Found");
            }
            System.out.println(k + ": " + classFeatures.get(k));
        }
    }
    
    public String checkSLIST(DetailAST child) {
            if (child.getType() == TokenTypes.EXPR) {
                child = child.getFirstChild();
                if (child.getType() == TokenTypes.METHOD_CALL) {
                    child = child.getFirstChild();
                    if (child.getType() == TokenTypes.IDENT) {
                        return "this";
                    } else if (child.getType() == TokenTypes.DOT) {
                        child = child.getFirstChild();
                        if (child.getType() == TokenTypes.IDENT) {
                            return child.getText();
                        }
                    }
                }
            } 
            return "none";
    }
}
//            if (s.branchContains(TokenTypes.VARIABLE_DEF)) {
//                System.out.println("Found SLIST");
//                
//                if (ast.branchContains(TokenTypes.VARIABLE_DEF)) {
//                        System.out.println("Variables found!" + ast.getText());
//                        // is it from another class?
//                        if (ast.branchContains(TokenTypes.ASSIGN)) {
//                                System.out.println("Assign found!" + ast.getText());
//                                if (ast.branchContains(TokenTypes.EXPR)) {
//                                    if (ast.branchContains(TokenTypes.LITERAL_NEW)) {
//                                        System.out.println("Possible creating of new class instance");
//                                        String className = ast.findFirstToken(TokenTypes.IDENT).getText();
//                                        System.out.println(className);
//                                        
//                                    }
//                                }
//                            }
//                        }
                        
                        // search slist for method calls
                        // find dots and save ident if so
//                        if (ast.branchContains(TokenTypes.METHOD_CALL)) {
//                            System.out.println("Method Calls found!" + ast.getText());
//                            DetailAST nextMethod = ast.findFirstToken(TokenTypes.METHOD_CALL);
//                            if (nextMethod.getFirstChild().getType() == TokenTypes.DOT) {
//                                System.out.println("Possible call to another class's methods");
//                            }
//                            if (nextMethod != null) {
//                                // is it from another class?
//                                System.out.println("Now have found " + nextMethod.getText());
//                            }
//                        }
            
            //keep a array of counts
            // if any one of the counts is greater than the count of in-class
            // stuff, throw a flag
//    @Override
//    public void visitToken(DetailAST ast) {
//        System.out.println("Hi!");
//        if (ast.getType() == TokenTypes.METHOD_DEF) {
//            DetailAST methodChild = ast.getFirstChild();
//            if(methodChild != null) {
//                System.out.println("Child 1 is " + methodChild.getText());
//                methodChild = ast.findFirstToken(TokenTypes.SLIST);
//                System.out.println("Found SLIST");
//            }
//            // search slist for method calls
//            // find dots and save ident if so
//            // search for var def
//            // find and save ident if new
//            //keep a array of counts
//            // if any one of the counts is greater than the count of in-class
//            // stuff, throw a flag
//            
//            System.out.println("Method definition found!" + ast.getText());
//            int numberOfMethodCalls = ast.getChildCount(TokenTypes.METHOD_CALL); // get num of parameters.
//            if (numberOfMethodCalls > 0) {
//                System.out.println("Method Calls found!" + ast.getText());
//                DetailAST nextMethod = ast.findFirstToken(TokenTypes.METHOD_CALL);
//                if (nextMethod.getFirstChild().getType() == TokenTypes.DOT) {
//                    System.out.println("Possible call to another class's methods");
//                }
//                if (nextMethod != null) {
//                    // is it from another class?
//                    System.out.println("Now have found " + nextMethod.getText());
//                }
//            }
//            int numberOfDataItems = ast.getChildCount(TokenTypes.VARIABLE_DEF); // get num of parameters.
//            if (numberOfDataItems > 0) {
//                System.out.println("Variables found!" + ast.getText());
//                // is it from another class?
//                DetailAST nextVar = ast.findFirstToken(TokenTypes.VARIABLE_DEF);
//                if (nextVar != null) {
//                    DetailAST nextAssign = nextVar.findFirstToken(TokenTypes.EXPR);
//                    if (nextAssign != null) {
//                        if (nextAssign.getFirstChild().getType() == TokenTypes.LITERAL_NEW) {
//                            System.out.println("Possible creating of new class instance");
//                        }
//                    }
//                }
//                if (nextVar != null) {
//                    // is it from another class?
//                    System.out.println("Now have found " + nextVar.getText());
//                }
//            }
//        }
    
    
        
        // for each token child that is a variable
//    forEachChild(DetailAST root, int type, Consumer<DetailAST> action)
//    Performs an action for each child of DetailAST root node which matches the given token type.
        // see if that variable is part of another class

