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
$ curl http://localhost:

