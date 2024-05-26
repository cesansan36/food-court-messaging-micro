package plazadecomidas.messages.domain.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OperationResultTest {

    @ParameterizedTest
    @CsvSource({
        "true, Operation was successful",
        "false, Operation failed"
    })
    void createOperationResult(boolean success, String message) {

        OperationResult operationResult = new OperationResult(success, message);

        assertEquals(success, operationResult.isSuccess());
        assertEquals(message, operationResult.getMessage());
    }

}