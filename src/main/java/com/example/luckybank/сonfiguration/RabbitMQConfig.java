package com.example.luckybank.сonfiguration;//package com.example.luckybank.сonfiguration;//package com.example.luckybank.сonfiguration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    static final String transferQueueName = "transferQueue";
    public static final String exchangeName = "transferExchange";
    static final String welcomeQueueName = "welcomeQueue";
    static final String welcomeExchangeName = "welcomeExchange";



    @Bean
    Queue transferQueue() {
        return new Queue(transferQueueName, false);
    }

    @Bean
    public FanoutExchange exchange() {
        return new FanoutExchange(exchangeName);
    }

    @Bean
    Queue welcomeQueue() {
        return new Queue(welcomeQueueName, false);
    }

    @Bean
    public Binding welcomeBinding(Queue welcomeQueue, FanoutExchange welcomeExchange) {
        return BindingBuilder.bind(welcomeQueue).to(welcomeExchange);
    }

    @Bean
    public FanoutExchange welcomeExchange() {
        return new FanoutExchange(welcomeExchangeName);
    }

    @Bean
    public Binding transferBinding(Queue transferQueue, FanoutExchange exchange) {
        return BindingBuilder.bind(transferQueue).to(exchange);
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);
        return template;
    }
}




