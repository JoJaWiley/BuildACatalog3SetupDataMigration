package com.cakefactory;

import com.cakefactory.entity.Item;
import com.cakefactory.repository.CatalogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class JPAUnitTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CatalogRepository repository;

    @Test
    public void saveItemsTest() {
        Item item1 = new Item("one", "test item1", 2.99);
        entityManager.persist(item1);

        Item item2 = new Item("two", "test item2", 2.99);
        entityManager.persist(item2);
        var items = repository.findAll();

        assertThat(items).contains(item1, item2);
    }

    @Test
    void getEntitiesFromDatabaseTest() {
        var items = repository.findAll();
        assertThat(items).anyMatch(item -> "Red Velvet".equalsIgnoreCase(item.getName()));
    }
}
