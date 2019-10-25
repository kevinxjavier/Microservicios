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

### Productos

[POST]	http://192.168.1.100:8090/api/productos/crear
Body | Raw | JSON
{
	"nombre": "Samsung Galaxy 12",
	"precio": 1500,
	"fechaCreacion": "2019-10-23"
}


$ curl -X POST \
  http://192.168.1.100:8090/api/productos/crear \
  -H 'Content-Type: application/json' \
  -d '{
	"nombre": "Xiomi Mi 7",
	"precio": 1500,
	"fechaCreacion": "2019-10-23"
}'

----------------------------------
[PUT]	http://192.168.1.100:8090/api/productos/editar/1
Body | Raw | JSON
{
	"nombre": "Samsung Galaxy 12",
	"precio": 1500,
	"fechaCreacion": "2019-10-23"
}


$ curl -X PUT \
  http://192.168.1.100:8090/api/productos/editar/1 \
  -H 'Content-Type: application/json' \
  -d '{
	"nombre": "Samsung Galaxy 11",
	"precio": 1500,
	"fechaCreacion": "2019-10-23"
}'

----------------------------------
[DELETE]	http://192.168.1.100:8090/api/productos/eliminar/101

$ curl -X DELETE http://192.168.1.100:8090/api/productos/eliminar/100

### Items

[POST]	http://192.168.1.100:8090/api/productos/crear
Body | Raw | JSON

$ curl -X POST \
  http://192.168.1.100:8090/api/productos/crear \
  -H 'Content-Type: application/json' \
  -d '{
	"id": null,
	"nombre": "Anzel",
	"precio": 1978,
	"fechaCreacion": "2018-10-09"
}'

----------------------------------
[PUT] 	http://192.168.1.100:8090/api/productos/editar/101
Body | Raw | JSON

$ curl -X PUT \
  http://192.168.1.100:8090/api/productos/editar/101 \
  -H 'Content-Type: application/json' \
  -d '{
	"id": null,
	"nombre": "New Anzel",
	"precio": 2000
}'

----------------------------------
[DELETE]	http://192.168.1.100:8090/api/productos/eliminar/103

$ curl -X DELETE http://192.168.1.100:8090/api/productos/eliminar/103

```

