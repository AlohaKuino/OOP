package ru.nsu.shushakov.tree;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * dfs iterator.
 *
 * @param <T> type of nodes in tree.
 */
public class SquirellCrawls<T> implements Iterable<Tree<T>> {
    /**
     * constructor.
     *
     * @param node vertice of tree.
     */
    public SquirellCrawls(Tree<T> node) {
        this.node = node;
    }

    public Tree<T> node;

    /**
     * implements iterator in subclass.
     */
    private class Squirell implements Iterator<Tree<T>> {
        public ArrayList<Tree<T>> queue;

        /**
         * node added not to catch error.
         */
        public Squirell() {
            queue = new ArrayList<Tree<T>>();
            queue.add(node);
        }

        /**
         * if queue is empty then tree is over.
         *
         * @return true if there is next.
         */
        @Override
        public boolean hasNext() {
            if (node.getValue() == null) {
                throw new ConcurrentModificationException();
            }
            if (!queue.isEmpty()) {
                return true;
            }
            return false;
        }

        /**
         * dfs but without main while cycle.
         *
         * @return node if tree is not over.
         */
        @Override
        public Tree<T> next() {
            Tree<T> node = queue.get(queue.size() - 1);
            queue.remove(queue.size() - 1);
            if (node.getValue() == null) {
                throw new ConcurrentModificationException();
            }
            queue.addAll(node.getChildren());
            return node;
        }
    }

    @Override
    public Iterator<Tree<T>> iterator() {
        return new Squirell();
    }
}