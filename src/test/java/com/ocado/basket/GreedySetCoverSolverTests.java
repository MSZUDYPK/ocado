package com.ocado.basket;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class GreedySetCoverSolverTests {

    @Test
    public void solve_returnsEmptyMap_whenUniverseAndSetsAreEmpty() {
        GreedySetCoverSolver<String, Integer> solver = new GreedySetCoverSolver<>();
        Collection<Integer> universe = new ArrayList<>();
        Map<String, Collection<Integer>> sets = new HashMap<>();

        Map<String, Collection<Integer>> result = solver.solve(universe, sets);

        assertTrue(result.isEmpty());
    }

    @Test
    public void solve_returnsCorrectCover_whenUniverseIsSubsetOfOneSet() {
        GreedySetCoverSolver<String, Integer> solver = new GreedySetCoverSolver<>();
        Collection<Integer> universe = List.of(1, 2, 3);
        Map<String, Collection<Integer>> sets = new HashMap<>();
        sets.put("A", List.of(1, 2, 3, 4, 5));

        Map<String, Collection<Integer>> result = solver.solve(universe, sets);

        assertEquals(1, result.size());
        assertTrue(result.containsKey("A"));
        assertEquals(List.of(1, 2, 3, 4, 5), new ArrayList<>(result.get("A")));
    }

    @Test
    public void solve_returnsCorrectCover_whenMultipleSetsAreGiven() {
        GreedySetCoverSolver<String, Integer> solver = new GreedySetCoverSolver<>();
        Collection<Integer> universe = List.of(1, 2, 3, 4, 5);
        Map<String, Collection<Integer>> sets = new HashMap<>();
        sets.put("A", List.of(1, 2, 3));
        sets.put("B", List.of(2, 3, 4));
        sets.put("C", List.of(4, 5));

        Map<String, Collection<Integer>> result = solver.solve(universe, sets);

        assertEquals(2, result.size());
        assertTrue(result.containsKey("A"));
        assertTrue(result.containsKey("C"));
    }

    @Test
    public void solve_returnsCorrectCover_whenSetsLargerThanUniverseAreGiven() {
        GreedySetCoverSolver<String, Integer> solver = new GreedySetCoverSolver<>();
        Collection<Integer> universe = List.of(1, 2, 3, 4, 5);
        Map<String, Collection<Integer>> sets = new HashMap<>();
        sets.put("A", List.of(1, 2, 3, 6, 7, 8, 9, 10));
        sets.put("B", List.of(2, 3, 4, 11, 12, 13, 14, 15));
        sets.put("C", List.of(4, 5, 1, 16, 17, 18, 19, 20));

        Map<String, Collection<Integer>> result = solver.solve(universe, sets);

        assertEquals(2, result.size());
        assertTrue(result.containsKey("A"));
        assertTrue(result.containsKey("C"));
    }
}
