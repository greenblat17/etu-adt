package com.greenblat.adt.lab3.avl_tree;

public class AVLTree<T extends Comparable<T>> {

    private TreeNode<T> root;

    public AVLTree(TreeNode<T> root) {
        this.root = root;
    }

    private TreeNode<T> insert(T value, TreeNode<T> node) {
        if (node == null) {
            return new TreeNode<>(value);
        }

        if (value.compareTo(node.getData()) < 0) {
            node.setLeft(insert(value, node.getLeft()));
        } else if (value.compareTo(node.getData()) > 0) {
            node.setRight(insert(value, node.getRight()));
        } else {
            return node;
        }

        updatedHeight(node);
        return rotation(node);
    }

    private TreeNode<T> rotation(TreeNode<T> node) {
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

    private TreeNode<T> delete(T value, TreeNode<T> node) {
        if (node == null) {
            throw new IllegalStateException();
        }

        if (value.compareTo(node.getData()) < 0) {
            node.setLeft(delete(value, node.getLeft()));
        } else if (value.compareTo(node.getData()) > 0) {
            node.setRight(delete(value, node.getRight()));
        } else {
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
            node.setLeft(delete(value, node.getLeft()));
        }

        updatedHeight(node);
        return rotation(node);
    }

    private TreeNode<T> search(T value, TreeNode<T> node) {
//        if (value.compareTo(node.getData()) < 0)
//            search(value, node.getLeft());
//        else if (value.compareTo(node.getData()) > 0)
//            search(value, node.getRight());
//
//        return node;

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

    private void show(TreeNode<T> node) {
        if (node != null) {
            show(node.getLeft());
            System.out.println(node.getData());
            show(node.getRight());
        }
    }

    private void updatedHeight(TreeNode<T> node) {
        int leftHeight = calculateHeight(node.getLeft());
        int rightHeight = calculateHeight(node.getRight());

        node.setHeight(Math.max(leftHeight, rightHeight) + 1);
    }

    private int calculateHeight(TreeNode<T> node) {
        return node != null ? node.getHeight() : 0;
    }


}
