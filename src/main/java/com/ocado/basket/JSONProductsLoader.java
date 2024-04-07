package com.ocado.basket;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

class JSONProductsLoader<K, V> implements IConfigFileLoader<K, Set<V>> {
    public Map<K, Set<V>> load(String absolutePathToConfigFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<K, Set<V>> products = new HashMap<>();
        try {
            products = objectMapper.readValue(Paths.get(absolutePathToConfigFile).toFile(), new TypeReference<>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return products;
    }
}