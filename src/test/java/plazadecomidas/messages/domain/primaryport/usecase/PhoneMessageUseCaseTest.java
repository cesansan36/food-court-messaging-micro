package plazadecomidas.messages.domain.primaryport.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import plazadecomidas.messages.domain.model.OperationResult;
import plazadecomidas.messages.domain.model.PhoneMessage;
import plazadecomidas.messages.domain.secondaryport.IMessagesSecondaryPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PhoneMessageUseCaseTest {

    private PhoneMessageUseCase phoneMessageUseCase;

    private IMessagesSecondaryPort messagesSecondaryPort;

    @BeforeEach
    void setUp() {
        messagesSecondaryPort = mock(IMessagesSecondaryPort.class);
        phoneMessageUseCase = new PhoneMessageUseCase(messagesSecondaryPort);
    }

    @ParameterizedTest
    @CsvSource({"true", "false"})
    void sendMessage(boolean expectedResult) {

        PhoneMessage phoneMessage = mock(PhoneMessage.class);

        when(messagesSecondaryPort.sendMessage(any(PhoneMessage.class))).thenReturn(expectedResult);

        OperationResult result = phoneMessageUseCase.sendMessage(phoneMessage);

        assertEquals(expectedResult, result.isSuccess());
        verify(messagesSecondaryPort, times(1)).sendMessage(any(PhoneMessage.class));
    }
}