package plazadecomidas.messages.adapter.driven.messaging.adapter;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import plazadecomidas.messages.domain.model.PhoneMessage;
import plazadecomidas.messages.domain.secondaryport.IMessagesSecondaryPort;

public class MessagesAdapter implements IMessagesSecondaryPort {

    @Value("${application.messaging.twilio.sid}")
    private String sid;

    @Value("${application.messaging.twilio.token}")
    private String token;

    @Value("${application.messaging.twilio.from}")
    private String from;

    @Override
    public boolean sendMessage(PhoneMessage phoneMessage) {

        try {
            Twilio.init(sid, token);

            Message.creator(
                    new com.twilio.type.PhoneNumber(phoneMessage.getTo()),
                    new com.twilio.type.PhoneNumber(from),
                    phoneMessage.getMessage()
            ).create();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
