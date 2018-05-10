package algorithms.ExpressionEvaluator.nodes;

public class TriangleNode extends UnaryOperatorNode {
    public TriangleNode(String op) {
        super(op);
    }

    /**
     * This expression evaluator returns double, because that is the result of evaluating
     * each node. Note that a NumberNode evaluates to its number while an OperatorNode
     * evaluates to the result of its computation.
     */
    @Override
    public double value() {
        return (left.value() * (left.value()+1)) / 2.0;
    }
}
