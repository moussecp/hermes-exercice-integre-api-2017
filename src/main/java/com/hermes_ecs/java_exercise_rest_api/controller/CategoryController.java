package com.hermes_ecs.java_exercise_rest_api.controller;

import com.hermes_ecs.java_exercise_rest_api.dao.CategoryDao;
import com.hermes_ecs.java_exercise_rest_api.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Transactional(readOnly = true)
public class CategoryController {

    private CategoryDao categoryDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allProductsInJson() {
        return getAllCategoriesResponse();
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> allProductsInXml() {
        return getAllCategoriesResponse();
    }


    protected ResponseEntity<List<Category>> getAllCategoriesResponse() {
        return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
    }
}
