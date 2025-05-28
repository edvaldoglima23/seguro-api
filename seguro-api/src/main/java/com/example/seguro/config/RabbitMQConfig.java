package com.example.seguro.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Definição das filas, exchanges e routing keys
    public static final String EXCHANGE_NOTIFICACAO = "seguro.notificacao";
    public static final String QUEUE_NOVA_APOLICE = "apolice.nova.queue";
    public static final String QUEUE_VENCIMENTO = "apolice.vencimento.queue";
    public static final String ROUTING_KEY_NOVA_APOLICE = "apolice.nova";
    public static final String ROUTING_KEY_VENCIMENTO = "apolice.vencimento";
    
    @Bean
    public Queue queueNovaApolice() {
        return new Queue(QUEUE_NOVA_APOLICE, true);
    }
    
    @Bean
    public Queue queueVencimento() {
        return new Queue(QUEUE_VENCIMENTO, true);
    }
    
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NOTIFICACAO);
    }
    
    @Bean
    public Binding bindingNovaApolice(Queue queueNovaApolice, TopicExchange exchange) {
        return BindingBuilder.bind(queueNovaApolice).to(exchange).with(ROUTING_KEY_NOVA_APOLICE);
    }
    
    @Bean
    public Binding bindingVencimento(Queue queueVencimento, TopicExchange exchange) {
        return BindingBuilder.bind(queueVencimento).to(exchange).with(ROUTING_KEY_VENCIMENTO);
    }
    
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
} 