package algorithms.ExpressionEvaluator.nodes;

public class ExpNode extends BinaryOperatorNode{
    public ExpNode(String op) {
        super(op);
    }

    @Override
    public int priority() {
        return 4;
    }

    /**
     * This expression evaluator returns double, because that is the result of evaluating
     * each node. Note that a NumberNode evaluates to its number while an OperatorNode
     * evaluates to the result of its computation.
     */
    @Override
    public double value() {
        return Math.pow(left.value(), right.value());
    }
}
