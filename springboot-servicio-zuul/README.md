# Run

```
$ mvn spring-boot:run
```
Or
```
$ java -jar target/*-0.0.1-SNAPSHOT.jar
```

# Go to

```
$ curl http://localhost:8090/api/productos/listar
$ curl http://localhost:8090/api/productos/ver/1
$ curl http://localhost:8090/api/items/listar
$ curl http://localhost:8090/api/items/ver/1/cantidad/5
$ curl http://localhost:8090/api/items/obtener-config

# Para refrescar los @Componentes que tengan @RefreshScope. Se invoca manualmente esta URL cuado se cambien datos en Spring Cloud Config
# de esta manera no es necesario Reiniciar el Servicio.
# Solo funciona para cambiar configuraciones propias de nosotros en Spring Cloud Config. Puerto y configuraciones de Base de Datos estamos 
# obligados a Reiniciar el Microservicio. Si el Repo Git esta en local no es necesario hacer commit.
$ curl -X POST http://localhost:8090/api/items/actuator/refresh
```
