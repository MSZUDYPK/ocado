package com.ocado.basket;

import java.util.Map;

interface IConfigFileLoader<K, V> {
    /**
     * Loads a config file from the given path.
     *
     * @param absolutePathToConfigFile the absolute path to the file to be loaded
     * @return a map representing the loaded config file
     */
    Map<K, V> load(String absolutePathToConfigFile);
}
