package algorithms.ExpressionEvaluator.nodes;

/**
 * Represents the addition of two values.
 */
public class PlusNode extends BinaryOperatorNode {

	public PlusNode(String op) {
		super(op);
	}

	@Override
	public int priority() {
		return 2;
	}

	/** Operator subclasses determine how to process given value. */
	public double value() {
		return left.value() + right.value();
	}

}
