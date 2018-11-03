package com.project.lab4.util;

import java.util.List;

public class Tree<T extends Comparable<T>> {
    class Node {
        T elem;
        Node left;
        Node right;

        public Node(T t) {
            elem = t;
            left = null;
            right = null;

        }
    }

    private Node root;

    public Tree() {
        root = null;
    }

    public Tree(T t) {
        root.elem = t;
    }

    public void add(T t) {
        if (root == null) {
            root = new Node(t);
        } else {
            addNode(root, t);
        }
    }

    private void addNode(Node node, T t) {
        if (t.compareTo(node.elem) > 0) {
            if (node.right == null) {
                node.right = new Node(t);
            } else addNode(node.right, t);
        } else if (t.compareTo(node.elem) < 0) {
            if (node.left == null) {
                node.left = new Node(t);
            } else addNode(node.left, t);
        }

    }

    public boolean search(T t) {
        try {
            return (search(root, t) != null);
        } catch (NullPointerException ex) {
            System.out.println(ex.getMessage() + " is returned");
        }
        return false;
    }

    private Node search(Node node, T t) {
        if (node.elem == null & node.left == null & node.right == null)
            throw new NullPointerException("null element");
        if(node==null||node.elem==t){
            return node;
        }
        if (t.compareTo(node.elem) < 0) {
            return search(node.left, t);
        }
        else {
            return search(node.right, t);
        }
    }

    public void goLeftRootRight() {
        System.out.println("LeftRootRight: ");
        goLeftRootRight(root);
        System.out.println();
    }

    private void goLeftRootRight(Node node) {
        if (node != null) {
            goLeftRootRight(node.left);
            System.out.print(node.elem + " ");
            goLeftRootRight(node.right);
        }
    }

    public void goRootLeftRight() {
        System.out.println("RootLeftRight: ");
        goRootLeftRight(root);
        System.out.println();
    }

    private void goRootLeftRight(Node node) {
        if (node != null) {
            System.out.print(node.elem + " ");
            goRootLeftRight(node.left);
            goRootLeftRight(node.right);
        }
    }

    public void goLeftRightRoot() {
        System.out.println("LeftRightRoot: ");
        goLeftRightRoot(root);
        System.out.println();
    }

    private void goLeftRightRoot(Node node) {

        if (node != null) {
            goRootLeftRight(node.left);
            goRootLeftRight(node.right);
            System.out.print(node.elem + " ");
        }
    }

    public void delete(T t) {
        try {
            deleteNode(t);
        } catch (NullPointerException e) {
            System.out.println("You have't got this element to delete");
        }
    }

    private void deleteNode(T t) {
        Node parent = null;
        Node current = root;

        while (current != null) {
            if (t.compareTo(current.elem) > 0) {
                parent = current;
                current = current.right;

            } else if (t.compareTo(current.elem) < 0) {
                parent = current;
                current = current.left;
            } else
                break;
        }

        if (current == null) throw new NullPointerException("No such elements to delete");

        if (current.left == null && current.right == null) {
            changeNode(parent, current, null);
        } else if (current.left == null) {
            changeNode(parent, current, current.right);
        } else if (current.right == null) {
            changeNode(parent, current, current.left);
        } else {
            Node leftmostParent = current;
            Node leftmost = current.right;
            while (leftmost.left != null) {
                leftmostParent = leftmost;
                leftmost = leftmost.left;
            }
            current.elem = leftmost.elem;
            changeNode(leftmostParent, leftmost, leftmost.right);
        }
    }

    private void changeNode(Node parent, Node oldChild, Node newChild) {
        if (parent == null) root = newChild;
        else if (parent.left == oldChild) parent.left = newChild;
        else if (parent.right == oldChild) parent.right = newChild;
    }


}
