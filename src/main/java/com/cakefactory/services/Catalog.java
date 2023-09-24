package com.cakefactory.services;

import com.cakefactory.domain.Item;

import java.util.List;

public interface Catalog {
    List<Item> findAllItems();
}
