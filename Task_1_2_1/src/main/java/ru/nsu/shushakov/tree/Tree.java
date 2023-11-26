package ru.nsu.shushakov.tree;

import static java.util.Collections.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * main class.
 *
 * @param <T> unknown type.
 */
public class Tree<T> implements Iterable<Tree<T>> {
    private T value;
    private Tree<T> father;
    private int timesModified;
    private List<Integer> hash;
    private WhatAlgorythm algorythm;
    private final ArrayList<Tree<T>> children;

    /**
     * constructor.
     *
     * @param val value of node.
     */
    public Tree(T val) {
        this.setValue(val);
        this.children = new ArrayList<>();
        this.timesModified = 0;
        this.hash = new ArrayList<>();
        this.algorythm = WhatAlgorythm.BFS;
    }

    /**
     * simple getter.
     *
     * @return how many times this has been modified.
     */
    public int getTimesModified() {
        return this.timesModified;
    }

    /**
     * setter which adds 1 when this changed.
     */
    public void setTimesModified() {
        this.timesModified++;
    }

    /**
     * gives value of the node.
     *
     * @return value of node.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * setter.
     *
     * @param value which needs to be set in node.
     */
    public void setValue(T value) {
        setTimesModified();
        this.value = value;
    }

    /**
     * getter.
     *
     * @return list of children.
     */
    public List<Tree<T>> getChildren() {
        return this.children;
    }

    /**
     * getter.
     *
     * @return father of the node.
     */
    public Tree<T> getFather() {
        return this.father;
    }

    /**
     * setter.
     *
     * @param father father which needs to be set for the node.
     */
    public void setFather(Tree<T> father) {
        this.father = father;
    }

    /**
     * setter.
     *
     * @param algorythm enum sets bfs or dfs.
     */
    public void setAlgorythm(WhatAlgorythm algorythm) {
        this.algorythm = algorythm;
    }

    /**
     * addchild for child of type T.
     *
     * @param kid of type T which needs to be added.
     * @return Tree T node.
     */
    public Tree<T> addChild(T kid) {
        this.setTimesModified();
        Tree<T> node = new Tree<>(kid);
        node.setFather(this);
        this.children.add(node);
        return node;
    }

    /**
     * addchild for child of type Tree T.
     *
     * @param kid of type Tree T.
     * @return Tree T node.
     */
    public Tree<T> addChild(Tree<T> kid) {
        this.setTimesModified();
        this.children.add(kid);
        kid.setFather(this);
        return kid;
    }

    /**
     * aka remove, i need throughchildren for remove by index, idk why, but its not working.
     */
    public void childFree() {
        this.setTimesModified();
        for (Tree<T> i : this.children) {
            i.setFather(this.getFather());
            this.getFather().addChild(i);
        }
        if (this.father != null) {
            int i = this.throughChildren(this.value);
            this.father.children.remove(i);
            this.father = null;
        }
        this.hash.clear();
        this.children.clear();
    }

    /**
     * finds index of element in list.
     *
     * @param value need to be found.
     * @return index of element.
     */
    public int throughChildren(T value) {
        int c = 0;
        for (Tree<T> i : this.getFather().getChildren()) {
            c++;
            if (i.getValue().hashCode() == value.hashCode()) {
                return c - 1;
            }
        }
        return 0;
    }

    /**
     * overrided equals.
     *
     * @param obj with which we need to compare smth.
     * @return equals or not.
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        var obj2 = (Tree<T>) obj;
        if (this.value != obj2.value) {
            return false;
        } else {
            return this.children.equals(obj2.children);
        }
    }

    /**
     * switcher between algorythms.
     *
     * @return custom iterator depends on enum.
     */
    public Iterator<Tree<T>> iterator() {
        if (algorythm == WhatAlgorythm.BFS) {
            return new Caterpillar<>(this);
        } else {
            return new Squirell<>(this);
        }
    }

    /**
     * enum for algorythm.
     */
    public enum WhatAlgorythm {
        BFS,
        DFS
    }
}
