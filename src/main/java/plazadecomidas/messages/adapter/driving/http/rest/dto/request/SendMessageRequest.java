package plazadecomidas.messages.adapter.driving.http.rest.dto.request;

public record SendMessageRequest(
    String to,
    String message
) {
}
