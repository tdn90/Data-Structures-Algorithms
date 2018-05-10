package algorithms.ExpressionEvaluator.nodes;

public class SqrtNode extends UnaryOperatorNode {

    public SqrtNode(String op) { super(op); }
    /**
     * This expression evaluator returns double, because that is the result of evaluating
     * each node. Note that a NumberNode evaluates to its number while an OperatorNode
     * evaluates to the result of its computation.
     */
    @Override
    public double value() {
        return Math.sqrt(left.value());
    }
}
