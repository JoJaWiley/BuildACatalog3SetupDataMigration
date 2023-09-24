package com.cakefactory;

import com.cakefactory.model.Item;
import com.cakefactory.services.Catalog;
import com.cakefactory.services.InMemoryCatalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CatalogTest {

    @Test
    public void catalogHappyFlow() {
        Catalog catalog = new InMemoryCatalog();

        Item item1 = new Item("abcr", "All Butter Croissant", 0.75);
        Item item2 = new Item("ccr", "Chocolate Croissant", 0.95);
        Item item3 = new Item("b", "Fresh Baguette", 1.60);
        Item item4 = new Item("rv", "Red Velvet", 3.95);
        Item item5 = new Item("vs", "Victoria Sponge", 5.45);
        Item item6 = new Item("cc", "Carrot Cake", 3.45);

        List<Item> items = catalog.findAllItems();

        assertEquals(items.get(0), item1);
        assertEquals(items.get(1), item2);
        assertEquals(items.get(2), item3);
        assertEquals(items.get(3), item4);
        assertEquals(items.get(4), item5);
        assertEquals(items.get(5), item6);
    }
}
