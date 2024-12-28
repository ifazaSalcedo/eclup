package com.app.eclub.stock.config;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;

    @RequiredArgsConstructor
    @Configuration
    //@ConditionalOnClass(EnableRabbit.class)
    public class RabbitConfig {
        public static final String QUEUE_STOCK_NAME = "ventas_queue";


        @Bean
        public Queue ventasQueue() {
            return new Queue(QUEUE_STOCK_NAME, true); // Cola durable
        }

        @Bean
        public Jackson2JsonMessageConverter messageConverter(){
            ObjectMapper objectMapper= new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.findAndRegisterModules();
            return new Jackson2JsonMessageConverter(objectMapper);
        }


        @Bean
        public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setChannelTransacted(true); // Activa las transacciones
            rabbitTemplate.setMessageConverter(messageConverter); // Establece el convertidor de mensajes
            return rabbitTemplate;
        }
    }
