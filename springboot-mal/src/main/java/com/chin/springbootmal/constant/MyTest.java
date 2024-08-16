package com.chin.springbootmal.constant;

public class MyTest {
    public static void main(String[] args) {
        PoductCategory poductCategory = PoductCategory.FOOD;
        String categoryName = poductCategory.name();
        System.out.println(categoryName);

        String car = "CAR";
        PoductCategory category = PoductCategory.valueOf(car);
        System.out.println(category);
    }
}