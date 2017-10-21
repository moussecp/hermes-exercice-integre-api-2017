package com.hermes_ecs.java_exercise_rest_api.controller;

import com.hermes_ecs.java_exercise_rest_api.dao.CategoryDao;
import com.hermes_ecs.java_exercise_rest_api.domain.Category;
import com.hermes_ecs.java_exercise_rest_api.domain.constant.Department;
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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {
    public static final String APPLICATION_XML_UTF_8 = "application/xml;charset=UTF-8";
    @Mock
    private CategoryDao categoryDao;
    private List<Category> categories = new ArrayList<>();
    private CategoryController controller;

    @Before
    public void setUp() throws Exception {
        controller = new CategoryController(categoryDao);
        categories.add(new Category("category1", Department.ACCESSORIES));
        categories.add(new Category("category2", Department.ELECTRONIC));
        categories.add(new Category("category3", Department.MECHANIC));
        when(categoryDao.findAll()).thenReturn(categories);
    }

    @Test
    public void getAllProductsResponseTest() {
        ResponseEntity<?> responseEntity = controller.getAllCategoriesResponse();
        assertThat(responseEntity.getStatusCode(), is(equalTo(HttpStatus.OK)));
        assertThat((responseEntity.getBody()), is(equalTo(categories)));
    }

    @Test
    public void allProductsInJsonTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/categories").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        ;
    }

    @Test
    public void allProductsInXmlTest() throws Exception {
        standaloneSetup(controller).build().perform(get("/categories").accept(APPLICATION_XML_UTF_8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_XML_UTF_8))
        ;
    }
}