package tnguyen4.hw5;

import edu.princeton.cs.algs4.In;

/** Standard undirected Grah implementation, as starting point for Q3 on HW5. */
public class Graph {
    final int V;
    int E;
    Bag<Integer>[] adj;
    
    /**
     * Initializes an empty graph with <tt>V</tt> vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if <tt>V</tt> < 0
     */
    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /** Added this method for day20 to load graph from file. */
    public Graph(In in) {
    	this (in.readInt());
    	int E = in.readInt();
    	for (int i = 0; i < E; i++) {
    		int v = in.readInt();
    		int w = in.readInt();
    		addEdge (v,w);
    	}
    }

    public int V() { return V; }
    public int E() { return E; }


    /** Adds the undirected edge v-w to this graph. */
    public void addEdge(int v, int w) {
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


    /** Returns the vertices adjacent to vertex <tt>v</tt>. */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    /** Returns the degree of vertex <tt>v</tt>. */
    public int degree(int v) {
        return adj[v].size();
    }

    /** Fill in this method to determine if undirected graph is connected. */
    public boolean connected() {
        boolean[] marked = new boolean[V];
    	dfs(marked, 0);
    	for (boolean visited : marked) if(!visited) return false;
    	return true;
    }

    /** Helper method for connected() **/
    private void dfs(boolean[] marked, int v) {
        marked[v] = true;
        for (int neighbor : adj(v)) {
            if(!marked[neighbor]) dfs(marked, neighbor);
        }
    }

    /** Implement as part of HW5, Question 3. */
    public int findSafeVertex() {
    	if (V == 0) return -1;
        boolean[] marked = new boolean[V];
        boolean[] isLeaves = new boolean[V];
        dfsHelper(marked, isLeaves, 0);
        for (boolean mark : marked) if (!mark) return -1;
        for (int i = 0; i < isLeaves.length; i++) if (isLeaves[i]) return i;
        return -1;
    }

    private void dfsHelper(boolean[] marked, boolean[] isLeaves, int v) {
        marked[v] = true;
        boolean leaf = true;   // initially, assuming that this is a "leaf"
        for (int neighbor : adj(v)) {
            if(!marked[neighbor]) {
                leaf = false;   // oops, not a leaf. Make it false
                dfsHelper(marked, isLeaves, neighbor);
            }
        }
        isLeaves[v] = leaf;
    }
    
    /** The length of the shortest path from that vertex to the
     *  furthest vertex from v in the graph. Conduct a BFS and 
     *  find largest distance.
     * @param s: j
     * @return
     */
    int eccentricity(int s) {
        int max = 0;
        boolean marked[] = new boolean[V];
        int numLengthTo[] = new int[V];
        Queue<Integer> toVisit = new Queue<>();
        toVisit.enqueue(s);
        marked[s] = true;

        while (!toVisit.isEmpty()) {
            int current = toVisit.dequeue();
            for (int neighbor : adj(current)) {
                if (!marked[neighbor]) {
                    marked[neighbor] = true;
                    numLengthTo[neighbor] = numLengthTo[current] + 1;
                    if (numLengthTo[neighbor] > max) max = numLengthTo[neighbor];
                    toVisit.enqueue(neighbor);
                }
            }
        }
    	return max;
    }
    
    /** Implement as part of HW5, Question 3. */
    public int diameter () {
        if (V == 0 || !connected()) return -1;
        int result = 0;
        for (int i = 0; i < V; i++) {
            if (eccentricity(i) > result) result = eccentricity(i);
        }
    	return result;
    }
    
    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

}
