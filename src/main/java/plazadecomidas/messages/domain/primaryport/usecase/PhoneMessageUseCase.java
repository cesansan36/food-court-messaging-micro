package plazadecomidas.messages.domain.primaryport.usecase;

import plazadecomidas.messages.domain.model.OperationResult;
import plazadecomidas.messages.domain.model.PhoneMessage;
import plazadecomidas.messages.domain.primaryport.IMessagePrimaryPort;
import plazadecomidas.messages.domain.secondaryport.IMessagesSecondaryPort;
import plazadecomidas.messages.domain.util.DomainConstants;

public class PhoneMessageUseCase implements IMessagePrimaryPort {

    private final IMessagesSecondaryPort messagesSecondaryPort;

    public PhoneMessageUseCase(IMessagesSecondaryPort messagesSecondaryPort) {
        this.messagesSecondaryPort = messagesSecondaryPort;
    }

    @Override
    public OperationResult sendMessage(PhoneMessage phoneMessage) {
        boolean isMessageSent = messagesSecondaryPort.sendMessage(phoneMessage);

        if (isMessageSent) {
            return new OperationResult(true, DomainConstants.MESSAGE_SENT_SUCCESSFULLY);
        } else {
            return new OperationResult(false, DomainConstants.MESSAGE_NOT_SENT);
        }
    }
}
