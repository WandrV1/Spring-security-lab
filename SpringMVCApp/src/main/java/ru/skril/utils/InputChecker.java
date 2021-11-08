package ru.skril.utils;

import java.util.Scanner;

public class InputChecker {

    private static Boolean isDouble(String value) {
        Scanner scanner = new Scanner(value.replace(".", ","));
        return scanner.hasNextDouble();
    }

    private static Boolean isInteger(String value) {
        Scanner scanner = new Scanner(value);
        return scanner.hasNextInt();
    }

    public static Boolean checkSearchParam(String param, String type) {
        switch (type) {
            case "name":
            case "substance":
            case "country":
                return true;
            case "priceLess":
            case "priceBigger":
            case "priceEqual":
                return isInteger(param);
            case "ratingLess":
            case "ratingBigger":
            case "ratingEqual":
                return isDouble(param);
            default:
                return false;
        }
    }
}
