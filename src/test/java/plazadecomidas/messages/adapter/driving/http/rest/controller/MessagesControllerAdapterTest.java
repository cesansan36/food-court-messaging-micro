package plazadecomidas.messages.adapter.driving.http.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import plazadecomidas.messages.adapter.driving.http.rest.dto.request.SendMessageRequest;
import plazadecomidas.messages.adapter.driving.http.rest.dto.response.SendMessageResponse;
import plazadecomidas.messages.adapter.driving.http.rest.mapper.ISendMessageRequestMapper;
import plazadecomidas.messages.adapter.driving.http.rest.mapper.ISendMessageResponseMapper;
import plazadecomidas.messages.domain.model.OperationResult;
import plazadecomidas.messages.domain.model.PhoneMessage;
import plazadecomidas.messages.domain.primaryport.IMessagePrimaryPort;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class MessagesControllerAdapterTest {

    private MessagesControllerAdapter messagesControllerAdapter;

    private IMessagePrimaryPort messagePrimaryPort;
    private ISendMessageRequestMapper sendMessageRequestMapper;
    private ISendMessageResponseMapper sendMessageResponseMapper;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        messagePrimaryPort = mock(IMessagePrimaryPort.class);
        sendMessageRequestMapper = mock(ISendMessageRequestMapper.class);
        sendMessageResponseMapper = mock(ISendMessageResponseMapper.class);
        messagesControllerAdapter = new MessagesControllerAdapter(messagePrimaryPort, sendMessageRequestMapper, sendMessageResponseMapper);

        mockMvc = MockMvcBuilders.standaloneSetup(messagesControllerAdapter).build();
    }

    @Test
    void sendMessageSuccess() throws Exception {
        Object inputObject = new Object() {
            public final String to = "1234567890";
            public final String message = "Hello";
        };
        ObjectMapper mapper = new ObjectMapper();
        String inputJson = mapper.writeValueAsString(inputObject);

        PhoneMessage phoneMessage = new PhoneMessage("1234567890", "Hello");
        OperationResult operationResult = new OperationResult(true,"Message sent successfully");
        SendMessageResponse sendMessageResponse = new SendMessageResponse(true, "Message sent successfully");

        when(messagePrimaryPort.sendMessage(any(PhoneMessage.class))).thenReturn(operationResult);
        when(sendMessageRequestMapper.toPhoneMessage(any(SendMessageRequest.class))).thenReturn(phoneMessage);
        when(sendMessageResponseMapper.toSendMessageResponse(any(OperationResult.class))).thenReturn(sendMessageResponse);

        MockHttpServletRequestBuilder request = post("/messages/send")
                .contentType("application/json")
                .content(inputJson);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(result -> result.getResponse().getContentAsString().equals(mapper.writeValueAsString(operationResult)));

        assertAll(
                () -> verify(messagePrimaryPort, times(1)).sendMessage(any(PhoneMessage.class)),
                () -> verify(sendMessageRequestMapper, times(1)).toPhoneMessage(any(SendMessageRequest.class)),
                () -> verify(sendMessageResponseMapper, times(1)).toSendMessageResponse(any(OperationResult.class))
        );
    }

    @Test
    void sendMessageFailure() throws Exception {
        Object inputObject = new Object() {
            public final String to = "1234567890";
            public final String message = "Hello";
        };
        ObjectMapper mapper = new ObjectMapper();
        String inputJson = mapper.writeValueAsString(inputObject);

        PhoneMessage phoneMessage = new PhoneMessage("1234567890", "Hello");
        OperationResult operationResult = new OperationResult(false,"Message not sent");
        SendMessageResponse sendMessageResponse = new SendMessageResponse(false, "Message not sent");

        when(messagePrimaryPort.sendMessage(any(PhoneMessage.class))).thenReturn(operationResult);
        when(sendMessageRequestMapper.toPhoneMessage(any(SendMessageRequest.class))).thenReturn(phoneMessage);
        when(sendMessageResponseMapper.toSendMessageResponse(any(OperationResult.class))).thenReturn(sendMessageResponse);

        MockHttpServletRequestBuilder request = post("/messages/send")
                .contentType("application/json")
                .content(inputJson);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(result -> result.getResponse().getContentAsString().equals(mapper.writeValueAsString(operationResult)));

        assertAll(
                () -> verify(messagePrimaryPort, times(1)).sendMessage(any(PhoneMessage.class)),
                () -> verify(sendMessageRequestMapper, times(1)).toPhoneMessage(any(SendMessageRequest.class)),
                () -> verify(sendMessageResponseMapper, times(1)).toSendMessageResponse(any(OperationResult.class))
        );
    }
}