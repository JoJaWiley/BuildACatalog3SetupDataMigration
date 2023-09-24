package com.cakefactory;

import com.cakefactory.entity.Item;
import com.cakefactory.repository.CatalogRepository;
import com.cakefactory.services.DBCatalog;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CatalogServiceTest {

    @Mock
    private CatalogRepository repository;

    @InjectMocks
    private DBCatalog catalog;

    @Test
    public void catalogConvertsModelsTest() {
        Item item1 = new Item("a", "test item 1", 2.99);
        Item item2 = new Item("b", "test item 2", 2.99);

        given(repository.findAll()).willReturn(List.of(item1, item2));

        List<com.cakefactory.domain.Item> items = catalog.findAllItems();

        assertEquals(items.get(0).getName(), item1.getName());
        assertEquals(items.get(0).getPrice(), item1.getPrice());

        assertEquals(items.get(1).getName(), item2.getName());
        assertEquals(items.get(1).getPrice(), item2.getPrice());
    }
}
