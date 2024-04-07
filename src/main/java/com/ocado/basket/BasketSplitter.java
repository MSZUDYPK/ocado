package com.ocado.basket;

import java.util.List;
import java.util.Map;
import java.util.*;
import java.util.stream.Collectors;

public class BasketSplitter {
    private final ISetCoverSolver<String, String> basketCoverSolver;
    private final IGrouper<String, String> deliveryGrouper;
    private final IDuplicatesRemover<String, String> duplicatedProductsRemover;

    public BasketSplitter(String absolutePathToConfigFile) {
        IConfigFileLoader<String, Set<String>> productsLoader = new JSONProductsLoader<>();
        var availableProducts = productsLoader.load(absolutePathToConfigFile);
        this.deliveryGrouper = new DeliveryGrouper<>(availableProducts);
        this.basketCoverSolver = new GreedySetCoverSolver<>();
        this.duplicatedProductsRemover = new DuplicatedProductsRemover<>();
    }

    public Map<String, List<String>> split(List<String> items) {
        if (items.isEmpty()) {
            return new HashMap<>();
        }

        var deliveryGroups = this.deliveryGrouper.group(items);

        if (new HashSet<>(items).size() == 1) {
            return convertMap(deliveryGroups);
        }

        var coverSets = this.basketCoverSolver.solve(items, deliveryGroups);
        var result = this.duplicatedProductsRemover.remove(coverSets);

        return convertMap(result);
    }

    private Map<String, List<String>> convertMap(Map<String, Collection<String>> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> new ArrayList<>(e.getValue())));
    }
}
