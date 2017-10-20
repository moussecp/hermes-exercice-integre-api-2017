package com.hermes_ecs.java_exercise_rest_api.dao;

import com.google.common.base.Optional;
import com.hermes_ecs.java_exercise_rest_api.domain.constant.Constant;
import com.hermes_ecs.java_exercise_rest_api.domain.constant.ConstantKey;

public interface ConstantDao extends Dao<ConstantKey, Constant> {
    Optional<Constant> findByKey(ConstantKey constantKey);

    Optional<Constant> findByKeyName(String constantKeyName);
}
