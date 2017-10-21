package com.hermes_ecs.java_exercise_rest_api.dao;


import com.hermes_ecs.java_exercise_rest_api.domain.Product;
import com.hermes_ecs.java_exercise_rest_api.domain.constant.Department;

import java.util.List;

public interface ProductDao extends Dao<Long, Product> {
    boolean existsWithSameLabel(String label);

    List<Product> getProductsWithCategoryName(String categoryName);

    List<Product> getProductsWithCategoryDepartment(Department department);

    boolean existsDifferentEntityWithSameLabel(Product product, String label);
}
