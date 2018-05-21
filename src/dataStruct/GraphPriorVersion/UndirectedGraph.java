package dataStruct.GraphPriorVersion;

public class UndirectedGraph extends Graph {

    public UndirectedGraph() {
        super();
    }

    /**
     * Abstract method, implemented in DirectedGraph and UndirectedGraph
     * Make sure that the edge is added to both the source node and the destination node
     * @param src: source node
     * @param dest: destination node
     */
    @Override
    public boolean addEdge(Node src, Node dest) {
        addNode(src);
        addNode(dest);
        if (addEdgeFromTo(src, dest) && addEdgeFromTo(dest, src)) {
            numEdges++;
            return true;
        }
        else return false;
    }

    @Override
    public boolean removeEdge(Node src, Node dest) {
        if (!containsNode(src) || !containsNode(dest)) return false;
        else {
            if (removeEdgeFromTo(src, dest) && removeEdgeFromTo(dest, src)) {
                numEdges--;
                return true;
            }
            else return false;
        }
    }
}
