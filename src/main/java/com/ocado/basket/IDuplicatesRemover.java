package com.ocado.basket;

import java.util.Collection;
import java.util.Map;

interface IDuplicatesRemover<K, V> {
    /**
     * Removes duplicates from collection in the given map.
     *
     * @param map the map from which duplicates in given collections should be removed
     * @return a new map with the same keys, but with collections without duplicates
     */
    Map<K, Collection<V>> remove(Map<K, Collection<V>> map);
}
