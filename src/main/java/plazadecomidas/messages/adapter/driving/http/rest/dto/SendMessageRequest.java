package plazadecomidas.messages.adapter.driving.http.rest.dto;

public record SendMessageRequest(
    String to,
    String message
) {
}
