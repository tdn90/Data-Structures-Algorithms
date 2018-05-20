package dataStruct.GraphPriorVersion;

import dataStruct.GraphPriorVersion.Graph;

public class DirectedGraph extends Graph {

    public DirectedGraph() {
        super();
    }

    /**
     * Abstract method, implemented in DirectedGraph and UndirectedGraph
     * Makes sure that the edge will be added for only the source node's edges
     * @param src: source node
     * @param dest: destination node
     */
    @Override
    public boolean addEdge(Node src, Node dest) {
        addNode(src);
        addNode(dest);
        if (addEdgeFromTo(src, dest)) {
            numEdges++;
            return true;
        }
        else return false;
    }

    @Override
    public boolean removeEdge(Node src, Node dest) {
        if (!containsNode(src) || !containsNode(dest)) return false;
        else {
            if (removeEdgeFromTo(src, dest)) {
                numEdges--;
                return true;
            }
            return false;
        }
    }
}
