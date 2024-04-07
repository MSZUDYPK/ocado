# Ocado Recruitment Task - Basket Splitter
The Basket Splitter is a Java-based library that categorizes items in a customer's basket into distinct delivery 
sets. The aim is to reduce the number of delivery sets and increase the quantity of items within each set.
## Requirements
- Java 21
- Maven

## Installation
 Clone the repository
```bash
git clone https://github.com/MSZUDYPK/ocado.git
``` 
Change directory to the project
```bash
cd ocado
```
To compile the application, run unit tests and create a jar file and add to local repository, use the following command:
```bash
mvn clean install
```

or to skip the tests, use the following command:
```bash
mvn clean install -DskipTests
```

## Usage
This library provides a `BasketSplitter` class that can be used to split a list of items into different delivery methods.

Here is a basic example of how to use the `BasketSplitter`:

```java
// Import the necessary classes
import com.ocado.basket.BasketSplitter;

// Create a new BasketSplitter based on the configuration file
BasketSplitter basketSplitter = new BasketSplitter("path/to/your/config.json");
// Define a list of items
List<String> items = Arrays.asList(
        "Cocoa Butter", 
        "Tart - Raisin And Pecan", 
        "Table Cloth 54x72 White", 
        "Flower - Daisies", 
        "Fond - Chocolate", 
        "Cookies - Englishbay Wht"
);       

// Split the items in the basket
var splittedBasket = basketSplitter.split(items);

// Print the splitted basket
splittedBasket.forEach((key, value) -> {
    System.out.println(key + ": " + value);
});
```

## Running Unit Tests
To run the unit tests, use the following command:
```bash
mvn test
```

