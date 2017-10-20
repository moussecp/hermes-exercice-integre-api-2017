package com.hermes_ecs.java_exercise_rest_api.dao;

import com.hermes_ecs.java_exercise_rest_api.domain.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

@Repository
public class ProductJpaDao extends AbstractJpaDao<Long, Product> implements ProductDao {

    ProductJpaDao() {
        super(Product.class);
    }

    @Override
    public boolean existsWithSameLabel(String label) {
        if (!StringUtils.isEmpty(label)) {
            return !getEntityManager().createQuery("select id from Product where label=:label").setParameter("label", label).getResultList().isEmpty();
        } else {
            return false;
        }
    }

    @Override
    public boolean existsDifferentEntityWithSameLabel(Product product, String label) {
        if (!StringUtils.isEmpty(label)) {
            return !getEntityManager().createQuery("select id from Product p where p.label=:label and p!=:product")
                    .setParameter("label", label)
                    .setParameter("product", product)
                    .getResultList().isEmpty();
        } else {
            return false;
        }
    }

}
