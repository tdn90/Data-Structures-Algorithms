package algorithms.ExpressionEvaluator.nodes;

public class MultiplyNode extends BinaryOperatorNode{
    public MultiplyNode(String op) {
        super(op);
    }

    @Override
    public int priority() {
        return 3;
    }

    /**
     * This expression evaluator returns double, because that is the result of evaluating
     * each node. Note that a NumberNode evaluates to its number while an OperatorNode
     * evaluates to the result of its computation.
     */
    @Override
    public double value() {
        return left.value() * right.value();
    }
}
