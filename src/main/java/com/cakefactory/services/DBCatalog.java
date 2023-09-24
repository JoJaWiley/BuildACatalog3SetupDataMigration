package com.cakefactory.services;

import com.cakefactory.entity.Item;
import com.cakefactory.repository.CatalogRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Primary
public class DBCatalog {

    private final CatalogRepository repository;

    public DBCatalog(CatalogRepository repository) {
        this.repository = repository;
    }

    private Iterable<Item> getAllEntityItems() {
        return repository.findAll();
    }

    public List<com.cakefactory.domain.Item> findAllItems() {
        Iterable<Item> items = this.getAllEntityItems();
        List<com.cakefactory.domain.Item> domainItems = new ArrayList<>();

        for(Item item : items) {
            com.cakefactory.domain.Item domainItem =
                    new com.cakefactory.domain.Item(item.getId(), item.getName(), item.getPrice());

            domainItems.add(domainItem);
        }

        return domainItems;
    }
}
