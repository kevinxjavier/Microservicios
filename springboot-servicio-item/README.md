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
# obligados a Reiniciar el Microservicio. Si el Repo Git esta en local no es necesario hacer commit.
$ curl -X POST http://localhost:8005/actuator/refresh

[POST]	http://192.168.1.100:8005/crear
Body | Raw | JSON

$ curl -X POST \
  http://192.168.1.100:8005/crear \
  -H 'Content-Type: application/json' \
  -d '{
	"id": null,
	"nombre": "Anzel",
	"precio": 1978,
	"fechaCreacion": "2018-10-09"
}'

----------------------------------
[PUT] 	http://192.168.1.100:8005/editar/101
Body | Raw | JSON

$ curl -X PUT \
  http://192.168.1.100:8005/editar/101 \
  -H 'Content-Type: application/json' \
  -d '{
	"id": null,
	"nombre": "New Anzel",
	"precio": 2000
}'

----------------------------------
[DELETE]	http://192.168.1.100:8005/eliminar/103

$ curl -X DELETE http://192.168.1.100:8005/eliminar/103
```
