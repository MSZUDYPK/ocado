package com.ocado.basket;

import java.util.*;

record DeliveryGrouper<K, V>(Map<K, Set<V>> availableProducts) implements IGrouper<K, V> {
    @Override
    public Map<V, Collection<K>> group(Collection<K> items) {
        if (items.isEmpty() || this.availableProducts.isEmpty()) {
            return new HashMap<>();
        }

        Map<V, Collection<K>> deliveryGroups = new HashMap<>();
        for (var item : items) {
            var productDeliveryOptions = this.availableProducts.get(item);
            for (var option : productDeliveryOptions) {
                deliveryGroups.computeIfAbsent(option, k -> new ArrayList<>()).add(item);
            }
        }

        return deliveryGroups;
    }
}
