package com.cakefactory.repository;

import com.cakefactory.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Item, String> {

    @Query("SELECT * FROM items")
    List<Item> findAllItems();
}
