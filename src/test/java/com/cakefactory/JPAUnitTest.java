package com.cakefactory;

import com.cakefactory.entity.Item;
import com.cakefactory.repository.CatalogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class JPAUnitTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CatalogRepository repository;

    @Test
    public void findAllTest() {
        Item item1 = new Item("a", "test item1", 2.99);
        entityManager.persist(item1);

        Item item2 = new Item("b", "test item2", 2.99);
        entityManager.persist(item2);

        List<Item> items = repository.findAll();

        assertThat(items).hasSize(2).contains(item1, item2);
    }
}
