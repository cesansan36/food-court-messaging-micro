package plazadecomidas.messages.adapter.driving.http.rest.mapper;

import org.mapstruct.Mapper;
import plazadecomidas.messages.adapter.driving.http.rest.dto.request.SendMessageRequest;
import plazadecomidas.messages.domain.model.PhoneMessage;

@Mapper(componentModel = "spring")
public interface ISendMessageRequestMapper {

    PhoneMessage toPhoneMessage(SendMessageRequest sendMessageRequest);
}
