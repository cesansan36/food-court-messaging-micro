package plazadecomidas.messages.adapter.driving.http.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plazadecomidas.messages.adapter.driving.http.rest.dto.request.SendMessageRequest;
import plazadecomidas.messages.adapter.driving.http.rest.dto.response.SendMessageResponse;
import plazadecomidas.messages.adapter.driving.http.rest.mapper.ISendMessageRequestMapper;
import plazadecomidas.messages.adapter.driving.http.rest.mapper.ISendMessageResponseMapper;
import plazadecomidas.messages.domain.primaryport.IMessagePrimaryPort;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessagesControllerAdapter {

    private final IMessagePrimaryPort messagePrimaryPort;
    private final ISendMessageRequestMapper sendMessageRequestMapper;
    private final ISendMessageResponseMapper sendMessageResponseMapper;

    @PostMapping("send")
    @PreAuthorize("hasRole('EMPLOYEE')")
    public ResponseEntity<String> sendMessage(@RequestBody SendMessageRequest sendMessageRequest) {

        SendMessageResponse result = sendMessageResponseMapper.toSendMessageResponse(
                messagePrimaryPort.sendMessage(
                        sendMessageRequestMapper.toPhoneMessage(sendMessageRequest)));

        if (result.success()) {
            return ResponseEntity.ok().body(result.message());
        }
        else {
            return ResponseEntity.badRequest().body(result.message());
        }
    }
}
