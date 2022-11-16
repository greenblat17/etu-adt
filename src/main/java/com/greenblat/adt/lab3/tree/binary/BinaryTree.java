package com.greenblat.adt.lab3.tree.binary;

public class BinaryTree {

    public static <T> void depthFirstSearch(TreeNode<T> node) {
        if (node != null) {
            depthFirstSearch(node.getLeft());
            System.out.print(node.getValue() + " ");
            depthFirstSearch(node.getRight());
        }
    }


}
