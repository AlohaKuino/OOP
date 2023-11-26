package ru.nsu.shushakov.tree;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * dfs iterator.
 *
 * @param <T> unknown type.
 */
public class Squirell<T> implements Iterator<Tree<T>> {
    private final List<Tree<T>> queue;
    private final Tree<T> node;
    private final int amountOfModifies;

    /**
     * node added not to catch error.
     */
    public Squirell(Tree<T> node) {
        queue = new ArrayList<>();
        this.node = node;
        queue.add(node);
        amountOfModifies = node.getTimesModified();
    }

    /**
     * if queue is empty then tree is over.
     *
     * @return true if there is next.
     */
    @Override
    public boolean hasNext() {
        if (node.getTimesModified() != amountOfModifies) {
            throw new ConcurrentModificationException();
        }
        return !queue.isEmpty();
    }

    /**
     * bfs but without main while cycle.
     *
     * @return node if tree is not over.
     */
    @Override
    public Tree<T> next() {
        if (node.getTimesModified() != amountOfModifies) {
            throw new ConcurrentModificationException();
        }
        Tree<T> node = queue.get(queue.size() - 1);
        queue.remove(queue.size() - 1);
        queue.addAll(node.getChildren());
        return node;
    }
}
