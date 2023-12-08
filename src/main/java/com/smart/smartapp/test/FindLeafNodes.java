package com.smart.smartapp.test;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    String valueName;
    List<TreeNode> children;

    TreeNode(String valueName) {
        this.valueName = valueName;
        this.children = new ArrayList<>();
    }

    boolean isLeaf() {
        return children.isEmpty();
    }
}

public class FindLeafNodes {
    public static void main(String[] args) {
        TreeNode root = buildTree();
        TreeNode parentNode = findNode(root, "王者");

        if (parentNode != null) {
            List<TreeNode> leafNodes = findLeafNodes(parentNode);
            for (TreeNode leafNode : leafNodes) {
                System.out.println(leafNode.valueName);
            }
        }
    }

    public static TreeNode findChild(TreeNode node, String valueName) {
        for (TreeNode child : node.children) {
            if (child.valueName.equals(valueName)) {
                return child;
            }
        }
        return null;
    }

    public static TreeNode buildTree() {
        TreeNode root = new TreeNode("");
        String[] paths = {"/游戏/王者/吃鸡", "/扩列"};
        for (String path : paths) {
            String[] components = path.split("/");
            TreeNode current = root;
            for (String component : components) {
                if (!component.isEmpty()) {
                    TreeNode child = findChild(current, component);
                    if (child == null) {
                        child = new TreeNode(component);
                        current.children.add(child);
                    }
                    current = child;
                }
            }
        }
        return root;
        // 构建树状结构，这里省略了具体的构建过程
        // 你可以使用类似的方法构建你的树
        // 示例中只包含了根节点的构建
    }

    public static TreeNode findNode(TreeNode node, String valueName) {
        if (node.valueName.equals(valueName)) {
            return node;
        }
        for (TreeNode child : node.children) {
            TreeNode result = findNode(child, valueName);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public static List<TreeNode> findLeafNodes(TreeNode node) {
        List<TreeNode> leafNodes = new ArrayList<>();
        if (node.isLeaf()) {
            leafNodes.add(node);
        } else {
            for (TreeNode child : node.children) {
                leafNodes.addAll(findLeafNodes(child));
            }
        }
        return leafNodes;
    }
}
