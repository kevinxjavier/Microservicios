# Init 

	Se debe configurar un Repo GIT puede ser local o remoto

	## Repositorio Git Local
	spring.cloud.config.server.git.uri=file:///home/kevin/Documentos/workspace-spring-tool-suite-4-4.4.0.RELEASE/Microservicios
	## Repositorio Git Remoto
	spring.cloud.config.server.git.uri=https://github.com/kevinxjavier/Microservicios
	
	# Si el Repo es privado agregar estas lineas
	spring.cloud.config.server.git.username=kevinxjavier
	spring.cloud.config.server.git.password==mipassword

# Run

```
	$ mvn spring-boot:run
	Or
	$ java -jar target/*-0.0.1-SNAPSHOT.jar
```

# Go to

```
	$ curl http://localhost:8888/servicio-items/default	   
	$ curl http://localhost:8888/servicio-items/dev
	$ curl http://localhost:8888/servicio-items/prod
	$ curl http://localhost:8888/servicio-productos/default
	$ curl http://localhost:8888/servicio-productos/dev
```

# Nota 
	Se puede ejecutar sin haber ejecutado Eureka, Zuul y servicios SpringBoots. 
	
	Este Microservicio levanta la configuracion en la Nube (GIT, SVN, HashiCorp Vault) para los 
	otros microservicios descritos en el Repo con la nomenclatura [NOMBRE-SERVICIO].properties
	que en este caso para fines practicos se colocaron en la raiz Microservios/*.properties
	
	Si el server.port esta definido en el application.properties de un servicio SpringBoot y el
	servicio Spring Cloud Config tambien define el server.port predomina la configuracion de 
	servicio Spring Cloud Config sobreescribiendo la configuracion del servicio SpringBoot.

# Docker

```
	$ mvn clean compile package
	$ sudo docker build -t config-server:v1 .
	$ sudo docker run -p 8888:8888 --name config-server --network springcloud config-server:v1

	$ curl http://localhost:8888/servicio-items/default	   
	$ curl http://localhost:8888/servicio-items/dev
	$ curl http://localhost:8888/servicio-items/prod
	$ curl http://localhost:8888/servicio-productos/default
	$ curl http://localhost:8888/servicio-productos/dev
```
