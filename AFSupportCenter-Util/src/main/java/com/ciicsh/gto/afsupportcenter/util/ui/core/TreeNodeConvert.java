package com.ciicsh.gto.afsupportcenter.util.ui.core;

/**
 * Tree Node Convert
 */
@FunctionalInterface
public interface TreeNodeConvert<T> {
    TreeNode handle(T t);
}
