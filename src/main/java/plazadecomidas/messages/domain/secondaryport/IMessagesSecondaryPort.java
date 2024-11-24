package plazadecomidas.messages.domain.secondaryport;

import plazadecomidas.messages.domain.model.PhoneMessage;

public interface IMessagesSecondaryPort {
    boolean sendMessage(PhoneMessage phoneMessage);
}
