package dataStruct.GraphPriorVersion;

import java.util.*;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * This is my self-implemented version of Graph data structure.
 * Feature: Each node and edge in this graph is unique.
 * @param <T>: Generic data type that this graph represents.
 */
public abstract class Graph<T> {
    private Map<Node<T>, Set<Edge<T>>> adjacencySets;
    private int numNodes;
    int numEdges;

    public Graph() {
        adjacencySets = new HashMap<>();
        numNodes = 0;
        numEdges = 0;
    }

    /**
     * Abstract method, implemented in DirectedGraph and UndirectedGraph
     */
    public abstract boolean addEdge(Node src, Node dest);
    public abstract boolean removeEdge(Node src, Node dest);

    /**
     * Add a node to this graph. Check to see if this operation is possible.
     * @param node: node to be added
     * @return true if the node specified is successfully added, false otherwise
     */
    public boolean addNode(Node<T> node) {
        if (adjacencySets.containsKey(node)) return false;
        else {
            Set<Edge<T>> edgeSet = new HashSet<>();
            adjacencySets.put(node, edgeSet);
            numNodes++;
            return true;
        }
    }

    /**
     * Get all the neighbors of a specific node.
     * Neighbors are defined as nodes that connect to the given node.
     * @param node: given node to get the neighbors
     * @return: a set of nodes that are neighbors to the given node.
     * Return an empty set if the node has no neighbor.
     * Return null if the node is not in the graph.
     */
    public Set<Node<T>> getNeighbors(Node<T> node) {
        Set<Edge<T>> edges = adjacencySets.get(node);
        if (edges == null) return null;
        else {
            Set<Node<T>> result = new HashSet<>();
            for (Edge<T> edge : edges) {
                result.add(edge.getDest());
            }
            return result;
        }
    }

    /**
     * Helper method to addEdge.
     * Pre-condition: the source node and destination node is already in the graph
     * @param src: given source node
     * @param dest: given destination node
     * @return true if an edge can be added, false otherwise
     */
    boolean addEdgeFromTo(Node<T> src, Node<T> dest) {
        Edge<T> newEdge = new Edge<>(src, dest);
        Set<Edge<T>> setEdges = adjacencySets.get(src);
        if (!setEdges.contains(newEdge)) {
            setEdges.add(newEdge);
            return true;
        }
        else return false;
    }

    /**
     * Helper method to removeEdge.
     * Pre-condition: the source node and destination node is already in the graph
     * @param src: given source node
     * @param dest: given destination node
     * @return true if an edge can be removed, false otherwise
     */
    boolean removeEdgeFromTo(Node<T> src, Node<T> dest) {
        Edge<T> toRemove = new Edge<>(src, dest);
        Set<Edge<T>> setEdges = adjacencySets.get(src);
        if (setEdges.contains(toRemove)) {
            setEdges.remove(toRemove);
            return true;
        }
        else return false;
    }

    /**
     * Get the number of nodes in the graph
     * @return the number of nodes in this graph
     */
    public int getNumNodes() {
        return numNodes;
    }

    /**
     * Get the number of edges in this graph
     * @return: the total number of edges in this graph
     */
    public int getNumEdges() {
        return numEdges;
    }

    /**
     * Return every Nodes in this graph
     * @return: the set of all Nodes in this graph
     */
    public Set<Node<T>> getAllNodes() {
        return adjacencySets.keySet();
    }

    /**
     * @param node: given node
     * @return: a set of all edges of a given node
     */
    public Set<Edge<T>> getNodeEdges(Node<T> node) {
        return adjacencySets.get(node);
    }

    /**
     *
     * @param node: node to be checked
     * @return: true if the node is an even node, false otherwise
     */
    public boolean isEvenNode(Node<T> node) {
        if (!containsNode(node)) return false;
        Set<Edge<T>> nodeEdges = getNodeEdges(node);
        return nodeEdges != null && (nodeEdges.size()) % 2 == 0;
    }

    /**
     * Determine whether the graph contains the node specified
     * @param node: the node to be checked.
     * @return true if the graph contains the node, false otherwise
     */
    public boolean containsNode(Node<T> node) {
        return adjacencySets.containsKey(node);
    }

    /**
     * Determine whether a graph contains the element specified
     * @param element: the element to be checked
     * @return true if the graph contains the element, false otherwise
     */
    public boolean containsElement(T element) {
        Node<T> newNode = new Node<>(element);
        return containsNode(newNode);
    }

    /**
     * Traverse this graph by breadth-first search
     * @param start: starting Node to traverse
     * @return: the path to traverse the graph with breadth-first search
     */
    public List<Node<T>> breathFirstTraversal(Node<T> start) {
        List<Node<T>> visited = new LinkedList<>();
        LinkedList<Node<T>> toVisit = new LinkedList<>();

        toVisit.offer(start);
        while (toVisit.peek() != null) {
            Node<T> current = toVisit.poll();
            visited.add(current);

            for (Node<T> neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor) && !toVisit.contains(neighbor)) {
                    toVisit.offer(neighbor);
                }
            }
        }
        return visited;
    }

    /**
     * Traverse the graph using depth-first search
     * @param start: start node
     * @return the path to traverse the graph with depth-first search, return null if the node is not in the graph
     */
    public List<Node<T>> depthFirstTraversal(Node<T> start) {
        if (!containsNode(start)) return null;
        List<Node<T>> visited = new LinkedList<>();
        Stack<Node<T>> toVisit = new Stack<>();

        toVisit.add(start);
        while (!toVisit.empty()) {
            Node<T> current = toVisit.pop();
            visited.add(current);

            for (Node<T> neighbor : getNeighbors(current)) {
                if (!visited.contains(neighbor) && !toVisit.contains(neighbor)) {
                    toVisit.push(neighbor);
                }
            }
        }
        return visited;
    }

    /**
     * Attempt to find the shortest path between 2 nodes
     * @param start: the source node to start from
     * @param end: the destination node
     * @return: a list of nodes in the path from the source node to the destination node
     */
    public List<Node<T>> findShortestPath(Node<T> start, Node<T> end) {
        if (!containsNode(start) || !containsNode(end)) return null;
        else {
            if (start.equals(end)) return new LinkedList<>();

            Set<Node<T>> visited = new HashSet<>();
            ConcurrentLinkedQueue<Node<T>> toVisit = new ConcurrentLinkedQueue<>();
            Map<Node<T>, ArrayList<Node<T>>> trackMap = new HashMap<>();

            toVisit.add(start);

            ArrayList<Node<T>> path = new ArrayList<>();
            path.add(start);
            trackMap.put(start, path);

            while(toVisit.peek() != null) {
                Node<T> current = toVisit.poll();
                visited.add(current);

                for (Node<T> neighbor : getNeighbors(current)) {
                    if (!toVisit.contains(neighbor) && !visited.contains(neighbor)) {
                        toVisit.offer(neighbor);
                        ArrayList<Node<T>> currentPath = (ArrayList<Node<T>>) trackMap.get(current).clone();
                        currentPath.add(neighbor);
                        if (neighbor.equals(end)) {
                            return currentPath;
                        }
                        trackMap.put(neighbor, currentPath);
                    }
                }

            }
            return null;
        }
    }
}
