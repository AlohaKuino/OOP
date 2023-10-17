package ru.nsu.shushakov.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.sort;

/**
 * main class.
 *
 * @param <T> unknown type.
 */
public class Tree<T> implements Iterable<Tree<T>> {
    private T value;
    private Tree<T> father;
    private int timesModified;
    private ArrayList<Integer> hash;
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
        if (node.father != null) {
            node.father.hash = node.father.thingForEquals();
        }
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
        if (kid.father != null) {
            kid.father.hash = kid.father.thingForEquals();
        }
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
        var cmp = (Tree<T>) obj;
        return Arrays.equals(this.wonderEq().toArray(), cmp.wonderEq().toArray());
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
     * makes a hash for node and children.
     *
     * @return <p>list which contains fathers hash on the first position and sorted
     * hashes of children on the other.</p>
     */
    public ArrayList<Integer> thingForEquals() {
        this.hash.clear();
        this.hash.add(this.value.hashCode());
        for (Tree<T> i : this.children) {
            this.hash.add(i.value.hashCode());
        }
        sort(this.hash);
        return this.hash;
    }

    /**
     * collects all hashes for tree.
     *
     * @return unic list of hashes.
     */
    public ArrayList<Integer> wonderEq() {
        for (Tree<T> i : this) {
            if (i == this) {
                continue;
            }
            this.hash.addAll(i.hash);
        }
        return this.hash;
    }

    /**
     * enum for algorythm.
     */
    public enum WhatAlgorythm {
        BFS,
        DFS
    }
}