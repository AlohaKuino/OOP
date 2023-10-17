package ru.nsu.shushakov.tree;

import org.junit.jupiter.api.Test;

import java.util.ConcurrentModificationException;

import static org.junit.jupiter.api.Assertions.*;


class TreeTest {

    @Test
    void getValueTest() {
        Tree<String> tree = new Tree<>("R1");
        assertEquals("R1", tree.getValue());
    }

    @Test
    void playWithTask() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        assertEquals("A", a.getValue());
        assertEquals("A", tree.getChildren().get(0).getValue());

        var b = a.addChild("B");
        assertEquals("B", b.getValue());
        assertEquals("B", a.getChildren().get(0).getValue());
        assertEquals("B", tree.getChildren().get(0).getChildren().get(0).getValue());
        assertEquals("R1", a.getFather().getValue());
        assertEquals("R1", b.getFather().getFather().getValue());
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        b.childFree();
        assertEquals(0, a.getChildren().size());
    }

    @Test
    void bfsTest() {
        Tree<String> tree = new Tree<>("R1");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        var a = tree.addChild("A");
        var b = a.addChild("B");
        b.childFree();
        tree.setAlgorythm(Tree.whatAlgorythm.BFS);
        for (String i : tree) {
            System.out.println(i);
        }
    }

    @Test
    void addChildValueTest() {
        var a = new Tree<>(2);
        assertEquals(2, a.getValue());
        a.addChild(6);
        assertEquals(6, a.getChildren().get(0).getValue());
    }

    @Test
    void addChildTreeTest() {
        var a = new Tree<>(2);
        assertEquals(2, a.getValue());
        a.addChild(new Tree<>(6));
        assertEquals(6, a.getChildren().get(0).getValue());
    }

    @Test
    void removeTest() {
        var a = new Tree<>(2);
        assertEquals(2, a.getValue());
        a.addChild(3);
        var b = a.addChild(new Tree<>(6));
        b.childFree();
        assertEquals(1, a.getChildren().size());
        assertEquals(3, a.getChildren().get(0).getValue());
    }

    @Test
    void equalTest() {
        var a = new Tree<>("A");
        var child = a.addChild("a");
        child.addChild("aa");

        var treeCopy = new Tree<>("A");
        var childCopy = treeCopy.addChild("a");
        childCopy.addChild("aa");

        assertEquals(a, treeCopy);
    }

    @Test
    void notEqualTest() {
        var a = new Tree<>("A");
        var child = a.addChild("a");
        child.addChild("ab");

        var treeCopy = new Tree<>("A");
        var childCopy = treeCopy.addChild("a");
        childCopy.addChild("aa");

        assertNotEquals(a, treeCopy);
    }

    @Test
    void dfsTest() {
        Tree<String> tree = new Tree<>("R1");
        Tree<String> subtree = new Tree<>("R2");
        subtree.addChild("C");
        subtree.addChild("D");
        tree.addChild(subtree);
        var a = tree.addChild("A");
        var b = a.addChild("B");
        b.childFree();
        tree.setAlgorythm(Tree.whatAlgorythm.DFS);
        for (String i : tree) {
            System.out.println(i);
        }
    }

    @Test
    void newParent(){
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        b.addChild("orphan");
        b.childFree();
        assertEquals("orphan", a.getChildren().get(0).getValue());
    }

//    @Test
//    void errorCatch(){
//        Tree<String> tree = new Tree<>("R1");
//        var a = tree.addChild("A");
//        var b = a.addChild("B");
//        b.addChild("orphan");
//        b.childFree();
//    }
}