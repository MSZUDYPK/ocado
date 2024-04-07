package com.ocado;

import com.ocado.basket.BasketSplitter;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BasketSplitter basketSplitter = new BasketSplitter("src/main/resources/config.json");
        List<String> items = Arrays.asList(
                        "Cocoa Butter",
                        "Tart - Raisin And Pecan",
                        "Table Cloth 54x72 White",
                        "Flower - Daisies",
                        "Fond - Chocolate",
                        "Cookies - Englishbay Wht"
        );

        var splittedBasket = basketSplitter.split(items);

        splittedBasket.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}
