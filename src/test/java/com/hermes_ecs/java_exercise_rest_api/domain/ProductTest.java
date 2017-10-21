package com.hermes_ecs.java_exercise_rest_api.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ProductTest {

    public static final String LABEL = "Hoverboard";
    public static final String DESCRIPTION = "In 2015, soon!";
    public static final RepublicDactaryAmount PRICE = new RepublicDactaryAmount(
            "5");
    public static final int QUANTITY = 42;
    private Product firstProduct;
    private Product secondProduct;

    @Before
    public void setup() {
        firstProduct = new Product(LABEL, null, DESCRIPTION, PRICE);
        secondProduct = new Product(LABEL + "delta", null, DESCRIPTION + "delta",
                PRICE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullLabeThrowsAnException() {
        new Product(null, null, DESCRIPTION, PRICE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithEmptyLabelThrowsAnException() {
        new Product("", null, DESCRIPTION, PRICE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorWithNullPriceThrowsAnException() {
        new Product(LABEL, null, DESCRIPTION, null);
    }

    @Test
    public void constructorWithNullDescriptionDoesNotThrowAnyException() {
        new Product(LABEL, null, PRICE);
    }

    @Test
    public void constructorWithEmptyDescriptionDoesNotThrowAnyException() {
        new Product(LABEL, null, "", PRICE);
    }

    @Test
    public void equalsWithSameObjectWithoutIdReturnsTrue() {
        assertThat(firstProduct.equals(firstProduct), is(true));
    }

    @Test
    public void equalsWithDifferentObjectsWithoutIdWithSameContentReturnsTrue() {
        secondProduct.setLabel(firstProduct.getLabel());
        secondProduct.setDescription(firstProduct.getDescription());
        secondProduct.setPrice(firstProduct.getPrice());

        assertThat(firstProduct.equals(secondProduct), is(true));
    }

    @Test
    public void equalsWithDifferentObjectsWithDifferentContentButSameIdReturnsFalse() {
        firstProduct.setId(1L);
        secondProduct.setId(1L);

        assertThat(firstProduct.equals(secondProduct), is(false));
    }
}
