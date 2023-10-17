package ru.nsu.shushakov.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Tree<T> implements Iterable<T>{
    private T value;
    private Tree father;
    private ArrayList<Tree<T>> children;
    private ArrayList<Integer> hash;

    private int timesModified;
    private whatAlgorythm algorythm;

    public int getTimesModified(){ return timesModified; }
    public void setTimesModified(){ this.timesModified ++; }

    public T getValue() {
        return value;
    }

    public ArrayList<Tree<T>> getChildren() {
        return children;
    }

    public Tree getFather() {
        return father;
    }

    public void setAlgorythm(whatAlgorythm algorythm){ this.algorythm = algorythm; }

    public Tree(T val) {
        this.value = val;
        this.children = new ArrayList<>();
        this.hash = new ArrayList<>();
    }

    public Tree<T> addChild(T kid) {
        var node = new Tree<T>(kid);
        node.father = this;
        this.children.add(node);
        this.setTimesModified();
        node.father.hash = thingForEquals(node.father);
        return node;
    }

    public Tree<T> addChild(Tree<T> kid) {
        this.children.add(kid);
        kid.father = this;
        this.setTimesModified();
        kid.father.hash = thingForEquals(kid.father);
        return kid;
    }

    public void childFree() {
        this.value = null;
        this.father.children.addAll(this.children);
        this.father.children.remove(this);
        this.setTimesModified();
        this.father.hash = thingForEquals(this.father);
        this.father = null;
    }

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
        return false;
    }
    public Iterator<T> iterator() {
        if (algorythm == whatAlgorythm.BFS) {
            return new Caterpillar<Tree<T>>(this);
        } else {
            return new Squirell<>(this);
        }
    }

    public ArrayList<Integer> thingForEquals(Tree<T> node){
        node.hash.add(node.value.hashCode());
        for(Tree<T> i : node.children){
            node.hash.add(i.value.hashCode());
        }
        return node.hash;
    }

    public enum whatAlgorythm{
        BFS,
        DFS
    }
}