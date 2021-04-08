package com.qcz.qmplatform.common.utils;

import com.qcz.qmplatform.common.bean.Tree;

import java.util.ArrayList;
import java.util.List;

public class TreeUtils {

    public static <T extends Tree> List<T> buildTree(List<T> trees) {
        // 先找出所有的顶级节点
        List<T> topNodes = new ArrayList<>();
        for (T tree : trees) {
            String parentId = tree.getParentId();
            if (StringUtils.isBlank(parentId)) {
                topNodes.add(tree);
            }
        }

        // 递归构建树形数据
        return buildTreeData(topNodes, trees);
    }

    private static <T extends Tree> List<T> buildTreeData(List<T> parentNodes, List<T> trees) {
        for (T parentNode : parentNodes) {
            String parentId = parentNode.getId();
            List<T> childes = new ArrayList<>();
            for (T tree : trees) {
                if (parentId.equals(tree.getParentId())) {
                    tree.setHasParent(true);
                    childes.add(tree);
                }
            }
            // 如果有子节点，执行递归查询
            if (childes.size() > 0) {
                parentNode.setHasChild(true);
                parentNode.setChildes(buildTreeData(childes, trees));
            } else {
                parentNode.setChildes(null);
            }
        }
        return parentNodes;
    }

}
