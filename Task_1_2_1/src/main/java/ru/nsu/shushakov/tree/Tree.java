package ru.nsu.shushakov.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * main class.
 *
 * @param <T> unknown datatype.
 */
public class Tree<T> {
    private Tree father;
    private T value;
    private List<Tree<T>> children;

    /**
     * simple getter.
     *
     * @return value of vertice.
     */
    public T getValue() {
        return value;
    }

    /**
     * simple setter.
     *
     * @param value of vertice.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * simple getter.
     *
     * @return children of vertice.
     */
    public List<Tree<T>> getChildren() {
        return children;
    }

    /**
     * simple getter.
     *
     * @return father of vertice.
     */
    public Tree getFather() {
        return father;
    }

    /**
     * constructor.
     *
     * @param val value of vertice.
     */
    public Tree(T val) {
        this.value = val;
        this.children = new ArrayList<>();
    }

    /**
     * add child of type T.
     *
     * @param kid of type T.
     * @return added child.
     */
    public Tree<T> addChild(T kid) {
        this.children.add(new Tree<T>(kid));
        this.children.get(this.children.size() - 1).father = this;
        return this.children.get(this.children.size() - 1);
    }

    /**
     * add child of type Tree\smth\.
     *
     * @param kid subtree.
     * @return added kid.
     */
    public Tree<T> addChild(Tree<T> kid) {
        this.children.add(kid);
        kid.father = this;
        return kid;
    }

    /**
     * remove child and add its children to uts father.
     */
    public void childFree() {
        this.value = null;
        this.father.children.addAll(this.children);
        this.father.children.remove(this);
        this.father = null;
    }

    public Iterable<Tree<T>> bfs() {
        return new SquirellCrawls<T>(this);
    }

    public Iterable<Tree<T>> dfs() {
        return new SquirellCrawls<T>(this);
    }

    /**
     * modified equals for trees.
     *
     * @param obj thing to compare.
     * @return boolean if trees are equal.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != this.getClass()) {
            return false;
        }
        Tree tree = (Tree<T>) obj;
        return this.value == tree.value && this.children.equals(tree.children);
    }

    /**
     * intresting hashcode for vertice.
     *
     * @return unic hash for vertice.
     */
    @Override
    public int hashCode() {
        var hashForNode = new int[]{value.hashCode(), children.hashCode()};
        return Arrays.hashCode(hashForNode);
    }
}