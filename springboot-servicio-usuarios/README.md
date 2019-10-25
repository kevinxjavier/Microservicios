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
```
Or
```
$ java -jar target/*-0.0.1-SNAPSHOT.jar
```

# Go to
```
[GET]
$ curl http://192.168.1.100:[PORT]/usuariocrud
$ curl http://192.168.1.100:[PORT]/usuariocrud/1

----------------------------------
[POST]	http://localhost:8090/api/usuarios/usuariocrud
Body | Raw | JSON
	
$ curl -X POST \
  http://localhost:8090/api/usuarios/usuariocrud \
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
[PUT]	http://localhost:8090/api/usuarios/usuariocrud/101
Body | Raw | JSON

$ curl -X PUT \
  http://localhost:8090/api/usuarios/usuariocrud/101 \
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
[DELETE]	http://localhost:8090/api/usuarios/usuariocrud/102

