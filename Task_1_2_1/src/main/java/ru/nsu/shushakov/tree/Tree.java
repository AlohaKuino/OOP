package ru.nsu.shushakov.tree;

import java.util.ArrayList;
import java.util.Iterator;

public class Tree<T> {
    public Tree father;
    public T value;
    public ArrayList<Tree<T>> children;

    public Tree(T val) {
        this.value = val;
        this.children = new ArrayList<>();
    }
    public Tree <T> addChild(T kid) {
        this.children.add(new Tree<T>(kid));
        return this;
    }
    public Tree <T> addChild(Tree<T> kid) {
        this.children.add(kid);
        return this;
    }
    public void remove() {
        this.children.clear();
        this.value = null;
    }
}



