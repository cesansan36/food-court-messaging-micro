package plazadecomidas.messages.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import plazadecomidas.messages.adapter.driven.messaging.adapter.MessagesAdapter;
import plazadecomidas.messages.domain.IMessagesSecondaryPort;

@Configuration
public class BeanConfiguration {

    @Bean
    public IMessagesSecondaryPort messagesSecondaryPort() {
        return new MessagesAdapter();
    }
}
