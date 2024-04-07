package com.ocado.basket;

import java.util.Collection;
import java.util.Map;


interface IGrouper<K, V> {
    /**
     * Groups the given collection of items by some criteria.
     *
     * @param items the collection of items to be grouped
     * @return a map where the keys are group identifiers and the values are collection of items belonging to each group
     */
    Map<V, Collection<K>> group(Collection<K> items);
}
