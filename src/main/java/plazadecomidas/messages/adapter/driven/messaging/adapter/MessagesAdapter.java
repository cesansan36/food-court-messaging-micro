package plazadecomidas.messages.adapter.driven.messaging.adapter;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import plazadecomidas.messages.adapter.driving.http.rest.dto.SendMessageRequest;
import plazadecomidas.messages.domain.IMessagesSecondaryPort;

public class MessagesAdapter implements IMessagesSecondaryPort {

    @Value("${application.messaging.twilio.sid}")
    private String sid;

    @Value("${application.messaging.twilio.token}")
    private String token;

    @Value("${application.messaging.twilio.from}")
    private String from;

    @Override
    public void sendMessage(SendMessageRequest sendMessageRequest) {
        try {
            Twilio.init(sid, token);

            Message.creator(
                    new com.twilio.type.PhoneNumber(sendMessageRequest.to()),
                    new com.twilio.type.PhoneNumber(from),
                    sendMessageRequest.message()
            ).create();

            System.out.println("Message sent");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
