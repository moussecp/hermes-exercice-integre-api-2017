package com.hermes_ecs.java_exercise_rest_api.controller;

import com.hermes_ecs.java_exercise_rest_api.dao.ProductDao;
import com.hermes_ecs.java_exercise_rest_api.domain.Product;
import com.hermes_ecs.java_exercise_rest_api.domain.constant.Department;
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

    public static final String PRODUCT_NOT_FOUND_FOR_ID_ERROR = "could not find product for id: ";
    public static final String PRODUCT_NOT_FOUND_FOR_CATEGORY_ERROR = "could not find product for category: ";
    public static final String PRODUCT_NOT_FOUND_FOR_DEPARTMENT_ERROR = "could not find product for department: ";
    private ProductDao productDao;

    @Autowired
    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allProductsInJson() {
        return getAllProductsResponse();
    }

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allProductsForCategoryNameInJson(@PathVariable("category") String category) {
        return getAllProductsForCategoryNameResponse(category);
    }

    @RequestMapping(value = "/department/{department}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> allProductsForDepartmentInJson(@PathVariable("department") String department) {
        return getAllProductsForDepartmentResponse(department);
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

    @RequestMapping(value = "/category/{category}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> allProductsForCategoryNameInXml(@PathVariable("category") String category) {
        return getAllProductsForCategoryNameResponse(category);
    }

    @RequestMapping(value = "/department/{department}", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<?> allProductsForDepartmentInXml(@PathVariable("department") String department) {
        return getAllProductsForDepartmentResponse(department);
    }

    protected ResponseEntity<List<Product>> getAllProductsResponse() {
        return new ResponseEntity<>(productDao.findAll(), HttpStatus.OK);
    }

    protected ResponseEntity<?> getSingleProductResponse(Long id) {
        try {
            return new ResponseEntity<>(productDao.find(id).get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RequestError(PRODUCT_NOT_FOUND_FOR_ID_ERROR + id), HttpStatus.NOT_FOUND);
        }
    }

    protected ResponseEntity<?> getAllProductsForCategoryNameResponse(String categoryName) {
        try {
            return new ResponseEntity<>(productDao.getProductsWithCategoryName(categoryName), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RequestError(PRODUCT_NOT_FOUND_FOR_CATEGORY_ERROR + categoryName), HttpStatus.NOT_FOUND);
        }
    }

    protected ResponseEntity<?> getAllProductsForDepartmentResponse(String departmentName) {
        try {
            Department department = Department.valueOf(departmentName);
            return new ResponseEntity<>(productDao.getProductsWithCategoryDepartment(department), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new RequestError(PRODUCT_NOT_FOUND_FOR_DEPARTMENT_ERROR + departmentName), HttpStatus.NOT_FOUND);
        }
    }
}
