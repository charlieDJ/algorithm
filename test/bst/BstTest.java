package bst;

import algorithm.bst.BinarySearchTree;

/**
 * @author dengjia on 2020/1/16
 */
public class BstTest {
    public static void main(String[] args) {
        final BinarySearchTree bst = new BinarySearchTree();
        bst.insert(33);
        bst.insert(16);
        bst.insert(50);
        bst.insert(13);
        bst.insert(18);
        bst.insert(34);
        bst.insert(58);
        bst.insert(15);
        bst.insert(17);
        bst.insert(25);
        bst.insert(51);
        bst.insert(66);
        bst.insert(19);
        bst.insert(27);
        bst.insert(55);
        bst.delete(55);
        System.err.println(bst.toString());
    }
}
