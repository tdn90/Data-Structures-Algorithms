package dataStruct;

/**
 * This is my self-implemented Binary Tree data structure.
 * @param <T>: Generic data type of objects to be dealt with,
 *           with T must implements Comparable Interface
 */
public class BinaryTree<T extends Comparable<T>> {
    private static class TreeNode<T> {
        private T content;
        private TreeNode<T> parent;
        private TreeNode<T> leftChild, rightChild;

        TreeNode(T content) {
            this.content = content;
            parent = null;
            leftChild = null;
            rightChild = null;
        }

        /*
        private TreeNode<T> findChild (T object) {
            if (leftChild == null) {
                return rightChild;
            }
            else {
                if (leftChild.content.equals(object)) return leftChild;
                else return rightChild;
            }
        }
        */

        /**
         * Check to see if this tree node is a leaf node (no children)
         * @return true if this node is a leaf node, false otherwise
         */
        private boolean isLeafNode() {
            return rightChild == null && leftChild == null;
        }

        /**
         * @return the only child of this tree node, return null if this tree node has none or two children
         */
        private TreeNode<T> getOnlyChild() {
            if (isLeafNode()) return null;
            else if (rightChild != null && leftChild != null) return null;
            else {
                if (rightChild == null) return leftChild;
                else return rightChild;
            }
        }
    }

    private TreeNode<T> root;

    BinaryTree() {
        root = null;
    }

    /*
    public BinaryTree(TreeNode<T> root) {
        this.root = root;
    }
    */

    /**
     * Find the maximum value in the binary tree, according to the T's natural order
     * @return the maximum value in the binary tree, return null if no max value is found
     */
    public T findMax() {
        if (root == null) return null;
        else return findMax(root).content;
    }

    /**
     * Helper method for findMax
     * @param node: given node to start looking down
     * @return the maximum value in the subtree starting from the node given
     */
    private TreeNode<T> findMax(TreeNode<T> node) {
        if (node.rightChild == null) return node;
        else return findMax(node.rightChild);
    }

    /**
     * Find the minimum value in the binary tree, according to the T's natural order
     * @return the minimum value in the binary tree
     */
    public T findMin() {
        if (root == null) return null;
        else return findMin(root).content;
    }

    /**
     * Helper method for findMin
     * @param node: given node to start looking down
     * @return the minimum value in the subtree starting from the node given
     */
    private TreeNode<T> findMin(TreeNode<T> node) {
        if (node.leftChild == null) return node;
        else return findMin(node.leftChild);
    }

    /**
     * Clear all the elements in this binary tree, effectively empty the tree
     */
    public void clear() {
        root = null;
    }

    /**
     * Add a given element if it is not already in the tree
     * @param object: object to be added to this binary tree
     * @return: true if an element can be added, false otherwise
     */
    public boolean add (T object) {
        if (root == null) {
            root = new TreeNode<>(object);
            return true;
        }
        return add(root, object);
    }

    /**
     * Helper method for add
     * @param node: given node to start looking down the tree to add the given element
     * @param object: the element specified to be added to this tree
     * @return true if the given value can be added to the tree, false otherwise
     */
    private boolean add (TreeNode<T> node, T object) {
        if (node == null) {
            return false;
        }
        int compareVal = object.compareTo(node.content);
        // The object is already in the tree
        if (compareVal == 0) {
            return false;
        }
        else if (compareVal < 0) {
            if (node.leftChild == null) {
                node.leftChild = new TreeNode<>(object);
                node.leftChild.parent = node;
                return true;
            }
            else return add(node.leftChild, object);
        }
        else {
            if (node.rightChild == null) {
                node.rightChild = new TreeNode<>(object);
                node.rightChild.parent = node;
                return true;
            }
            else return add(node.rightChild, object);
        }
    }

    /**
     * Remove an element specified from this binary tree
     * @param object: the element specified to be removed
     * @return: true if the element given can be removed, false otherwise
     */
    public boolean remove(T object) {
        // Case 1: the tree is empty
        if (root == null) return false;

        // Case 2: remove root
        if(root.content.equals(object)) {
            // Case a: root node is a leaf node
            if (root.isLeafNode()) {
                root = null;
                return true;
            }
            // Case b: remove root that has only the left branch
            else if (root.rightChild == null) {
                root = root.leftChild;
                root.parent = null;
            }
            // Case c: remove root that has only the right branch
            else if (root.leftChild == null) {
                root = root.rightChild;
                root.parent = null;
            }
            // Case d: Remove root that has both branch
            else {
                T rootSuccessor = getSuccessor(object);
                root.content = rootSuccessor;
                remove(rootSuccessor, root.rightChild);
            }
            return true;
        }

        // Case 3: remove other node
        return remove(object, root);
    }
    /**
     * Pre-condition: this tree has more than one node
     * Remove a specified element if it is in the tree.
     * @param object: object to be removed from this binary tree
     * @return: true if an element is in the tree, false otherwise
     */
    private boolean remove(T object, TreeNode<T> top) {
        TreeNode<T> remNode = findNode(top, object);

        // Case 1: object is not in the tree
        if (remNode == null) return false;

        else {
            // Case 2: object is leaf node
            if (remNode.isLeafNode()) {
                TreeNode<T> parent = remNode.parent;
                if (parent.leftChild == null) {
                    parent.rightChild = null;
                }
                else {
                    if (parent.leftChild.content.equals(remNode.content)) {
                        parent.leftChild = null;
                    }
                    else {
                        parent.rightChild = null;
                    }
                }
                return true;
            }
            // Case 3: object is not leaf node
            else {
                TreeNode<T> onlyChild = remNode.getOnlyChild();
                // Case: has two children
                if (onlyChild == null) {
                    T content = findMin(remNode.rightChild).content;
                    remNode.content = content;
                    remove(content, remNode.rightChild);
                    return true;
                }
                // Case: has one children
                else {
                    TreeNode<T> parent = remNode.parent;
                    if (parent.leftChild == null) {
                        parent.rightChild = onlyChild;
                    }
                    else {
                        if (parent.leftChild.content.equals(remNode.content)) {
                            parent.leftChild = onlyChild;
                        }
                        else {
                            parent.rightChild = onlyChild;
                        }
                    }
                    onlyChild.parent = parent;
                    return true;
                }
            }
        }
    }

