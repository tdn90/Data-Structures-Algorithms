package algorithms.ExpressionEvaluator.nodes;

/**
 * Example of a no-parameter operation node, that returns the constant e.
 */
public class ENode extends NoParameterOperatorNode {

	public ENode(String op) {
		super(op);
	}

	@Override
	public int priority() {
		return 5;
	}

	/** No Parameter operator returns its value. */
	public double value() {
		return Math.E;
	}

}
