# Database 
	
	Este archivo se ejecuta automaticamente al levantar el servicio. Solo hay INSERTS.

```
	$ ls src/main/resource/import.sql
```

## SQL Adicional	

```
	-- No es necesario ya que se crea dinamicamente por JPA
	
	DROP TABLE `productos`;
	
	CREATE TABLE `productos` (
	  `id` mediumint(8) unsigned NOT NULL auto_increment,
	  `nombre` varchar(255) default NULL,
	  `precio` mediumint default NULL,
	  `fecha_creacion` TEXT default NULL,
	  PRIMARY KEY (`id`)
	) AUTO_INCREMENT=1;
```

# Run

```
	$ mvn spring-boot:run
	Or
	$ java -jar target/*-0.0.1-SNAPSHOT.jar
```

## Run recommended
```
	$ java -jar target/springboot-servicio-productos-0.0.1-SNAPSHOT.jar --server.port=8001
	$ java -jar target/springboot-servicio-productos-0.0.1-SNAPSHOT.jar --server.port=9001
	Or
	$ SERVER_PORT=8001 mvn spring-boot:run
	$ SERVER_PORT=9001 mvn spring-boot:run
```

# Go to

```
[GET]
	$ curl http://localhost:8001/listar
	$ curl http://localhost:9001/listar
	$ curl http://localhost:[PORT]/listar
	$ curl http://localhost:8001/ver/1
	$ curl http://localhost:9001/ver/1
	$ curl http://localhost:[PORT]/ver/1

----------------------------------
[POST]	http://192.168.1.100:[PORT]/crear
Body | Raw | JSON

	$ curl -X POST \
	  http://192.168.1.100:[PORT]/crear \
	  -H 'Content-Type: application/json' \
	  -d '{
		"nombre": "Xiomi Mi 7",
		"precio": 1500,
		"fechaCreacion": "2019-10-23"
	}'

----------------------------------
[PUT]	http://192.168.1.100:[PORT]/editar/1
Body | Raw | JSON

	$ curl -X PUT \
	  http://192.168.1.100:[PORT]/editar/1 \
	  -H 'Content-Type: application/json' \
	  -d '{
		"nombre": "Samsung Galaxy 11",
		"precio": 1500,
		"fechaCreacion": "2019-10-23"
	}'

----------------------------------
[DELETE]
	$ curl -X DELETE http://192.168.1.100:[PORT]/eliminar/100
```

# Docker

```
	$ mvn clean compile package	# Evitando los Tests: $ mvn clean compile package -DskipTests
	$ sudo docker build -t servicio-productos:v1 .
	# sudo docker run -P --name servicio-productos --network springcloud servicio-productos:v1
	$ sudo docker run -P --network springcloud servicio-productos:v1	# Con -P disponibiliza los puertos aleatorios, no es necesario --name ya que no necesitariamos el nombre.

	$ curl http://localhost
```
