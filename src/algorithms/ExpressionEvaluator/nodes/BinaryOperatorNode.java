package algorithms.ExpressionEvaluator.nodes;

public abstract class BinaryOperatorNode extends OperatorNode {
	public BinaryOperatorNode(String op) {
		super(op);
	}
	
	/** Binary operators use two parameters. */
	public int numParameters() { return 2; }
	
	/** Represent binary operator as "val op val". */
	public String representation() {
		return "(" + left.representation() + " " + op + " " + right.representation() + ")";
	}
}
