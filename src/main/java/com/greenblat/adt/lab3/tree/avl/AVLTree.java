package com.greenblat.adt.lab3.tree.avl;

import com.greenblat.adt.lab1.collections.ArrayList;
import com.greenblat.adt.lab1.collections.LinkedList;
import com.greenblat.adt.lab1.collections.List;
import com.greenblat.adt.lab1.collections.Stack;

public class AVLTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public TreeNode<T>  makeAVLTree(com.greenblat.adt.lab3.tree.binary.TreeNode<T> binaryTree) {
        TreeNode<T> avlNode = null;
        Stack<com.greenblat.adt.lab3.tree.binary.TreeNode<T>> stack = new LinkedList<>();
        stack.push(binaryTree);
        while (!stack.isEmpty()) {
            com.greenblat.adt.lab3.tree.binary.TreeNode<T> node = stack.pop();
            avlNode = insert(node.getValue(), avlNode);

            if (node.getLeft() != null)
                stack.push(node.getLeft());
            if (node.getRight() != null)
                stack.push(node.getRight());
        }

        depthSearchValuesLNR(avlNode);

        return avlNode;
    }

    private TreeNode<T> insert(T value) {
        return insert(value, root);
    }


    public TreeNode<T> insert(T value, TreeNode<T> node) {
        if (node == null) {
            return new TreeNode<>(value);
        }

        if (value.compareTo(node.getData()) < 0)
            node.setLeft(insert(value, node.getLeft()));
        else if (value.compareTo(node.getData()) > 0)
            node.setRight(insert(value, node.getRight()));
        else
            return node;

        updatedHeight(node);
        return balancing(node);
    }

    private TreeNode<T> balancing(TreeNode<T> node) {
        int diffHeight = node != null ? calculateHeight(node.getLeft()) - calculateHeight(node.getRight()) : 0;

        if (diffHeight > 1) {
            int diffHeightLeft = node.getLeft() != null ?
                    calculateHeight(node.getLeft().getLeft()) - calculateHeight(node.getLeft().getRight()) : 0;
            if (diffHeightLeft < 0) {
                node.setLeft(rotationToLeft(node.getLeft()));
            }

            return rotationToRight(node);
        } else if (diffHeight < -1) {
            int diffHeightRight = node.getRight() != null ?
                    calculateHeight(node.getRight().getLeft()) - calculateHeight(node.getRight().getRight()) : 0;
            if (diffHeightRight > 0) {
                node.setRight(rotationToRight(node.getRight()));
            }

            return rotationToLeft(node);
        }

        return node;
    }

    private TreeNode<T> rotationToLeft(TreeNode<T> node) {
        TreeNode<T> rightNode = node.getRight();
        TreeNode<T> leftChildOfRightNode = rightNode.getLeft();

        rightNode.setLeft(node);
        node.setRight(leftChildOfRightNode);

        updatedHeight(node);
        updatedHeight(rightNode);

        return rightNode;
    }

    private TreeNode<T> rotationToRight(TreeNode<T> node) {
        TreeNode<T> leftNode = node.getLeft();
        TreeNode<T> rightChildOfLeftNode = leftNode.getRight();

        node.setLeft(rightChildOfLeftNode);
        leftNode.setRight(node);

        updatedHeight(node);
        updatedHeight(leftNode);

        return leftNode;
    }

    private void delete(T value) {
        root = delete(value, root);
    }

    public TreeNode<T> delete(T value, TreeNode<T> node) {
        if (node == null) {
            throw new IllegalStateException();
        }

        if (value.compareTo(node.getData()) < 0)
            node.setLeft(delete(value, node.getLeft()));
        else if (value.compareTo(node.getData()) > 0)
            node.setRight(delete(value, node.getRight()));
        else {
            if (node.getLeft() == null)
                return node.getRight();
            if (node.getRight() == null)
                return node.getLeft();

            // search max value
            TreeNode<T> tmp = node.getLeft();
            while (tmp.getRight() != null) {
                tmp = tmp.getRight();
            }

            node.setData(tmp.getData());
            node.setLeft(delete(node.getData(), node.getLeft()));
        }

        updatedHeight(node);
        return balancing(node);
    }

    public TreeNode<T> search(T value, TreeNode<T> node) {
        while (node != null) {
            if (value.compareTo(node.getData()) < 0)
                node = node.getLeft();
            else if (value.compareTo(node.getData()) > 0)
                node = node.getRight();
            else
                return node;
        }

        throw new IllegalStateException();
    }

    private void updatedHeight(TreeNode<T> node) {
        int leftHeight = calculateHeight(node.getLeft());
        int rightHeight = calculateHeight(node.getRight());

        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    private int calculateHeight(TreeNode<T> node) {
        return node != null ? node.getHeight() : 0;
    }


    public void depthSearchValuesNLR(TreeNode<T> root) {
        Stack<TreeNode<T>> stack = new LinkedList<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode<T> node = stack.pop();

            System.out.print(node.getData() + " ");

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }


        }

        System.out.println();
    }

    public void depthSearchValuesLNR(TreeNode<T> node) {
        Stack<TreeNode<T>> stack = new LinkedList<>();

        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                System.out.print(node.getData() + " ");
                node = node.getRight();
            }
        }

        System.out.println();
    }

    public void depthSearchValuesLRN(TreeNode<T> node) {
        Stack<TreeNode<T>> stack = new LinkedList<>();
        TreeNode<T> lastVisited = null;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                TreeNode<T> peekNode = stack.peek();
                if (peekNode.getRight() != null && lastVisited != peekNode.getRight()) {
                    node = peekNode.getRight();
                } else {
                    System.out.print(peekNode.getData() + " ");
                    lastVisited = stack.pop();
                }
            }
        }

        System.out.println();
    }

    public void breadthSearchValues(TreeNode<T> node) {
        List<TreeNode<T>> queue = new ArrayList<>();
        queue.add(node);

        int idx = 0;
        while (idx < queue.size()) {

            node = queue.get(idx);
            System.out.print(node.getData() + " ");

            if (node.getLeft() != null)
                queue.add(node.getLeft());
            if (node.getRight() != null)
                queue.add(node.getRight());

            idx++;
        }

        System.out.println();
    }


}
