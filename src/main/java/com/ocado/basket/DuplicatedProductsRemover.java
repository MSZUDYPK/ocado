package com.ocado.basket;

import java.util.*;

class DuplicatedProductsRemover<K, V> implements IDuplicatesRemover<K, V> {
    @Override
    public Map<K, Collection<V>> remove(Map<K, Collection<V>> cover) {
        Map<K, Collection<V>> result = new LinkedHashMap<>();

        for (Map.Entry<K, Collection<V>> entry : cover.entrySet()) {
            result.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }

        List<K> keys = new ArrayList<>(result.keySet());
        for (int i = keys.size() - 1; i >= 0; i--) {
            for (int j = keys.size() - 1; j >= 0; j--) {
                if (i != j && result.get(keys.get(j)).size() >= result.get(keys.get(i)).size()) {
                    result.get(keys.get(i)).removeAll(result.get(keys.get(j)));
                }
            }
        }

        return result;
    }
}
