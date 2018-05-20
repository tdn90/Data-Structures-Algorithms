package dataStruct.GraphPriorVersion;

import java.util.Objects;

public class Edge<T> {
    private Node<T> src;
    private Node<T> dest;

    public Edge(Node<T> src, Node<T> dest) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(src, edge.src) &&
                Objects.equals(dest, edge.dest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, dest);
    }

    public Node<T> getSrc() {
        return src;
    }

    public Node<T> getDest() {
        return dest;
    }
}
