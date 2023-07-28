package com.leetcode;

/**
 * @author：江南
 * @Classname MaxDepth
 * @Description 二叉树的最大深度 https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @Date 2022/4/21 20:40
 */
public class MaxDepth {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);

        TreeNode right1 = new TreeNode(15);
        TreeNode right2 = new TreeNode(7);

        treeNode.left = left;

        right.left = right1;

        right.right = right2;

        treeNode.right = right;


        System.out.println(maxDepth(treeNode));


    }


    private static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;

    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
