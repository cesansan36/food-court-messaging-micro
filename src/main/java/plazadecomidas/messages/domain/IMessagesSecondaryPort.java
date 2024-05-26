package plazadecomidas.messages.domain;

import plazadecomidas.messages.adapter.driving.http.rest.dto.SendMessageRequest;

public interface IMessagesSecondaryPort {
    void sendMessage(SendMessageRequest sendMessageRequest);
}
