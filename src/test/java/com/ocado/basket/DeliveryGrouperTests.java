package com.ocado.basket;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

public class DeliveryGrouperTests {

    @Test
    public void group_shouldReturnCorrectGroups_whenMultipleItemsAreGiven() {
        // Given
        Map<String, Set<String>> availableProducts = new HashMap<>();
        availableProducts.put("Product1", Set.of("Option1", "Option2"));
        availableProducts.put("Product2", Set.of("Option2", "Option3"));
        DeliveryGrouper<String, String> grouper = new DeliveryGrouper<>(availableProducts);
        List<String> items = List.of("Product1", "Product2");

        // When
        Map<String, Collection<String>> result = grouper.group(items);

        // Then
        assertEquals(3, result.size());
        assertTrue(result.containsKey("Option1"));
        assertTrue(result.containsKey("Option2"));
        assertTrue(result.containsKey("Option3"));
        assertEquals(List.of("Product1"), result.get("Option1"));
        assertEquals(List.of("Product1", "Product2"), result.get("Option2"));
        assertEquals(List.of("Product2"), result.get("Option3"));
    }

    @Test
    public void group_shouldReturnEmptyGroups_whenItemsAreEmpty() {
        // Given
        Map<String, Set<String>> availableProducts = new HashMap<>();
        availableProducts.put("Product1", Set.of("Option1", "Option2"));
        DeliveryGrouper<String, String> grouper = new DeliveryGrouper<>(availableProducts);
        List<String> items = new ArrayList<>();

        // When
        Map<String, Collection<String>> result = grouper.group(items);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void group_shouldReturnEmptyGroups_whenNoAvailableProducts() {
        // Given
        Map<String, Set<String>> availableProducts = new HashMap<>();
        DeliveryGrouper<String, String> grouper = new DeliveryGrouper<>(availableProducts);
        List<String> items = List.of("Product1");

        // When
        Map<String, Collection<String>> result = grouper.group(items);

        // Then
        assertTrue(result.isEmpty());
    }
}
