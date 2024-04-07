package com.ocado.basket;

import java.util.*;

interface ISetCoverSolver<K, V> {
    /**
     * Solves the Set Cover problem.
     * More details about the problem can be found at:
     * <a href="https://en.wikipedia.org/wiki/Set_cover_problem">Set Cover Problem</a>
     *
     * @param universe collection representing set that contains all elements that we want to cover.
     * @param sets a map where the keys are the names of the sets and the values are the collection representing sets themselves.
     * @return A map where the keys are the names of the selected sets and the values are the selected sets themselves.
     */
    Map<K, Collection<V>> solve(Collection<V> universe, Map<K, Collection<V>> sets);
}
