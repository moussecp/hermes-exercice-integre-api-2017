package com.hermes_ecs.java_exercise_rest_api.dao;

import com.hermes_ecs.java_exercise_rest_api.domain.Product;
import com.hermes_ecs.java_exercise_rest_api.domain.ProductMother;
import com.hermes_ecs.java_exercise_rest_api.domain.RepublicDactaryAmount;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

public class ProductJpaDaoTest extends AbstractJpaDaoTest<Long, Product> {

    @Autowired
    private ProductDao dao;

    @Override
    protected Product buildItem(int i) {
        final RepublicDactaryAmount price = new RepublicDactaryAmount("123");
        return new Product("Landspeeder" + i, null, "Incredibly fast kart", price);
    }

    @Override
    protected ProductDao getDao() {
        return dao;
    }

    @Test
    public void existsWithSameLabel() {
        Product product = ProductMother.getSimpleProduct();
        dao.persist(product);
        assertThat(dao.existsWithSameLabel(product.getLabel()), is(true));
    }

    @Test
    public void getProductsWithCategoryName() {
        Product product = setUpProducts();
        assertThat(dao.getProductsWithCategoryName(ProductMother.CATEGORY_NAME), is(hasItem(product)));
        assertThat(dao.getProductsWithCategoryName("dummy"), is(empty()));
    }

    @Test
    public void getProductsWithCategoryDepartment() {
        Product product = setUpProducts();
        assertThat(dao.getProductsWithCategoryDepartment(ProductMother.DEPARTMENT), is(hasItem(product)));
        assertThat(dao.getProductsWithCategoryName("dummy"), is(empty()));
    }

    private Product setUpProducts() {
        Product product = ProductMother.getSimpleProduct();
        Product product2 = ProductMother.getSimpleProduct();
        Product product3 = ProductMother.getSimpleProduct();
        dao.persist(product);
        dao.persist(product2);
        dao.persist(product3);
        return product;
    }


}