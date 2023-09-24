package com.cakefactory;

import com.cakefactory.repository.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class JPAUnitTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CatalogRepository repository;
}
