# MICROSERVICIOS + COMUNICACION ENTRE MICROSERVICIOS + BROKER DE MENSAJERIA RABBITMQ

Exponer dos microservicios Ventas y Stock. Establecer la comunicación entre ellos.

## Características

- Utilizar spring boot 3 como Marco de desarrollo.
- FeignClients para la cominicación entre microservicios.
- Utilizar RabbitMQ como broker para el envio de mensaje entre microservicios.
- Base de datos PostgreSQL

## Requisitos

- Java 17
- Spring boot 3+
- RabbitMQ
- Docker

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/ifazaSalcedo/eclup.git


## Configurar broker
   - Exchange: exchage_ventas_stock
   - Routing key: exchage_ventas_stock
   - Queue: ventas_queue

## Despliegue con docker-compose
1. Desplegar:
   ```bash
   docker compose up -d

2. Serciorarse que existan la base de datos requeridas

