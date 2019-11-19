# Creando Red

```
	$ sudo docker network create springcloud	# Crear Red
	$ sudo docker network ls					# Listar Redes	
```

# Generando Dockers y Levantando Dockers, en este orden.

```
	## Docker: springboot-servicio-config
		$ cd springboot-servicio-config
		$ mvn clean compile package
		$ sudo docker build -t config-server:v1 .
		$ sudo docker run -p 8888:8888 --name config-server --network springcloud config-server:v1
	
		$ curl http://localhost:8888/servicio-items/default
		$ curl http://localhost:8888/servicio-items/dev
		$ curl http://localhost:8888/servicio-items/prod
		$ curl http://localhost:8888/servicio-productos/default
		$ curl http://localhost:8888/servicio-productos/dev
		
	## Docker: springboot-servicio-eureka
		$ cd springboot-servicio-eureka
		$ mvn clean compile package
		$ sudo docker build -t eureka-server:v1 .
		$ sudo docker run -p 8761:8761 --name eureka-server --network springcloud eureka-server:v1
		
		$ curl http://localhost:8761/
			
	## Docker: MySQL 
		# sudo docker run -p 3306:3306 --name servicio-mysql --network springcloud -e MYSQL_ROOT_PASSWORD=249861 -e MYSQL_DATABASE=db_spring_cloud -d mysql:8
		$ sudo docker run -p 3306:3306 --name servicio-mysql --network springcloud -e MYSQL_ROOT_PASSWORD=249861 -e MYSQL_DATABASE=db_spring_cloud -d mariadb
		
		$ sudo docker inspect servicio-mysql | grep IP
		$ mysql -u root -h 172.18.0.4 -p
		
	## Docker: Postgres
		# sudo docker run -p 5432:5432 --name servicio-postgres --network springcloud -e POSTGRES_PASSWORD=249861 -e POSTGRES_DB=db_spring_cloud -d postgres:12-alpine
		$ sudo docker run -p 5432:5432 --name servicio-postgres --network springcloud -e POSTGRES_PASSWORD=249861 -e POSTGRES_DB=db_spring_cloud -d postgres

		$ sudo docker inspect servicio-postgres | grep IP
		$ psql -U postgres -h 172.18.0.5

	## Docker: springboot-servicio-productos
		$ cd springboot-servicio-productos
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
		
	## Docker: springboot-servicio-zuul	
		$ cd springboot-servicio-zuul
		$ mvn clean compile package -DskipTests
		$ sudo docker build -t zuul-server:v1 .
		$ sudo docker run -p 8090:8090 --network springcloud zuul-server:v1
		
		$ curl http://localhost:8090/api/productos/listar
		
		### Ver Zuul README endpoints
		$ curl http://localhost:8090/api/productos/listar
		$ curl http://localhost:8090/api/usuarios/usuariocrud
		$ curl http://localhost:8090/api/security/oauth/token
		
	## Docker: springboot-servicio-usuarios
		$ cd springboot-servicio-usuarios
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
		
	### Docker: springboot-servicio-oauth
		$ cd springboot-servicio-oauth
		$ mvn clean compile package -DskipTests
		$ sudo docker build -t servicio-oauth:v1 .
		$ sudo docker run -p 9100:9100 --network springcloud servicio-oauth:v1

		# Ver Zuul README endpoints 
		$ curl http://localhost:8090/api/security/oauth/token
		
	### Docker: springboot-servicio-item
		$ cd springboot-servicio-item
		$ mvn clean compile package -DskipTests
		$ sudo docker build -t servicio-item:v1 .
		$ sudo docker run -p 8002:8002 -p 8005:8005 -p 8007:8007 --network springcloud servicio-item:v1		# Se habilitan esos puertos por los posibles profiles a usar de Spring Config de servicio-items-[default, dev, prod].properties
	
		$ curl http://localhost:8005/listar
	
		# Ver Zuul README endpoints 
		$ curl http://localhost:8090/api/items/listar		

	### Docker: RabbitMQ
		$ sudo docker pull rabbitmq:3.8-management-alpine
		
		$ sudo docker run -p 15672:15672 -p 5672:5672 --name rabbitmq --network springcloud -d rabbitmq:3.8-management-alpine
			# 15672 Puerto consola Web
			# 5672  Puerto broker "Servidor de Rabbit"
			
		$ curl http://localhost:15672
			# Default User: guest
			# Default Pass: guest
		
	### Docker: Zipkin
		$ sudo docker inspect servicio-mysql | grep -i ip		# Buscar la IP del Servidor MySQL desplegado arriba.
		$ mysql -u root -h 172.18.0.2 -p
		mysql> CREATE DATABASE zipkin DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
		mysql> CREATE USER 'zipkin'@'%' IDENTIFIED BY 'zipkin';							-- % Access From All IP, localhost only local machine access.
		mysql> GRANT ALL PRIVILEGES ON zipkin.* TO zipkin@'%' IDENTIFIED BY 'zipkin';	-- DATABASE.TABLE "In this case zipkin.* -> zipkin all Tables".
		mysql> FLUSH PRIVILEGES;
		mysql> quit
		$ mysql -u zipkin -h 172.18.0.2 -pzipkin

		-- Load Script
		# mysql -u user -h hostname database < path/to/test.sql
		$ mysql -u zipkin -h 172.18.0.2 -p zipkin < ./zipkin_script.sql
		
		$ cd ./Zipkin-Server
		$ sudo docker build -t zipkin-server:v1 .
		$ sudo docker run -p 9411:9411 --name zipkin-server --network springcloud -e RABBIT_ADDRESSES=rabbitmq:5672 -e STORAGE_TYPE=mysql -e MYSQL_USER=zipkin -e MYSQL_PASS=zipkin -e MYSQL_HOST=servicio-mysql zipkin-server:v1
			#### NOTA:
				-e RABBIT_ADDRESSES=rabbitmq:5672	Es el nombre dado a la instancia docker de RabbitMQ --name=rabbitmq
				-e MYSQL_HOST=servicio-mysql		Es el nombre dado a la instancia docker de MySQL --name=servicio-mysql
		
		$ curl http://localhost:9411/zipkin			Zipkin
		$ curl http://localhost:15672				RabbitMQ, Debe estar arriba el Docker o Servicio local en Maquina	
		
		#### Levantar servicios en este orden: Config, Eureka, Productos, Items, Zuul. $ sudo docker start ...
		$ curl http://localhost:8090/api/items/listar
		$ curl http://localhost:8090/api/productos/listar
		# Ver las trazas en Zipkin o MySQL
```

# Docker-Compose
```
	## Version docker-compose .yml file de 
		$ curl https://docs.docker.com/compose/compose-file/
	
	$ sudo docker-compose up					Levanta las instancias (En este caso lo levantamos 1 a 1 debido a las dependencias)
	$ sudo docker-compose down -v				Detiene y Elimina la red y todas las instancias del docker-compose.yml
	
	$ sudo docker-compose up -d config-server
	$ sudo docker-compose up -d eureka-server
	$ sudo docker-compose up -d servicio-mysql
	
	$ sudo docker-compose logs -f
		Una vez que en los logs se vea que se levanto MySQL se levanta Productos

	$ sudo docker-compose up -d servicio-productos
	$ sudo docker-compose up -d servicio-item
	$ sudo docker-compose up -d zuul-server
	
	$ curl http://localhost:8090/api/items/listar
	$ curl http://localhost:8090/api/productos/listar
```
