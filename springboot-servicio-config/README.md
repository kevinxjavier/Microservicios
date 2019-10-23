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
$ curl http://localhost:8888/servicio-items/default
$ curl http://localhost:8888/servicio-items/dev

```

# Nota 
Se puede ejecutar sin haber ejecutado Eureka, Zuul y servicios SpringBoots. 

Este Microservicio levanta la configuracion en la Nube (GIT, SVN, HashiCorp Vault) para los 
otros microservicios descritos en el Repo con la nomenclatura [NOMBRE-SERVICIO].properties

Si el server.port esta definido en el application.properties de un servicio SpringBoot y el
servicio Spring Cloud Config tambien define el server.port predomina la configuracion de 
servicio Spring Cloud Config sobreescribiendo la configuracion del servicio SpringBoot.
