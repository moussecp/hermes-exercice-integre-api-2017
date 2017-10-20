package com.hermes_ecs.java_exercise_rest_api.dao;

import com.hermes_ecs.java_exercise_rest_api.domain.Category;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryJpaDao extends AbstractJpaDao<Long, Category> implements CategoryDao {

    CategoryJpaDao() {
        super(Category.class);
    }

}
