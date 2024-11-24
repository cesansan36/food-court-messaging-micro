package plazadecomidas.messages.domain.model;

import plazadecomidas.messages.domain.exception.EmptyFieldException;
import plazadecomidas.messages.domain.exception.RuleInvalidException;
import plazadecomidas.messages.domain.util.DomainConstants;
import plazadecomidas.messages.domain.util.Validator;

public class PhoneMessage {

    private final String to;
    private final String message;

    public PhoneMessage(String to, String message) {
        validate(to, message);
        to = to.trim();
        message = message.trim();

        this.to = to;
        this.message = message;
    }

    private void validate(String to, String message) {
        if (Validator.isEmptyField(to)) {
            throw new EmptyFieldException(String.format(Validator.EMPTY_FIELD_VALIDATION_MESSAGE, DomainConstants.MessageFields.TO));
        }
        if (!Validator.isValidPhoneNumber(to)) {
            throw new RuleInvalidException(Validator.PHONE_NUMBER_VALIDATION_MESSAGE);
        }
        if (Validator.isEmptyField(message)) {
            throw new EmptyFieldException(String.format(Validator.EMPTY_FIELD_VALIDATION_MESSAGE, DomainConstants.MessageFields.MESSAGE));
        }
    }

    public String getTo() {
        return to;
    }

    public String getMessage() {
        return message;
    }
}
