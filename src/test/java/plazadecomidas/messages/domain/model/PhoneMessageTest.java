package plazadecomidas.messages.domain.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import plazadecomidas.messages.domain.exception.EmptyFieldException;
import plazadecomidas.messages.domain.exception.RuleInvalidException;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PhoneMessageTest {

    @ParameterizedTest
    @MethodSource("correctData")
    void CreatePhoneMessageSuccess(String to, String message) {
        PhoneMessage phoneMessage = new PhoneMessage(to, message);

        assertEquals(to, phoneMessage.getTo());
        assertEquals(message, phoneMessage.getMessage());
    }

    @ParameterizedTest
    @MethodSource("dataWithEmptyOrNullFields")
    void CreatePhoneMessageFailBecauseEmptyOrNullData(String to, String message) {
        assertThrows(EmptyFieldException.class, () -> new PhoneMessage(to, message));
    }

    @ParameterizedTest
    @MethodSource("dataWithBrokenRules")
    void CreatePhoneMessageFailBecauseBrokenRules(String to, String message) {
        assertThrows(RuleInvalidException.class, () -> new PhoneMessage(to, message));
    }

    static Stream<Arguments> correctData() {
        return Stream.of(
                Arguments.of("+123456789", "Hello"),
                Arguments.of("987654321", "New World")
        );
    }

    static Stream<Arguments> dataWithEmptyOrNullFields() {
        return Stream.of(
                Arguments.of("", "Hello"),
                Arguments.of("      ", "Hello"),
                Arguments.of("+123456789", ""),
                Arguments.of("+123456789", "     "),
                Arguments.of("", ""),
                Arguments.of(null, null),
                Arguments.of(null, "Hello"),
                Arguments.of("+123456789", null)
        );
    }

    static Stream<Arguments> dataWithBrokenRules() {
        return Stream.of(
                Arguments.of("-123456789", "Hello"),
                Arguments.of("+12365498456354654459456789", "Hello"),
                Arguments.of("987+654321", "World")
        );
    }
}