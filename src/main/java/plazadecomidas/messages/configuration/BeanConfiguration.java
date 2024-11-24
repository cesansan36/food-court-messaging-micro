package plazadecomidas.messages.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plazadecomidas.messages.adapter.driven.messaging.adapter.MessagesAdapter;
import plazadecomidas.messages.domain.primaryport.IMessagePrimaryPort;
import plazadecomidas.messages.domain.primaryport.usecase.PhoneMessageUseCase;
import plazadecomidas.messages.domain.secondaryport.IMessagesSecondaryPort;

@Configuration
public class BeanConfiguration {

    @Bean
    public IMessagesSecondaryPort messagesSecondaryPort() {
        return new MessagesAdapter();
    }

    @Bean
    public IMessagePrimaryPort messagePrimaryPort(IMessagesSecondaryPort messagesSecondaryPort) {
        return new PhoneMessageUseCase(messagesSecondaryPort);
    }
}
