package plazadecomidas.messages.adapter.driving.http.rest.dto.response;

public record SendMessageResponse(
    boolean success,
    String message
) {
}
