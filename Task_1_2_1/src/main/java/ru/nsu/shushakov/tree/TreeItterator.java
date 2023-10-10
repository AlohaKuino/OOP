package ru.nsu.shushakov.tree;

import java.util.ArrayList;
import java.util.Iterator;

public class TreeItterator <T> implements Iterator <T> {
        private int currentIndex;
        private final ArrayList<Tree<T>> list;

        public TreeItterator(ArrayList<Tree<T>> list) {
            this.list = list;
            this.currentIndex = 0;
        }

        @Override
        public boolean hasNext() {
            while (currentIndex < list.size()) {
                Tree <T> currentNode = list.get(currentIndex);
                if (isMovieEligible(currentMovie)) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public Movie next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return list.get(currentIndex++);
        }
    }
}
