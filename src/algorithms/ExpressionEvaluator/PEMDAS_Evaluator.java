package algorithms.ExpressionEvaluator;
import algorithms.ExpressionEvaluator.nodes.*;
import dataStruct.LinkedList;
import dataStruct.Stack;
import java.util.Scanner;

/**
 * Stub code from p. 129 of Sedgewick (4ed) revised to construct binary expression tree from a
 * parenthetical infix expression.
 */
public class PEMDAS_Evaluator {
    public static void main(String[] args) {
        Stack<OperatorNode> ops = new LinkedList<>();
        Stack<ExpressionNode> vals = new LinkedList<>();
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            // Read token. push if operator.
            String s= input.next();
            switch (s) {
                case "(":
                    // this change ensures we will be able to pop values back to beginning of expression
                    // to support arbitrarily long infix expressions.
                    ops.push(new StartExpressionNode(s));
                    break;
                // new operator recognition goes here...
                case "^":
                    ops.push(new ExpNode(s));
                    break;
                case "+":
                    ops.push(new PlusNode(s));
                    break;
                case "-":
                    ops.push(new SubstractNode(s));
                    break;
                case "*":
                    ops.push(new MultiplyNode(s));
                    break;
                case "/":
                    ops.push(new DivisionNode(s));
                    break;
                case "triangle":
                    ops.push(new TriangleNode(s));
                    break;
                case "sqrt":
                    ops.push(new SqrtNode(s));
                    break;

                // these are constants to be pushed onto the vals stack.
                case "e":
                    vals.push(new ENode(s));
                    break;
                case "pi":
                    vals.push(new PiNode(s));
                    break;
                // you can put more here...
                case ")":
                    // Grab 0, 1 or 2 parameters based on the operator. Will pop
                    // back to the StartExpressionNode just to be sure we have got all
                    // of the parameters. If not, then attempt to clone operator and
                    // build up small subtrees for expressions such as (3 + 4 + 5 + 7)
                    OperatorNode op = ops.pop();
                    if (op.numParameters() == 0) {
                        // leave alone. Push right back.
                        vals.push(op);
                    } else {
                        op.setLeft(vals.pop());
                        if (op.numParameters() == 2) {  // do in proper order
                            op.setRight(op.getLeft());
                            op.setLeft(vals.pop());
                        }
                    }

                    /*
                     * My implementation to process and evaluate the expression tree
                     * The magic happens here:
                     * Operator with lower priority is "pushed up"
                     * Those with higher priority is "pushed down"
                     */
                    OperatorNode check = ops.pop();
                    while (check.isOperator()) {  // If operator IS an operator, then have unexpectedly
                        // run into more of the expression.
                        int cmpTop = check.priority() - op.priority();
                        // Lower priority than the root (top) operator
                        // Push the rest down and assign new operator as root
                        if (cmpTop < 0) {
                            check.setRight(op);
                            check.setLeft(vals.pop());
                            op = check;
                        } else {  // otherwise sink the new operator down
                            OperatorNode cur = op;
                            // Attempt to traverse down to the left
                            // to find the appropriate position to put new operator
                            while (cur.getLeft().isOperator() && (check.priority() >= ((OperatorNode) cur.getLeft()).priority())) {
                                cur = (OperatorNode) cur.getLeft();
                            }
                            check.setLeft(vals.pop());
                            check.setRight(cur.getLeft());
                            cur.setLeft(check);
                        }
                        check = ops.pop();        // keep on going.
                    }
                    // Once operator node is fully processed, it goes onto the vals stack.
                    vals.push(op);
                    break;
                default:
                    // Token no operator or parenthesis; must be double value to push
                    double d = Double.parseDouble(s);
                    NumberNode n = new NumberNode(d);
                    vals.push(n);
                    break;
            }
        }

        // Retrieve the node representing the entire expression
        ExpressionNode expr = vals.pop();

        // The following shows a human-readable form of the expression
        System.out.println("Formula:" +expr.representation());

        // now compute its value.
        System.out.println(expr.value());
    }
}

