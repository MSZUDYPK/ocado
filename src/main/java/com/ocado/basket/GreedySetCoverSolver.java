package com.ocado.basket;

import java.util.*;

class GreedySetCoverSolver<K, V> implements ISetCoverSolver<K, V> {
    @Override
    public Map<K, Collection<V>> solve(Collection<V> universe, Map<K, Collection<V>> sets) {
        if (universe.isEmpty() || sets.isEmpty()) {
            return new HashMap<>();
        }

        Map<K, Collection<V>> cover = new HashMap<>();
        Collection<V> remainingElements = new ArrayList<>(universe);

        while (!remainingElements.isEmpty()) {
            K maxSetKey = null;
            Collection<V> maxSetValue = null;
            int maxCover = 0;

            for (Map.Entry<K, Collection<V>> set : sets.entrySet()) {
                Collection<V> copy = new ArrayList<>(set.getValue());
                copy.retainAll(remainingElements);

                if (copy.size() > maxCover) {
                    maxCover = copy.size();
                    maxSetKey = set.getKey();
                    maxSetValue = set.getValue();
                }
            }

            if (maxSetValue != null) {
                remainingElements.removeAll(maxSetValue);
                cover.put(maxSetKey, maxSetValue);
            }
        }
        return cover;
    }
}
