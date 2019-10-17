# Database 
	
Este archivo se ejecuta automaticamente al levantar el servicio. Solo hay INSERTS.

```
	$ ls src/main/resource/import.sql
```

## SQL Adicional	

```
DROP TABLE `productos`;

CREATE TABLE `productos` (
  `id` mediumint(8) unsigned NOT NULL auto_increment,
  `nombre` varchar(255) default NULL,
  `precio` mediumint default NULL,
  `fecha_creacion` TEXT default NULL,
  PRIMARY KEY (`id`)
) AUTO_INCREMENT=1;
```
