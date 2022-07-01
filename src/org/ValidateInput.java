package org;

public class ValidateInput {
    // validate First Name

    public static boolean validateFirstName(String firstName) {
        return firstName.matches("[A-Z][a-zA-Z]*");

    }
    public static boolean validateLastName(String lastName) {
        return lastName.matches("[a-zA-Z]+(['-][a-zA-Z]+)*");
    }
    public static boolean validatePhoneNumber(String phoneNumber) {
        return phoneNumber.matches("[1-9]\\d{2}-[1-9]\\d{2}-\\d{4}");
    }

    public static boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}
