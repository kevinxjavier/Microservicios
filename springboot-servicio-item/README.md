# Run

```
$ mvn spring-boot:run
```
Or
```
$ java -jar target/*-0.0.1-SNAPSHOT.jar
```

# Go to
El puerto lo determina Spring Cloud Config

```
$ curl http://localhost:8005/listar
$ curl http://localhost:8005/ver/1/cantidad/1
$ curl http://localhost:8005/obtenet-config

# Para refrescar los @Componentes que tengan @RefreshScope. Se invoca manualmente esta URL cuado se cambien datos en Spring Cloud Config
# de esta manera no es necesario Reiniciar el Servicio.
# Solo funciona para cambiar configuraciones propias de nosotros en Spring Cloud Config. Puerto y configuraciones de Base de Datos estamos 
# obligados a Reiniciar el Microservicio.
$ curl -X POST http://localhost:8005/actuator/refresh
```

