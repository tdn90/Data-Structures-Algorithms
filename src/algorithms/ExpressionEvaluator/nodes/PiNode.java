package algorithms.ExpressionEvaluator.nodes;

public class PiNode extends NoParameterOperatorNode{
    public PiNode(String op) {
        super(op);
    }

    @Override
    public int priority() {
        return 5;
    }

    /**
     * NoParameterOperatorNode subclasses determine how to process given value.
     */
    @Override
    public double value() {
        return Math.PI;
    }
}
