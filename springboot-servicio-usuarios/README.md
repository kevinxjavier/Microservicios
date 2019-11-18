# Database 
	
	Este archivo se ejecuta automaticamente al levantar el servicio. Solo hay INSERTS.

```
	$ ls src/main/resource/import.sql
```

## SQL Adicional	

```
	-- No es necesario ya que se crea dinamicamente por JPA
	
	DROP TABLE `usuarios`;
	
	CREATE TABLE `usuarios` (
	  `id` mediumint(8) unsigned NOT NULL auto_increment,
	  `username` varchar(13) default NULL,
	  `password` varchar(255),
	  `enabled` TEXT default NULL,
	  `nombre` varchar(255) default NULL,
	  `apellido` varchar(255) default NULL,
	  `email` varchar(255) default NULL,
	  PRIMARY KEY (`id`)
	) AUTO_INCREMENT=1;
```

# Run

```
	$ mvn spring-boot:run
	Or
	$ java -jar target/*-0.0.1-SNAPSHOT.jar
```

# Go to

```
[GET]
	$ curl http://localhost:[PORT]/usuariocrud
	$ curl http://localhost:[PORT]/usuariocrud/1
	
	$ curl http://localhost:[PORT]/usuariocrud/search/findByUsername?username=16870609
	
	$ curl http://localhost:[PORT]/usuariocrud/search/buscar-correo?correo=et.magnis@porttitor.co.uk
	
	$ curl http://localhost:[PORT]/usuariocrud/search/obtenerPorUsername?username=16870609

----------------------------------
[POST]	http://localhost:[PORT]/usuariocrud
Body | Raw | JSON
	
	$ curl -X POST \
	  http://localhost:[PORT]/usuariocrud \
	  -H 'Content-Type: application/json' \
	  -d '{
	  "username": "kevinxjavier",
	  "password": "123456",
	  "enabled": true,
	  "nombre": "Kevin",
	  "apellido": "Pina",
	  "email": "kevin@kevinpina.com",
	  "roles": [
	    {
	      "id": 1, "nombre": "ROLE_USER"
	    },
	    {
	      "id": 2, "nombre": "ROLE_ADMIN"
	    }
	  ]
	}'

----------------------------------
[PUT]	http://localhost:[PORT]/usuariocrud/101
Body | Raw | JSON

	$ curl -X PUT \
	  http://localhost:[PORT]/usuariocrud/101 \
	  -H 'Content-Type: application/json' \
	  -d '{
	  "username": "kevinpina",
	  "password": "123456",
	  "enabled": true,
	  "nombre": "Kevin J",
	  "apellido": "Pina Calatrava",
	  "email": "aufwacht@hotmail.com",
	  "roles": [
	    {
	      "id": 1, "nombre": "ROLE_USER"
	    }
	  ]
	}'

----------------------------------
[DELETE]
	$ curl -X DELETE http://localhost:[PORT]/usuariocrud/102
```

# Docker

```
	$ mvn clean compile package	# Evitando los Tests: $ mvn clean compile package -DskipTests
	$ sudo docker build -t servicio-usuarios:v1 .
	# sudo docker run -P --name servicio-usuarios --network springcloud servicio-usuarios
	$ sudo docker run -P --network springcloud servicio-usuarios:v1		# Con -P disponibiliza los puertos aleatorios, no es necesario --name ya que no necesitariamos el nombre.

	$ sudo docker run -P --network springcloud servicio-usuarios:v1		# Podemos ejecutarlo varias veces mas sin problema ya que el puerto es dinamico y Eureka Balancea automaticamente.
	
	$ sudo docker exec -it [ID_CONTAINER] bash
		$ cat /etc/hosts	# Aca se averigua la IP, el puerto con el servicio Eureka: posicionando el Mouse sobre el link.
	$ curl http://172.18.0.8:34811/usuariocrud
		
	# Ver Zuul README endpoints
	$ curl http://localhost:8090/api/usuarios/usuariocrud
```