package com.ocado.basket;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class DuplicatedProductsRemoverTests {

    @Test
    public void remove_shouldReturnEmptyMap_whenInputIsEmpty() {
        // Given
        DuplicatedProductsRemover<String, Integer> remover = new DuplicatedProductsRemover<>();
        Map<String, Collection<Integer>> cover = new HashMap<>();

        // When
        var result = remover.remove(cover);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    public void remove_shouldReturnSameMap_whenNoDuplicates() {
        // Given
        DuplicatedProductsRemover<String, Integer> remover = new DuplicatedProductsRemover<>();
        Map<String, Collection<Integer>> cover = new HashMap<>();
        cover.put("A", Arrays.asList(1, 2, 3));
        cover.put("B", Arrays.asList(4, 5, 6));

        // When
        var result = remover.remove(cover);

        // Then
        assertEquals(cover, result);
    }

    @Test
    public void remove_shouldRemoveDuplicates_whenDuplicatesExist() {
        // Given
        DuplicatedProductsRemover<String, Integer> remover = new DuplicatedProductsRemover<>();
        Map<String, Collection<Integer>> cover = new HashMap<>();
        cover.put("A", Arrays.asList(1, 2, 3));
        cover.put("B", Arrays.asList(2, 3, 4));
        cover.put("C", Arrays.asList(3, 4, 5));

        // When
        var result = remover.remove(cover);

        // Then
        assertEquals(3, result.size());
        assertTrue(result.containsKey("A"));
        assertTrue(result.containsKey("B"));
        assertTrue(result.containsKey("C"));
        assertEquals(result.get("A"), List.of(1, 2, 3));
        assertEquals(result.get("B"), List.of(4));
        assertEquals(result.get("C"), List.of(5));
    }

    @Test
    public void remove_shouldReturnCorrectResult_whenHasOneSet() {
        // Given
        DuplicatedProductsRemover<String, Integer> remover = new DuplicatedProductsRemover<>();
        Map<String, Collection<Integer>> cover = new HashMap<>();
        cover.put("A", Arrays.asList(1, 2, 3));

        // When
        var result = remover.remove(cover);

        // Then
        assertEquals(1, result.size());
        assertTrue(result.containsKey("A"));
        assertEquals(result.get("A"), List.of(1, 2, 3));
    }

    @Test
    public void remove_shouldReturnCorrectResult_whenCoverHasSetsWithAllDifferentElements() {
        // Given
        DuplicatedProductsRemover<String, Integer> remover = new DuplicatedProductsRemover<>();
        Map<String, Collection<Integer>> cover = new HashMap<>();
        cover.put("A", Arrays.asList(1, 2, 3));
        cover.put("B", Arrays.asList(4, 5, 6));
        cover.put("C", Arrays.asList(7, 8, 9));
        cover.put("D", Arrays.asList(10, 11, 12));

        // When
        var result = remover.remove(cover);

        // Then
        assertEquals(4, result.size());
        assertTrue(result.containsKey("A"));
        assertTrue(result.containsKey("B"));
        assertTrue(result.containsKey("C"));
        assertTrue(result.containsKey("D"));
        assertEquals(result.get("A"), List.of(1, 2, 3));
        assertEquals(result.get("B"), List.of(4, 5, 6));
        assertEquals(result.get("C"), List.of(7, 8, 9));
        assertEquals(result.get("D"), List.of(10, 11, 12));
    }
}
