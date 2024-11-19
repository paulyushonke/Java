package csit.semit.spr.webappssprlab3.model;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class ValidationUtil {

    public static String validateName(String name, String fieldName, Map<String, String> errors) {
        if (name == null || name.trim().isEmpty()) {
            errors.put(fieldName.toLowerCase(), fieldName + " cannot be empty");
            return name;
        }

        String trimmedName = name.trim();

        if (trimmedName.length() < 2) {
            errors.put(fieldName.toLowerCase(), fieldName + " must be at least 2 characters long");
            return name;
        }

        if (trimmedName.length() > 255) {
            errors.put(fieldName.toLowerCase(), fieldName + " must not exceed 255 characters");
            return name;
        }

        if (!trimmedName.matches("^[A-Za-zА-Яа-я\\s-]+$")) {
            errors.put(fieldName.toLowerCase(), fieldName + " must contain only letters, spaces, and hyphens");
            return name;
        }

        return trimmedName;
    }
    public static LocalDate validateBirthday(String birthday, Map<String, String> errors) {
        if (birthday == null || birthday.trim().isEmpty()) {
            errors.put("birthday", "Birthday is required");
            return null;
        }
        try {
            LocalDate birthdayDate = LocalDate.parse(birthday);
            if (birthdayDate.isAfter(LocalDate.now())) {
                errors.put("birthday", "Birthday cannot be in the future");
                return null;
            }
            if(birthdayDate.isBefore(LocalDate.of(1900,1,1))){
                errors.put("birthday", "Birthday cannot be before 1900");
                return null;
            }
            return birthdayDate;
        } catch (DateTimeParseException e) {
            errors.put("birthday", "Invalid date format for birthday");
            return null;
        }
    }

    public static boolean validateGender(String gender, Map<String, String> errors) {
        if (gender == null || gender.trim().isEmpty()) {
            errors.put("gender", "Gender is required");
            return false;
        }
        if (!gender.equals("Male") && !gender.equals("Female")) {
            errors.put("gender", "Invalid gender value");
            return false;
        }
        return gender.equals("Male");
    }

}
