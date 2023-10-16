package ru.nsu.shushakov.tree;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * bfs iterator.
 *
 * @param <T> type of nodes in tree.
 */
public class CaterpillarCrawls<T> implements Iterable<Tree<T>> {
    /**
     * constructor.
     *
     * @param node vertice of tree.
     */
    public CaterpillarCrawls(Tree<T> node) {
        this.node = node;
    }

    public Tree<T> node;

    /**
     * implements iterator in subclass.
     */
    private class Caterpillar implements Iterator<Tree<T>> {
        public ArrayList<Tree<T>> queue;

        /**
         * node added not to catch error.
         */
        public Caterpillar() {
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
         * bfs but without main while cycle.
         *
         * @return node if tree is not over.
         */
        @Override
        public Tree<T> next() {
            Tree<T> node = queue.get(0);
            queue.remove(0);
            if (node.getValue() == null) {
                throw new ConcurrentModificationException();
            }
            queue.addAll(node.getChildren());
            return node;
        }
    }

    @Override
    public Iterator<Tree<T>> iterator() {
        return new Caterpillar();
    }
}