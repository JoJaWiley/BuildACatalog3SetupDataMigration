package com.cakefactory.services;

import com.cakefactory.model.Item;

import java.util.List;

public interface Catalog {
    List<Item> findAllItems();
}
