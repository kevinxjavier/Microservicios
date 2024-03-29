# URL Spring Initializer

```
- Web - Devtools - JPA - H2
https://start.spring.io/starter.zip?name=springboot-servicio-productos-1&groupId=com.kevinpina.springboot&artifactId=springboot-servicio-productos-1&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot+Properties&packageName=com.kevinpina.springboot.productos&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=h2&dependencies=data-jpa&dependencies=web
	Se agrego luego: &dependencies=mysql&dependencies=cloud-config-client&dependencies=amqp
	Se agrego luego: &dependencies=cloud-starter-sleuth luego esta se quito para usar &dependencies=cloud-starter-zipkin que contempla las 2	

- Web - Feign - Ribbon
https://start.spring.io/starter.zip?name=springboot-servicio-item&groupId=com.kevinpina.springboot&artifactId=springboot-servicio-item&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.item&type=maven-project&packaging=jar&javaVersion=1.8&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=cloud-feign&dependencies=cloud-ribbon&dependencies=web
	Se agrego luego: &dependencies=amqp
	Se agrego luego: &dependencies=cloud-starter-sleuth luego esta se quito para usar &dependencies=cloud-starter-zipkin que contempla las 2
	
- Eureka
https://start.spring.io/starter.zip?name=springboot-servicio-eureka&groupId=com.kevinpina.springboot&artifactId=springboot-servicio-eureka&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.eureka&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=cloud-eureka-server

- Zuul
https://start.spring.io/starter.zip?name=springboot-servicio-zuul&groupId=com.kevinpina.springboot&artifactId=springboot-servicio-zuul&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.zuul&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=cloud-eureka&dependencies=cloud-zuul&dependencies=web
	Se agrego luego: &dependencies=cloud-oauth2&dependencies=cloud-config-client&dependencies=amqp
	Se agrego luego: &dependencies=cloud-starter-sleuth luego esta se quito para usar &dependencies=cloud-starter-zipkin que contempla las 2

- Config
https://start.spring.io/starter.zip?name=springboot-servicio-config&groupId=com.kevinpina.springboot&artifactId=springboot-servicio-config&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.config&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=cloud-config-server

- OAuth2 (OAuth2 contiene a Spring Security)
https://start.spring.io/starter.zip?name=springboot-servicio-oauth&groupId=com.kevinpina.springboot&artifactId=springboot-servicio-oauth&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.oauth&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=cloud-eureka&dependencies=cloud-feign&dependencies=cloud-oauth2&dependencies=web
	Se agrego luego: &dependencies=cloud-config-client&dependencies=amqp
	Se agrego luego: &dependencies=cloud-starter-sleuth luego esta se quito para usar &dependencies=cloud-starter-zipkin que contempla las 2

- Commons (JPA, H2)
https://start.spring.io/starter.zip?name=springboot-servicio-commons&groupId=com.kevinpina.springboot&artifactId=springboot-servicio-commons&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.commons&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=data-jpa&dependencies=h2

- Commons (JPA)
https://start.spring.io/starter.zip?name=springboot-servicio-commons-usuarios&groupId=com.kevinpina.springboot&artifactId=springboot-servicio-commons-usuarios&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.commons.usuarios&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=data-jpa

- Rest Repository
https://start.spring.io/starter.zip?name=springboot-servicio.usuarios&groupId=com.kevinpina.springboot&artifactId=springboot-servicio.usuarios&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.usuarios&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=data-rest&dependencies=data-jpa&dependencies=h2&dependencies=cloud-eureka&dependencies=web
	Se agrego luego: &dependencies=cloud-config-client&dependencies=postgresql&dependencies=amqp
	Se agrego luego: &dependencies=cloud-starter-sleuth luego esta se quito para usar &dependencies=cloud-starter-zipkin que contempla las 2

```

# Init
	Crear el usuario, schema y cargar los scripts de schema_zipkin.sql en MySQL.
	Luego de levantar MySQL levantar RabbitMQ y por ultimo Zipkin.

# Start RabbitMQ
```
	## Simplemente Instalar RabbitMQ
	$ sudo rabbitmq-server start
	$ curl http://localhost:15672/
```

# Start Zipkin
```
	## Ver ../Zipkin-Server/Zipkin Server Install.txt
	$ sh ./Zipkin-RabbitMQ.sh			# Zipkin solo RabbitMQ ejecutandose
	$ sh ./Zipkin-RabbitMQ-MySQL.sh		# Zipkin solo RabbitMQ y MySQL ejecutandose
		Crear Schema primero: schema_zipkin.sql
	$ curl http://localhost:9411/
```

# Run

Orden para ejecutar los proyectos

00- springboot-servicio-commons			[Libreria Commons]
	$ mvn install
	
01- springboot-servicio-commons-usuarios[Libreria Commons]
	$ mvn install

02- springboot-servicio-config			[Config]
	$ mvn spring-boot:run

03- springboot-servicio-eureka			[Eureka]
	$ mvn spring-boot:run

04- springboot-servicio-productos		[Service - H2 - JPA]
	$ java -jar target/springboot-servicio-productos-0.0.1-SNAPSHOT.jar --server.port=8001
	$ java -jar target/springboot-servicio-productos-0.0.1-SNAPSHOT.jar --server.port=9001
	# OR
	$ SERVER_PORT=8001 mvn spring-boot:run
	$ SERVER_PORT=9001 mvn spring-boot:run
	
05- springboot-servicio-item			[Service - RestTemplate - Feign - Ribbon - Hystrix]
	$ mvn spring-boot:run
	
06- springboot-servicio-usuarios		[Service - Rest Repository]
	$ mvn spring-boot:run

07- springboot-servicio-oauth			[Service - OAuth2 - Feign]
	$ mvn spring-boot:run

08-	springboot-servicio-zuul			[Zuul]
	$ mvn spring-boot:run

