package com.app.eclub.ventas.configure;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Binding.DestinationType;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;

    @RequiredArgsConstructor
    @Configuration
    //@ConditionalOnClass(EnableRabbit.class)
    public class RabbitConfig {
        
        public static final String EXCHANGE_STOCK_NAME = "exchage_ventas_stock";
        public static final String QUEUE_STOCK_NAME = "ventas_queue";
        public static final String ROUTING_STOCK_KEY = "ventas_queue_key";

    

        @Bean
        public Queue ventasQueue(){
            return new Queue(QUEUE_STOCK_NAME, true);
        }

        @Bean
        public TopicExchange ventasExchange(){
            return new TopicExchange(EXCHANGE_STOCK_NAME);
        }

        @Bean
        public Binding ventasBinding(){
            return BindingBuilder
            .bind(ventasQueue())
            .to(ventasExchange())
            .with(ROUTING_STOCK_KEY);
        }

        @Bean
        @ConditionalOnMissingClass("org.springframework.orm.jpa.JpaTransactionManager")
        public RabbitTransactionManager rabbitTransactionManager(ConnectionFactory connectionFactory){
            return new RabbitTransactionManager(connectionFactory);
        }


        @Bean
        public Jackson2JsonMessageConverter messageConverter(){
            ObjectMapper objectMapper= new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.findAndRegisterModules();
            return new Jackson2JsonMessageConverter(objectMapper);
        }


        @Bean        
        public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
            return new RabbitAdmin(connectionFactory);
        }

        @Bean
        public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter messageConverter) {
            RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
            rabbitTemplate.setChannelTransacted(true); // Activa las transacciones
            rabbitTemplate.setMessageConverter(messageConverter); // Establece el convertidor de mensajes
            return rabbitTemplate;
        }
    }
