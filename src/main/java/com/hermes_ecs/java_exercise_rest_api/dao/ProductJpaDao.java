package com.hermes_ecs.java_exercise_rest_api.dao;

import com.hermes_ecs.java_exercise_rest_api.domain.Product;
import com.hermes_ecs.java_exercise_rest_api.domain.constant.Department;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductJpaDao extends AbstractJpaDao<Long, Product> implements ProductDao {

    protected ProductJpaDao() {
        super(Product.class);
    }

    @Override
    public boolean existsWithSameLabel(String label) {
        if (!StringUtils.isEmpty(label)) {
            return !getProductsWithLabel(label).isEmpty();
        } else {
            return false;
        }
    }

    private List getProductsWithLabel(String label) {
        return getEntityManager().createQuery(
                "select p " +
                        "from Product p " +
                        "where p.label=:label")
                .setParameter("label", label)
                .getResultList();
    }

    @Override
    public List<Product> getProductsWithCategoryName(String categoryName) {
        return getEntityManager().createQuery(
                "select p " +
                        "from Product p " +
                        "join p.category c " +
                        "where c.name=:categoryName")
                .setParameter("categoryName", categoryName)
                .getResultList();
    }

    @Override
    public List<Product> getProductsWithCategoryDepartment(Department department) {
        return getEntityManager().createQuery(
                "select p " +
                        "from Product p " +
                        "join p.category c " +
                        "where c.department=:department")
                .setParameter("department", department)
                .getResultList();
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
