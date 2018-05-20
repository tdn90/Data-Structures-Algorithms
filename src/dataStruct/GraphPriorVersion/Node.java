package dataStruct.GraphPriorVersion;

import java.util.Objects;

public class Node<T> {
    private T content;

    public Node(T content) {
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public String toString() {
        return content.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(getContent(), node.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getContent());
    }
}
