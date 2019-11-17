# Creando Red
```
	$ sudo docker network create springcloud	# Crear Red
	$ sudo docker network ls					# Listar Redes	
```

# Generando Dockers
```
	## springboot-servicio-config
		$ cd springboot-servicio-config
		$ sudo docker build -t config-server:v1 .
		$ sudo docker run -p 8888:8888 --name config-server --network springcloud config-server:v1
	
		$ curl http://localhost:8888/servicio-items/default	   
		$ curl http://localhost:8888/servicio-items/dev
		$ curl http://localhost:8888/servicio-items/prod
		$ curl http://localhost:8888/servicio-productos/default
		$ curl http://localhost:8888/servicio-productos/dev
```

