package com.hermes_ecs.java_exercise_rest_api.domain;

import com.hermes_ecs.java_exercise_rest_api.domain.constant.Department;

import java.util.ArrayList;
import java.util.List;

public abstract class ProductMother {

    public static final RepublicDactaryAmount PRICE_1000 = new RepublicDactaryAmount("1000");

    public static final String LANDSPEEDER_X34 = "Landspeeder X-34";
    public static final String LANDSPEEDER_X34_DESCRIPTION = "Durable if not stylish, the X-34 featured holographic " +
            "displays, a computer for ground navigation, and a number of repulsor counterbalances for smooth and " +
            "steady travel over rough terrain. Its maximum altitude was 1 meter (100 cm ), but its usual cruising " +
            "altitude was roughly 10 cm above ground level. It utilized three turbine engines to propel the vehicle " +
            "forward. Its repulsorfield generator housing was located behind the cockpit. Its power circuit likewise " +
            "was located in front of the cockpit.";
    public static final RepublicDactaryAmount LANDSPEEDER_X34_PRICE = new RepublicDactaryAmount("10550 ||7");

    public static final String T47_AIRSPEEDER = "T-47 airspeeder";
    public static final String T47_AIRSPEEDER_DESCRIPTION = "The Incom Corporation's T-47 was an atmospheric vehicle " +
            "designed for industrial cargo handling. Its cockpit featured positions for a forward-facing pilot and a " +
            "rear-facing cargo manager. The cargo manager used the airspeeder's magnetic harpoon and tow cable to " +
            "control repulsorlift cargo modules.";
    public static final RepublicDactaryAmount T47_AIRSPEEDER_PRICE = new RepublicDactaryAmount("25000 ||7");
    public static final String CATEGORY_NAME = "category_name";
    public static final Department DEPARTMENT = Department.ACCESSORIES;

    private ProductMother() {
    }

    public static Product landSpeeder() {
        return new Product(LANDSPEEDER_X34, null, LANDSPEEDER_X34_DESCRIPTION, LANDSPEEDER_X34_PRICE);
    }

    public static Product t47Airspeeder() {
        return new Product(T47_AIRSPEEDER, null, T47_AIRSPEEDER_DESCRIPTION, T47_AIRSPEEDER_PRICE);
    }


    public static List<Product> getSimpleProducts(int number) {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < number; i++)
            products.add(getSimpleProduct(i));
        return products;
    }

    public static Product getSimpleProduct() {
        return getSimpleProduct(42);
    }

    public static Product getSimpleProduct(int idx) {
        Category category = new Category(CATEGORY_NAME, DEPARTMENT);
        return new Product("label" + idx, category, "description" + idx, PRICE_1000);
    }
}
