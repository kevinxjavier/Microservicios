# Creando Red

```
	$ sudo docker network create springcloud	# Crear Red
	$ sudo docker network ls					# Listar Redes	
```

# Generando Dockers, en este orden.

```
	## springboot-servicio-config
		$ cd springboot-servicio-config
		$ mvn clean compile package
		$ sudo docker build -t config-server:v1 .
		$ sudo docker run -p 8888:8888 --name config-server --network springcloud config-server:v1
	
		$ curl http://localhost:8888/servicio-items/default
		$ curl http://localhost:8888/servicio-items/dev
		$ curl http://localhost:8888/servicio-items/prod
		$ curl http://localhost:8888/servicio-productos/default
		$ curl http://localhost:8888/servicio-productos/dev
		
	## springboot-servicio-eureka
		$ mvn clean compile package
		$ sudo docker build -t eureka-server:v1 .
		$ sudo docker run -p 8761:8761 --name eureka-server --network springcloud eureka-server:v1
		
		$ curl http://localhost:8761/
			
	## MySQL 
		# sudo docker run -p 3306:3306 --name servicio-mysql --network springcloud -e MYSQL_ROOT_PASSWORD=249861 -e MYSQL_DATABASE=db_spring_cloud -d mysql:8
		$ sudo docker run -p 3306:3306 --name servicio-mysql --network springcloud -e MYSQL_ROOT_PASSWORD=249861 -e MYSQL_DATABASE=db_spring_cloud -d mariadb
		
		$ sudo docker inspect servicio-mysql | grep IP
		$ mysql -u root -h 172.18.0.4 -p
		
	## Postgres
		# sudo docker run -p 5432:5432 --name servicio-postgres --network springcloud -e POSTGRES_PASSWORD=249861 -e POSTGRES_DB=db_spring_cloud -d postgres:12-alpine
		$ sudo docker run -p 5432:5432 --name servicio-postgres --network springcloud -e POSTGRES_PASSWORD=249861 -e POSTGRES_DB=db_spring_cloud -d postgres

		$ sudo docker inspect servicio-postgres | grep IP
		$ psql -U postgres -h 172.18.0.5

	## springboot-servicio-productos
		$ mvn clean compile package	# Evitando los Tests: $ mvn clean compile package -DskipTests
		$ sudo docker build -t servicio-productos:v1 .
		# sudo docker run -P --name servicio-productos --network springcloud servicio-productos:v1
		$ sudo docker run -P --network springcloud servicio-productos:v1	# Con -P disponibiliza los puertos aleatorios, no es necesario --name ya que no necesitariamos el nombre.
		
		$ sudo docker run -P --network springcloud servicio-productos:v1	# Podemos ejecutarlo varias veces mas sin problema ya que el puerto es dinamico y Eureka Balancea automaticamente.
	
		$ sudo docker exec -it [ID_CONTAINER] bash
			$ cat /etc/hosts	# Aca se averigua la IP, el puerto con el servicio Eureka: posicionando el Mouse sobre el link.
		$ curl http://172.18.0.6:40933/listar
		
		### Ver Zuul README endpoints
		$ curl http://localhost:8090/api/productos/listar
		
	## springboot-servicio-zuul	
		$ mvn clean compile package -DskipTests
		$ sudo docker build -t zuul-server:v1 .
		$ sudo docker run -p 8090:8090 --network springcloud zuul-server:v1
		
		$ curl http://localhost:8090/api/productos/listar
		
		### Ver Zuul README endpoints
		$ curl http://localhost:8090/api/productos/listar
		$ curl http://localhost:8090/api/usuarios/usuariocrud
		$ curl http://localhost:8090/api/security/oauth/token
		
	## springboot-servicio-usuarios
		$ mvn clean compile package	# Evitando los Tests: $ mvn clean compile package -DskipTests
		$ sudo docker build -t servicio-usuarios:v1 .
		# sudo docker run -P --name servicio-usuarios --network springcloud servicio-usuarios
		$ sudo docker run -P --network springcloud servicio-usuarios:v1		# Con -P disponibiliza los puertos aleatorios, no es necesario --name ya que no necesitariamos el nombre.
		
		$ sudo docker run -P --network springcloud servicio-usuarios:v1		# Podemos ejecutarlo varias veces mas sin problema ya que el puerto es dinamico y Eureka Balancea automaticamente.
		
		$ sudo docker exec -it [ID_CONTAINER] bash
			$ cat /etc/hosts	# Aca se averigua la IP, el puerto con el servicio Eureka: posicionando el Mouse sobre el link.
		$ curl http://172.18.0.8:34811/usuariocrud
	
		### Ver Zuul README endpoints
		$ curl http://localhost:8090/api/usuarios/usuariocrud
		
	### springboot-servicio-oauth
		$ mvn clean compile package -DskipTests
		$ sudo docker build -t servicio-oauth:v1 .
		$ sudo docker run -p 9100:9100 --network springcloud servicio-oauth:v1

		# Ver Zuul README endpoints 
		$ curl http://localhost:8090/api/security/oauth/token
		
	### springboot-servicio-item
		$ mvn clean compile package -DskipTests
		$ sudo docker build -t servicio-item:v1 .
		$ sudo docker run -p 8002:8002 -p 8005:8005 -p 8007:8007 --network springcloud servicio-item:v1		# Se habilitan esos puertos por los posibles profiles a usar de Spring Config de servicio-items-[default, dev, prod].properties
	
		$ curl http://localhost:8005/listar
	
		# Ver Zuul README endpoints 
		$ curl http://localhost:8090/api/items/listar		

	### RabbitMQ
		$ sudo docker pull rabbitmq:3.8-management-alpine
		
		$ sudo docker run -p 15672:15672 -p 5672:5672 --name rabbitmq --network springcloud -d rabbitmq:3.8-management-alpine
			# 15672 Puerto consola Web
			# 5672  Puerto broker "Servidor de Rabbit"
			
		$ curl http://localhost:15672
			# Default User: guest
			# Default Pass: guest
		
```

