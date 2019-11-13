#!/bin/bash

# Este script es para Levantar Zipkin Server con RabbitMQ y el proyecto SpringBoot: sleuth-zipkin-rabbit_mq-example2

# Nota: 
# 	 5672 -> Puerto del Boker 
#	15672 -> Puerto del WebManager

# Register in RabbitMQ
export RABBIT_ADDRESSES=localhost:5672

# Storage Zipkin in MySQL -> https://github.com/openzipkin/zipkin/tree/master/zipkin-server
export STORAGE_TYPE=mysql
export MYSQL_USER=zipkin
export MYSQL_PASS=zipkin
export MYSQL_HOST=172.17.0.3
export MYSQL_TCP_PORT=3306

java -jar ../zipkin-server-2.19.1-exec.jar

# 	Verificar en	http://localhost:15672/#/connections 	Debe verse una Coneccion
# 			http://localhost:15672/#/channels				Debe verse un Canal
# 			http://localhost:15672/#/queues					Debe verse una Cola llamada: zipkin

