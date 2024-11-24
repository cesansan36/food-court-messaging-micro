package plazadecomidas.messages.adapter.driving.http.rest.mapper;

import org.mapstruct.Mapper;
import plazadecomidas.messages.adapter.driving.http.rest.dto.response.SendMessageResponse;
import plazadecomidas.messages.domain.model.OperationResult;

@Mapper(componentModel = "spring")
public interface ISendMessageResponseMapper {

    SendMessageResponse toSendMessageResponse(OperationResult operationResult);
}
