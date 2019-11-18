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
		

```

