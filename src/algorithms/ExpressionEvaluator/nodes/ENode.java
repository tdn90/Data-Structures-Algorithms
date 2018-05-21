package algorithms.ExpressionEvaluator.nodes;

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
