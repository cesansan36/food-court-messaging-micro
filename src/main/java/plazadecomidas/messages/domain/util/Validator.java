package plazadecomidas.messages.domain.util;

import java.util.regex.Pattern;

public class Validator {

    private Validator() {
        throw new IllegalStateException("Utility class");
    }

    private static final String PHONE_NUMBER_PATTERN = "^(\\+)?\\d{1,13}$";
    private static final Pattern PHONE_NUMBER_REGEX = Pattern.compile(PHONE_NUMBER_PATTERN);

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return PHONE_NUMBER_REGEX.matcher(phoneNumber).matches();
    }

    public static boolean isEmptyField(String field) {
        return field == null || field.trim().isEmpty();
    }

    public static final String PHONE_NUMBER_VALIDATION_MESSAGE = "The number you are trying to use is not valid";
    public static final String EMPTY_FIELD_VALIDATION_MESSAGE = "The field %s cannot be empty";
}
