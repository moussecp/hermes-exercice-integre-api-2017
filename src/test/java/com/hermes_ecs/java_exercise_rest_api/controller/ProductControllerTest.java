package com.hermes_ecs.java_exercise_rest_api.controller;

import com.hermes_ecs.java_exercise_rest_api.dao.ProductDao;
import com.hermes_ecs.java_exercise_rest_api.domain.Product;
import com.hermes_ecs.java_exercise_rest_api.domain.ProductMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    public static final String APPLICATION_XML_UTF_8 = "application/xml;charset=UTF-8";
    @Mock
    private ProductDao productDao;
    private List<Product> products = new ArrayList<>();
    private ProductController controller;

    @Before
    public void setUp() throws Exception {
        controller = new ProductController(productDao);
        products = ProductMother.getSimpleProducts(5);
        when(productDao.findAll()).thenReturn(products);
    }

    @Test
    public void getAllProductsResponseTest() {
        ResponseEntity<?> responseEntity = controller.getAllProductsResponse();
        assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat((responseEntity.getBody()), is(equalTo(products)));
    }

    @Test
    public void getSingleProductResponseWithNoResultTest() {
        ResponseEntity<?> responseEntity = controller.getSingleProductResponse(1L);
        assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND)));
        assertThat((RequestError) responseEntity.getBody(), is(isA(RequestError.class)));
        assertThat(((RequestError) responseEntity.getBody()).getMessage(), is(equalTo(ProductController.PRODUCT_NOT_FOUND_FOR_ID_ERROR + "1")));
    }

    @Test
    public void allProductsInJsonTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/products").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        ;
    }

    @Test
    public void singleProductInJsonTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/products/1").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        ;
    }


    @Test
    public void allProductsInJsonForCategoryTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/products/category/" + ProductMother.CATEGORY_NAME).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        ;

        standaloneSetup(controller).build().perform(get("/products/category/" + "dsfdsf").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        ;
    }

    @Test
    public void allProductsInJsonForDepartmentTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/products/department/" + ProductMother.DEPARTMENT).accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        ;

        standaloneSetup(controller).build().perform(get("/products/department/" + "dsfdsf").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        ;
    }

    @Test
    public void allProductsInXmlTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/products").accept(APPLICATION_XML_UTF_8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_XML_UTF_8))
        ;
    }

    @Test
    public void singleProductInXmlTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/products/1").accept(APPLICATION_XML_UTF_8))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_XML_UTF_8))
        ;
    }

    @Test
    public void allProductsInXmlForCategoryTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/products/category/" + ProductMother.CATEGORY_NAME).accept(APPLICATION_XML_UTF_8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_XML_UTF_8))
        ;

        standaloneSetup(controller).build().perform(get("/products/category/" + "dsfdsf").accept(APPLICATION_XML_UTF_8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_XML_UTF_8))
        ;
    }

    @Test
    public void allProductsInXmlForDepartmentTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/products/department/" + ProductMother.DEPARTMENT).accept(APPLICATION_XML_UTF_8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_XML_UTF_8))
        ;

        standaloneSetup(controller).build().perform(get("/products/department/" + "dsfdsf").accept(APPLICATION_XML_UTF_8))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_XML_UTF_8))
        ;
    }
}