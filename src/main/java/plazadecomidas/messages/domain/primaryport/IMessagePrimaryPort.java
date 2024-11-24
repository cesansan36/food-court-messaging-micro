package plazadecomidas.messages.domain.primaryport;

import plazadecomidas.messages.domain.model.OperationResult;
import plazadecomidas.messages.domain.model.PhoneMessage;

public interface IMessagePrimaryPort {
    OperationResult sendMessage(PhoneMessage phoneMessage);
}
