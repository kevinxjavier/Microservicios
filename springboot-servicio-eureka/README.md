# Run

```
	$ mvn spring-boot:run
	Or
	$ java -jar target/*-0.0.1-SNAPSHOT.jar
```

# Go to

```
	$ curl http://localhost:8761
```

# Docker

```
	$ mvn clean compile package
	$ sudo docker build -t eureka-server:v1 .
	$ sudo docker run -p 8761:8761 --name eureka-server --network springcloud eureka-server:v1
	
	$ curl http://localhost:8761/
```
