#!/bin/bash

# Este script es para Levantar Zipkin Server con RabbitMQ y el proyecto SpringBoot: sleuth-zipkin-rabbit_mq-example2

# Nota: 
# 	 5672 -> Puerto del Boker 
#	15672 -> Puerto del WebManager

export RABBIT_ADDRESSES=localhost:5672
java -jar ../zipkin-server-2.19.1-exec.jar

# 	Verificar en	http://localhost:15672/#/connections 	Debe verse una Coneccion
# 			http://localhost:15672/#/channels				Debe verse un Canal
# 			http://localhost:15672/#/queues					Debe verse una Cola llamada: zipkin

