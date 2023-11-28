package ru.nsu.shushakov.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;


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
        tree.setAlgorythm(Tree.WhatAlgorythm.BFS);
        for (Tree<String> i : tree) {
            System.out.println(i.getValue());
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
        a.addChild(6);
        var b = a.addChild(new Tree<>(3);
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
        assertFalse(a.equals(treeCopy));
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
        tree.setAlgorythm(Tree.WhatAlgorythm.DFS);
        for (Tree<String> i : tree) {
            System.out.println(i.getValue());
        }
    }

    @Test
    void newParent() {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        var b = a.addChild("B");
        b.addChild("orphan");
        b.childFree();
        assertEquals("orphan", a.getChildren().get(0).getValue());
    }

    @Test
    void equalsTestOfNotEqualTrees() {
        var a = new Tree<>("A");
        a.addChild("a");
        a.addChild("aa");

        var b = new Tree<>("A");
        var childB = b.addChild("a");
        childB.addChild("aa");

        assertFalse(a.equals(b));
    }

    @Test
    void equalsTestOfNotEqual() {
        var a = new Tree<>("A");
        var b = a.addChild("B");
        var c = a.addChild("C");
        var d = b.addChild("D");
        var e = b.addChild("E");

        var a1 = new Tree<>("A");
        var b1 = a1.addChild("B");
        var c1 = a1.addChild("C");
        var d1 = c1.addChild("D");
        var e1 = c1.addChild("E");

        assertFalse(a.equals(a1));
    }
}
