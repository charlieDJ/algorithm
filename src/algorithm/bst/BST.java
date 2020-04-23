package algorithm.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * @author dengjia
 * @date 2019/7/22 17:43
 */
public class BST<E extends Comparable<E>> {
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "e=" + e +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树中添加新的元素 e
     *
     * @param e 元素e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二分搜索树中插入元素 e，递归算法
     * 返回插入新节点后二分搜索树的根
     *
     * @param node 根节点
     * @param e    元素
     * @return 根节点
     */
    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            // 因为是递归调用，要拿一个元素去借住这个变化。
            // 如果右侧的 node.left 为空，递归下去后返回 node 的左子节点
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        // 递归后，node 没有变化
        return node;
    }

    /**
     * 看二分搜索树中是否包含元素e
     *
     * @param e 元素
     * @return true：包含元素
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void inOrderNormal() {
        inOrderNormal(root);
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void postOrderNormal() {
        postOrderNormal(root);
    }

    /**
     * 非递归后序遍历
     * 后序：左 -> 右-> 根，我们可以把后序当作：根 -> 右-> 左，再反转一下
     * @param node 根节点
     */
    private void postOrderNormal(Node node) {
        if (node == null) {
            return;
        }
        final List<E> list = new ArrayList<>();
        final Stack<Node> q = new Stack<>();
        q.add(node);
        while (!q.isEmpty()) {
            final Node pop = q.pop();
            list.add(pop.e);
            if(pop.left!=null){
                q.add(pop.left);
            }
            if(pop.right!=null){
                q.add(pop.right);
            }
        }
        Collections.reverse(list);
        System.out.println(list);
    }


    public void preOrderNormal() {
        preOrderNormal(root);
    }

    /**
     * 非递归前序遍历
     *
     * @param node 根节点
     */
    private void preOrderNormal(Node node) {
        if (node == null) {
            return;
        }
        final Stack<Node> q = new Stack<>();
        q.add(node);
        while (!q.isEmpty()) {
            final Node pop = q.pop();
            System.out.println(pop.e);
            if (node.right != null) {
                q.add(node.right);
            }
            if (node.left != null) {
                q.add(node.left);
            }
        }

    }

    /**
     * 非递归中序遍历
     *
     * @param node 根节点
     */
    private void inOrderNormal(Node node) {
        if (node == null) {
            return;
        }
        // 使用栈来帮助遍历
        final Stack<Node> q = new Stack<>();
        q.add(node);
        while (!q.isEmpty()) {
            // 左子树不为空，将左子节点加入栈，并向左子树前进
            if (node.left != null) {
                q.add(node.left);
                node = node.left;
            } else {
                // 遍历并移除节点
                final Node pop = q.pop();
                System.out.println(pop.e);
                // 如果有右子节点，将右子节点加入到栈里
                if (pop.right != null) {
                    q.add(pop.right);
                }
            }
        }
    }

    /**
     * 看以node为根的二分搜索树中是否包含元素e, 递归算法
     *
     * @param node 根节点
     * @param e    元素
     * @return true：包含元素e
     */
    private boolean contains(Node node, E e) {

        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            // e.compareTo(node.e) > 0
            return contains(node.right, e);
        }
    }

    // 从二分搜索树中删除元素为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 寻找二分搜索树的最小元素
     *
     * @return 最小节点
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        Node minNode = minimum(root);
        return minNode.e;
    }

    /**
     * 返回以node为根的二分搜索树的最小值所在的节点
     *
     * @param node 节点
     * @return 最小节点
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 寻找二分搜索树的最大元素
     *
     * @return 最大节点
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }

        return maximum(root).e;
    }

    /**
     * 返回以node为根的二分搜索树的最大值所在的节点
     *
     * @param node 节点
     * @return 最大节点
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }

        return maximum(node.right);
    }

    /**
     * 从二分搜索树中删除最小值所在节点, 返回最小值
     *
     * @return 最小元素
     */
    public E removeMin() {
        E ret = minimum();
        //注意这里，我们从root为根的树中删除了最小值，返回删除后树的根节点，这个点就是新的root
        root = removeMin(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最小节点
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node 节点
     * @return 删除的节点
     */
    private Node removeMin(Node node) {
        // 处理递归到底的情况。
        // 我们先把该节点的右子树节点保存，然后删除该右子树节点，最后把右子树节点返回即可
        if (node.left == null) {
            Node rightNode = node.right;
            //左节点为空后，让右子树节点也为空，相当于脱离了树
            node.right = null;
            size--;
            //返回右子树是为了后面的绑定操作
            return rightNode;
        }
        // 没有递归到底的情况，那么就递归调用其左子树，这个调用的过程会返回被删除节点的右子树，将返回的右子树重新绑定到上一层的node的左节点上，相当于彻底删除了那个元素
        node.left = removeMin(node.left);
        // 删除后，根节点依然是node，返回即可
        return node;
    }

    /**
     * 从二分搜索树中删除最大值所在节点
     *
     * @return 最大元素
     */
    public E removeMax() {
        E ret = maximum();
        root = removeMax(root);
        return ret;
    }

    /**
     * 删除掉以node为根的二分搜索树中的最大节点
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node 节点
     * @return 最大节点
     */
    private Node removeMax(Node node) {

        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 删除掉以node为根的二分搜索树中值为 e 的节点, 递归算法
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node 根节点
     * @param e    元素
     * @return
     */
    private Node remove(Node node, E e) {

        if (node == null) {
            return null;
        }
        // 元素在左子树
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            // 元素在右子树
            node.right = remove(node.right, e);
            return node;
        } else {
            // 查找到了待删除元素，e.compareTo(node.e) == 0
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                //保存右子树节点
                Node rightNode = node.right;
                //将右子树与二叉树断开联系
                node.right = null;
                size--;
                //返回右子树
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况
            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }


    }


    public static void main(String[] args) {
        final BST<Integer> bst = new BST<>();
        bst.add(6);
        bst.add(5);
        bst.add(7);
        bst.add(4);
        bst.postOrderNormal();
    }

}
