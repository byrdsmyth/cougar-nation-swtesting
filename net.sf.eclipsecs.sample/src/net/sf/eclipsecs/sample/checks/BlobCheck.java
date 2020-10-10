package net.sf.eclipsecs.sample.checks;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

/*
 * The Blob, also named God Class, is a class implementing different responsibilities,
 * generally characterized by the presence of a high number of attributes and methods, which
 * implement different functionalities, and by many dependencies with data classes (i.e., classes
 * implementing only getter and setter methods) [24].
 */

public class BlobCheck extends AbstractCheck {

	private int max = 10;
	private int variableCount = 0;
	private int methodCount = 0;

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.VARIABLE_DEF, TokenTypes.METHOD_DEF };
	}

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.VARIABLE_DEF, TokenTypes.METHOD_DEF };
	}

	public void setMax(int limit) {
		max = limit;
	}

	@Override
	public void visitToken(DetailAST ast) {
		switch (ast.getType()) {
		case TokenTypes.VARIABLE_DEF:
			variableCount++;
			break;
		case TokenTypes.METHOD_DEF:
			methodCount++;
			break;
		}

	}

	public void finishTree(DetailAST tree) {
		log(tree.getLineNo(), "blobVar", variableCount);
		if (variableCount > max) {
			log(tree.getLineNo(), "blobVarMax", max);
		}
		variableCount = 0;

		log(tree.getLineNo(), "methodCount", methodCount);
		if (methodCount > max) {
			log(tree.getLineNo(), "methodlimit", max);
		}
		methodCount = 0;
	}

}
