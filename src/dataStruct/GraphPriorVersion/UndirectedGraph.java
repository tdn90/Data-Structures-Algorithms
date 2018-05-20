package dataStruct.GraphPriorVersion;

import dataStruct.GraphPriorVersion.Graph;

public class UndirectedGraph extends Graph {

    public UndirectedGraph() {
        super();
    }

    /**
     * Abstract method, implemented in DirectedGraph and UndirectedGraph
     *
     * @param src
     * @param dest
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
