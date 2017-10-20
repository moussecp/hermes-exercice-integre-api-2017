package com.hermes_ecs.java_exercise_rest_api.controller;

import com.hermes_ecs.java_exercise_rest_api.dao.ProductDao;
import com.hermes_ecs.java_exercise_rest_api.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@Transactional(readOnly = true)
public class ProductController {

    @Autowired
    private ProductDao productDao;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allProductsInJson() {
        return getAllProductsResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> singleProductInJson(@PathVariable("id") Long id) {
        return getSingleProductResponse(id);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> allProductsInXml() {
        return getAllProductsResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> singleProductInXml(@PathVariable("id") Long id) {
        return getSingleProductResponse(id);
    }

    private ResponseEntity<List<Product>> getAllProductsResponse() {
        return new ResponseEntity<>(productDao.findAll(), HttpStatus.OK);
    }

    private ResponseEntity<?> getSingleProductResponse(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(productDao.find(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RequestError("could not found product for id: " + id), HttpStatus.NOT_FOUND);
        }
    }
}
