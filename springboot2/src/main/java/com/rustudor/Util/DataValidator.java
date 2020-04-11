package com.rustudor.Util;

import com.rustudor.Dto.ConsumptionDto;
import com.rustudor.Dto.FullUserDto;
import com.rustudor.Dto.ItemDto;

import java.sql.Timestamp;
import java.util.regex.Pattern;

public class DataValidator {

    private static boolean isPositiveNumber(int calories) {
        if (calories>0)
            return true;
        return false;
    }

    private static boolean isName(String name) {
        return name.matches("^[ A-Za-z]+$");
    }
    private static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }

    private static boolean isNumeric(String name) {
        return name.matches("[0-9]+");
    }

    private static boolean isAlphanumeric(String name) {
        return name.matches("[A-Za-z0-9]+");
    }

    private static boolean isValidPeriod(Timestamp purchaseDate, Timestamp expirationDate) {
        if (purchaseDate.getTime()-expirationDate.getTime()<0)
            return true;
        return false;
    }

    public static boolean isEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static boolean validateItem(ItemDto itemDto){
        if (!DataValidator.isName(itemDto.getName())){
            System.out.println("1");
            return false;
        }

        if (!DataValidator.isPositiveNumber(itemDto.getCalories())) {
            System.out.println("2");
            return false;
        }
        if (!DataValidator.isPositiveNumber(itemDto.getQuantity())) {
            System.out.println("3");
            return false;
        }
        if (!DataValidator.isValidPeriod(itemDto.getPurchaseDate(),itemDto.getExpirationDate())) {
            System.out.println("4");
            return false;
        }
        return true;
    }

    public static boolean validateUser(FullUserDto fullUserDto) {
        if (!DataValidator.isAlphanumeric(fullUserDto.getUsername()))
            return false;
        if (!DataValidator.isAlphanumeric(fullUserDto.getPassword()))
            return false;
        if (!DataValidator.isName(fullUserDto.getName()))
            return false;
        if (!DataValidator.isEmail(fullUserDto.getEmail()))
            return false;
        return true;
    }

    public static boolean validateGoal(String goal) {
        if (!DataValidator.isNumeric(goal))
            return false;
        if (!DataValidator.isPositiveNumber(Integer.parseInt(goal)))
            return false;
        return true;
    }


    public static boolean validateConsumptionDto(ConsumptionDto consumptionDto) {
        if (!DataValidator.isName(consumptionDto.getName()))
            return false;
        return true;
    }
}
