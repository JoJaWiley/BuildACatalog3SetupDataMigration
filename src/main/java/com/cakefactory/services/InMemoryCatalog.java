package com.cakefactory.services;

import com.cakefactory.model.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class InMemoryCatalog implements Catalog {

    @Override
    public List<Item> findAllItems() {
        Item item1 = new Item("abcr", "All Butter Croissant", 0.75);
        Item item2 = new Item("ccr", "Chocolate Croissant", 0.95);
        Item item3 = new Item("b", "Fresh Baguette", 1.60);
        Item item4 = new Item("rv", "Red Velvet", 3.95);
        Item item5 = new Item("vs", "Victoria Sponge", 5.45);
        Item item6 = new Item("cc", "Carrot Cake", 3.45);

        List<Item> items = new ArrayList<>();

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);

        return items;
    }
}