    /**
     * Check to see if the given element is in this binary tree.
     * @param object: specified element to be found
     * @return true if the specified element is in this tree, false otherwise
     */
    public boolean contains(T object) {
        return root != null && findNode(object) != null;
    }

    /**
     * Retrieve the node with a specified content
     * @param object: object to be found in the binary tree
     * @return the node in the tree whose content is the specified object
     */
    public TreeNode<T> findNode(T object) {
        return findNode(root, object);
    }

    /**
     * Helper method for findNode
     * @param node: given starting node to search down the tree
     * @param object: object to be found
     * @return: the node in the subtree (starting from the node specified) whose content is the specified object
     */
    private TreeNode<T> findNode(TreeNode<T> node, T object) {
        if (node == null) return null;

        if (node.content.equals(object)) return node;
        else if (node.content.compareTo(object) < 0) {
            return findNode(node.rightChild, object);
        }
        else {
            return findNode(node.leftChild, object);
        }
    }

    private void visit(TreeNode<T> node) {
        System.out.println(node.content.toString());
    }

    /**
     * Traverse this binary tree, starting from the given node
     * @return: all objects in this tree in order
     */
    private void inOrderTraversal(TreeNode<T> node) {
        if (node == null) return;
        inOrderTraversal(node.leftChild);
        visit(node);
        inOrderTraversal(node.rightChild);
    }

    /**
     * Traverse this whole binary tree
     * @return: all objects in this tree in order
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    /**
     * Predecessor is the node with the largest value that is smaller
     * than the given node
     * @param object: object specified
     * @return: the predecessor of an object in this tree,
     * return null if no predecessor is found
     */
    public T getPredecessor(T object) {
        TreeNode<T> node = findNode(object);
        if (node == null) return null;
        else {
            if (node.leftChild != null) return findMax(node.leftChild).content;
            else {
                while (node.parent != null) {
                    if (node.parent.content.compareTo(object) < 0) return node.parent.content;
                    else node = node.parent;
                }
                return null;
            }
        }
    }

    /**
     * Sort all elements in the binary tree and put them in the list
     * @return: a list of sorted elements in descending order
     */
    public List<T> sortDescending() {
        List<T> result = new ArrayList<>();
        if (root == null) return result;
        else {
            T max = findMax();
            result.add(max);
            T predecessor = getPredecessor(max);
            while (predecessor != null) {
                result.add(predecessor);
                predecessor = getPredecessor(predecessor);
            }
            return result;
        }
    }

    /**
     * Successor is the node with the smallest value that is larger than
     * the given node
     * @param object: object given
     * @return: the successor's value of an object in this tree,
     * return null if no successor is found
     */
    public T getSuccessor(T object) {
        TreeNode<T> node = findNode(object);
        if (node == null) return null;
        else {
            if (node.rightChild != null) return findMin(node.rightChild).content;
            else {
                while (node.parent != null) {
                    if (node.parent.content.compareTo(object) > 0) return node.parent.content;
                    else node = node.parent;
                }
                return null;
            }
        }
    }

    /**
     * Sort all elements in the binary tree and put them in the list
     * @return: a list of sorted elements in ascending order
     */
    public List<T> sortAscending() {
        List<T> result = new ArrayList<>();
        if (root == null) return result;
        else {
            T min = findMin();
            result.add(min);
            T successor = getSuccessor(min);
            while (successor != null) {
                result.add(successor);
                successor = getSuccessor(successor);
            }
            return result;
        }
    }

    /**
     * The depth of a node is the number of edges from the root to the node
     * Find the depth of the tree, going from the given node
     * @param object: object specified
     * @return: this depth of the object given, return -1 if the object is not in the tree
     */
    public int depth(T object) {
       int result = -1;
       TreeNode<T> current = root;
       while (current != null) {
           result++;
           if (current.content.compareTo(object) == 0) return result;
           else if (object.compareTo(current.content) > 0) {
               current = current.rightChild;
           }
           else {
               current = current.leftChild;
           }
       }
       return -1;
    }

    /**
     * Print out the whole binary tree
     * @return the String representation of the whole binary tree
     */
    public String toString() {
        if (root == null) return "Empty";
        else return toString(root, 0, "(Root)");
    }

    /**
     * Helper method for toString()
     * @param node: given node
     * @param indent: amount of indentation (tabs)
     * @return the String representation of a subtree
     * starting from the given node with a specified indentation
     */
    private String toString(TreeNode<T> node, int indent, String position) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < indent; i ++){
            result.append("\t\t\t");
        }
        result.append(node.content.toString()).append(position).append("\n");
        if (node.leftChild == null && node.rightChild == null) {
            return result.toString();
        }
        else {
            if (node.leftChild != null) {
                result.append(toString(node.leftChild, indent + 1, "(Left)"));
            }
            if (node.rightChild != null) {
                result.append(toString(node.rightChild, indent + 1, "(Right)"));
            }
            return result.toString();
        }
    }
}
