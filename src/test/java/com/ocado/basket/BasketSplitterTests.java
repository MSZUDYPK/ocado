package com.ocado.basket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class BasketSplitterTests {
    private BasketSplitter splitter;

    @BeforeEach
    public void setup() {
        splitter = new BasketSplitter("src/main/resources/config.json");
    }

    @Test
    public void should_return_true_given_basket_1() {
        // Arrange
        var items = Arrays.asList(
                "Cocoa Butter",
                "Tart - Raisin And Pecan",
                "Table Cloth 54x72 White",
                "Flower - Daisies",
                "Cookies - Englishbay Wht",
                "Fond - Chocolate"
                );

        // Act
        var result = splitter.split(items);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.containsKey("Courier"));
        assertTrue(result.containsKey("Pick-up point"));
        assertEquals(5, result.get("Courier").size());
        assertEquals(1, result.get("Pick-up point").size());
        assertTrue(result.get("Courier").containsAll(Arrays.asList(
                "Cocoa Butter",
                "Tart - Raisin And Pecan",
                "Table Cloth 54x72 White",
                "Flower - Daisies",
                "Cookies - Englishbay Wht"
        )));

        assertTrue(result.get("Pick-up point").contains("Fond - Chocolate"));
    }

    @Test
    public void should_return_true_given_basket_2() {
        // Arrange
        var items = Arrays.asList(
                "Fond - Chocolate",
                "Chocolate - Unsweetened",
                "Nut - Almond, Blanched, Whole",
                "Haggis",
                "Mushroom - Porcini Frozen",
                "Cake - Miini Cheesecake Cherry",
                "Sauce - Mint",
                "Longan",
                "Bag Clear 10 Lb",
                "Nantucket - Pomegranate Pear",
                "Puree - Strawberry",
                "Numi - Assorted Teas",
                "Apples - Spartan",
                "Garlic - Peeled",
                "Cabbage - Nappa",
                "Bagel - Whole White Sesame",
                "Tea - Apple Green Tea"
        );

        // Act
        var result = splitter.split(items);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsKey("Courier"));
        assertTrue(result.containsKey("Express Collection"));
        assertTrue(result.containsKey("Same day delivery"));
        assertEquals(1, result.get("Courier").size());
        assertEquals(13, result.get("Express Collection").size());
        assertEquals(3, result.get("Same day delivery").size());

        assertTrue(result.get("Courier").containsAll(Arrays.asList(
                "Cake - Miini Cheesecake Cherry"
        )));


        assertTrue(result.get("Same day delivery").containsAll(Arrays.asList(
                "Sauce - Mint",
                "Garlic - Peeled",
                "Numi - Assorted Teas"
        )));

        assertTrue(result.get("Express Collection").containsAll(Arrays.asList(
                "Fond - Chocolate",
                "Chocolate - Unsweetened",
                "Nut - Almond, Blanched, Whole",
                "Haggis",
                "Mushroom - Porcini Frozen",
                "Puree - Strawberry",
                "Apples - Spartan",
                "Cabbage - Nappa",
                "Bagel - Whole White Sesame",
                "Tea - Apple Green Tea",
                "Longan",
                "Bag Clear 10 Lb",
                "Nantucket - Pomegranate Pear"
        )));

    }

    @Test
    public void should_return_empty_Map_given_empty_items()  {
        List<String> items = Collections.emptyList();
        Map<String, List<String>> result = splitter.split(items);
        assertTrue(result.isEmpty());
    }

    @Test
    public void should_return_NullPointerException_give_null() {
        assertThrows(NullPointerException.class, () -> splitter.split(null));
    }

    @Test
    public void should_return_correct_result_given_single_item() {
        var items = Collections.singletonList("Cocoa Butter");
        var result = splitter.split(items);

        assertNotNull(result);
        assertEquals(6, result.size());
        assertTrue(result.get("Next day shipping").contains("Cocoa Butter"));
        assertTrue(result.get("Mailbox delivery").contains("Cocoa Butter"));
        assertTrue(result.get("In-store pick-up").contains("Cocoa Butter"));
        assertTrue(result.get("Parcel locker").contains("Cocoa Butter"));
        assertTrue(result.get("Courier").contains("Cocoa Butter"));
        assertTrue(result.get("Same day delivery").contains("Cocoa Butter"));
    }

    @Test
    public void should_return_correct_result_given_multiple_identical_items() {
        var items = Arrays.asList("Cocoa Butter", "Cocoa Butter", "Cocoa Butter");
        var result = splitter.split(items);

        assertNotNull(result);
        assertEquals(6, result.size());
        assertEquals(result.get("Next day shipping").size(), 3);
        assertEquals(result.get("Mailbox delivery").size(), 3);
        assertEquals(result.get("In-store pick-up").size(), 3);
        assertEquals(result.get("Parcel locker").size(), 3);
        assertEquals(result.get("Courier").size(), 3);
        assertEquals(result.get("Same day delivery").size(), 3);

        assertEquals(result.get("Next day shipping"), List.of("Cocoa Butter", "Cocoa Butter", "Cocoa Butter"));
        assertEquals(result.get("Mailbox delivery"), List.of("Cocoa Butter", "Cocoa Butter", "Cocoa Butter"));
        assertEquals(result.get("In-store pick-up"), List.of("Cocoa Butter", "Cocoa Butter", "Cocoa Butter"));
        assertEquals(result.get("Parcel locker"), List.of("Cocoa Butter", "Cocoa Butter", "Cocoa Butter"));
        assertEquals(result.get("Courier"), List.of("Cocoa Butter", "Cocoa Butter", "Cocoa Butter"));
        assertEquals(result.get("Same day delivery"), List.of("Cocoa Butter", "Cocoa Butter", "Cocoa Butter"));
    }
}
