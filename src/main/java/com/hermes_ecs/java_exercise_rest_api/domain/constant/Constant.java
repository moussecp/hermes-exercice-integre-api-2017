package com.hermes_ecs.java_exercise_rest_api.domain.constant;


import com.hermes_ecs.java_exercise_rest_api.domain.Identifiable;

import javax.persistence.*;

@Entity
@Table(name = "CONSTANT")
public class Constant implements Identifiable<ConstantKey> {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "NAME") // "key" is a reserved word in MySQL
    private ConstantKey key;

    private String value;

    Constant() {
    }

    public Constant(ConstantKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public ConstantKey getKey() {
        return key;
    }

    @Override
    public ConstantKey getId() {
        return key;
    }
}
