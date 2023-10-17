package ru.nsu.shushakov.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static java.util.Collections.sort;

public class Tree<T> implements Iterable<Tree<T>>{
    private T value;
    private Tree<T> father;
    private ArrayList<Tree<T>> children;
    private ArrayList<Integer> hash;
    private int timesModified;
    private whatAlgorythm algorythm;

    public Tree(T val) {
        this.setValue(val);
        this.children = new ArrayList<>();
        this.timesModified = 0;
        this.hash = new ArrayList<>();
        this.algorythm = whatAlgorythm.BFS;
    }

    public int getTimesModified(){
        return this.timesModified;
    }
    public void setTimesModified(){
        this.timesModified ++;
    }
    public T getValue() {
        return this.value;
    }
    public void setValue(T value){
        setTimesModified();
        this.value = value;
    }

    public List<Tree<T>> getChildren() {
        return this.children;
    }

    public Tree<T> getFather() {
        return this.father;
    }

    public void setFather(Tree<T> father) {
        this.father = father;
    }

    public void setAlgorythm(whatAlgorythm algorythm){
        this.algorythm = algorythm;
    }

    public Tree<T> addChild(T kid) {
        this.setTimesModified();
        Tree<T> node = new Tree<>(kid);
        node.setFather(this);
        this.children.add(node);
        if(node.father != null) {
            node.father.hash = node.father.thingForEquals();
        }
        return node;
    }

    public Tree<T> addChild(Tree<T> kid) {
        this.setTimesModified();
        this.children.add(kid);
        kid.setFather(this);
        if(kid.father != null) {
            kid.father.hash = kid.father.thingForEquals();
        }
        return kid;
    }

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

    public int throughChildren(T value){
        int c = 0;
        for(Tree<T> i : this.getFather().getChildren()){
            c ++;
            if (i.getValue().hashCode() == value.hashCode()){
                return c - 1;
            }
        }
        return 0;
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
        var cmp = (Tree<T>) obj;
        return Arrays.equals(this.wonderEq().toArray(), cmp.wonderEq().toArray());
    }
    public Iterator<Tree<T>> iterator() {
        if (algorythm == whatAlgorythm.BFS) {
            return new Caterpillar<T>(this);
        } else {
            return new Squirell<T>(this);
        }
    }

    public ArrayList<Integer> thingForEquals(){
        this.hash.clear();
        this.hash.add(this.value.hashCode());
        for(Tree<T> i : this.children){
            this.hash.add(i.value.hashCode());
        }
        sort(this.hash);
        return this.hash;
    }
    public ArrayList<Integer> wonderEq(){
        for (Tree<T> i : this) {
            if(i == this){
                continue;
            }
            this.hash.addAll(i.hash);
        }
        return this.hash;
    }

    public enum whatAlgorythm{
        BFS,
        DFS
    }
}