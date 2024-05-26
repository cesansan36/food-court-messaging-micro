package plazadecomidas.messages.adapter.driving.http.rest.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plazadecomidas.messages.adapter.driving.http.rest.dto.SendMessageRequest;
import plazadecomidas.messages.domain.IMessagesSecondaryPort;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessagesControllerAdapter {

    private final IMessagesSecondaryPort messagesSecondaryPort;

    @PostMapping("send")
    public void sendMessage(@RequestBody SendMessageRequest sendMessageRequest) {

        messagesSecondaryPort.sendMessage(sendMessageRequest);
    }
}
