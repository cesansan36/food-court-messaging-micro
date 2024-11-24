package plazadecomidas.messages.domain.util;

public class DomainConstants {

    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum MessageFields {
        TO,
        MESSAGE
    }

    public static final String MESSAGE_SENT_SUCCESSFULLY = "Message sent successfully.";
    public static final String MESSAGE_NOT_SENT = "Message could not be sent.";
}
