package com.greenblat.adt.lab3.avl_tree;

public class TreeNode<T extends Comparable<T>> {

    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;
    private int height = 1;

    public TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public int getHeight() {
        return height;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
