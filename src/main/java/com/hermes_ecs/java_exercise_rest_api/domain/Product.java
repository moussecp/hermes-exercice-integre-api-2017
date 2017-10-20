package com.hermes_ecs.java_exercise_rest_api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;

@Entity
@Table(name = "PRODUCT")
public class Product implements Identifiable<Long> {

    @Id
    @GeneratedValue(generator = "PRODUCT_SEQ_GEN")
    @SequenceGenerator(name = "PRODUCT_SEQ_GEN", sequenceName = "PRODUCT_SEQ", allocationSize = 20)
    @JsonIgnore
    private Long id;

    @Column(nullable = false)
    private String label;

    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @AttributeOverrides(value = {@AttributeOverride(name = "value", column = @Column(name = "PRICE"))})
    private RepublicDactaryAmount price;

    Product() {
    }

    public Product(String label, Category category, RepublicDactaryAmount price) {
        checkArgument(!StringUtils.isEmpty(label), "label must not be null or empty");
        checkArgument(price != null, "price must not be null");
        this.label = label;
        this.category = category;
        this.price = price;
    }

    public Product(String label, Category category, String description, RepublicDactaryAmount price) {
        this(label, category, price);
        this.description = description;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RepublicDactaryAmount getPrice() {
        return price;
    }

    public void setPrice(RepublicDactaryAmount price) {
        this.price = price;
    }

    @Override
    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) &&
                Objects.equals(label, product.label) &&
                Objects.equals(description, product.description) &&
                Objects.equals(category, product.category) &&
                Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, description, category, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                '}';
    }
}
