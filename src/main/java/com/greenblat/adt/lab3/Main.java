package com.greenblat.adt.lab3;

import com.greenblat.adt.lab3.tree.avl.AVLTree;
import com.greenblat.adt.lab3.tree.binary.BinaryTree;
import com.greenblat.adt.lab3.tree.binary.TreeNode;

public class Main {

    public static void main(String[] args) {

        // task 1, 2
        TreeNode<Integer> binaryTree =  BinaryTree.parseSequence();

        // task 3
        AVLTree<Integer> avlTree = new AVLTree<>();
        com.greenblat.adt.lab3.tree.avl.TreeNode<Integer> node = avlTree.makeAVLTree(binaryTree);

        // task 4
        System.out.print("Поиск в глубину (прямой обход): ");
        avlTree.depthSearchValuesNLR(node);

        System.out.print("Поиск в глубину (центрированный обход): ");
        avlTree.depthSearchValuesLNR(node);

        System.out.print("Поиск в глубину (обратный обход): ");
        avlTree.depthSearchValuesLRN(node);

        System.out.print("Поиск в ширину: ");
        avlTree.breadthSearchValues(node);
    }
}
