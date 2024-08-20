package com.chin.springbootmal.constant;

public class MyTest {
    public static void main(String[] args) {
        ProductCategory poductCategory = ProductCategory.FOOD;
        String categoryName = poductCategory.name();
        System.out.println(categoryName);
        String car = "CAR";
        ProductCategory category = ProductCategory.valueOf(car);
        System.out.println(category);
    }
}