# URL Spring Initializer

```
- Web - Devtools - JPA - H2
https://start.spring.io/starter.zip?name=springboot-servicio-productos-1&groupId=com.kevinpina.springboot.productos&artifactId=springboot-servicio-productos-1&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot+Properties&packageName=com.kevinpina.springboot.productos&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=h2&dependencies=data-jpa&dependencies=web

- Web - Feign - Ribbon
https://start.spring.io/starter.zip?name=springboot-servicio-item&groupId=com.kevinpina.springboot.item&artifactId=springboot-servicio-item&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.item&type=maven-project&packaging=jar&javaVersion=1.8&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=cloud-feign&dependencies=cloud-ribbon&dependencies=web

- Eureka
https://start.spring.io/starter.zip?name=springboot-servicio-eureka&groupId=com.kevinpina.springboot.eureka&artifactId=springboot-servicio-eureka&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.eureka&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=cloud-eureka-server

- Zuul
https://start.spring.io/starter.zip?name=springboot-servicio-zuul&groupId=com.kevinpina.springboot.zuul&artifactId=springboot-servicio-zuul&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.zuul&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=cloud-eureka&dependencies=cloud-zuul&dependencies=web

- Config
https://start.spring.io/starter.zip?name=springboot-servicio-config&groupId=com.kevinpina.springboot.config&artifactId=springboot-servicio-config&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.config&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=devtools&dependencies=cloud-config-server

- Commons (JPA, H2)
https://start.spring.io/starter.zip?name=springboot-servicio-commons&groupId=com.kevinpina.springboot.commons&artifactId=springboot-servicio-commons&version=0.0.1-SNAPSHOT&description=Demo+project+for+Spring+Boot&packageName=com.kevinpina.springboot.commons&type=maven-project&packaging=jar&javaVersion=11&language=java&bootVersion=2.2.0.RELEASE&dependencies=data-jpa&dependencies=h2
```

# Run

Orden para ejecutar los proyectos

00- springboot-servicio-commons			[Libreria Commons]
	$ mvn install

01- springboot-servicio-config			[Config]
	$ mvn spring-boot:run

02- springboot-servicio-eureka			[Eureka]
	$ mvn spring-boot:run

03- springboot-servicio-productos		[Service, H2, JPA]
	$ java -jar target/springboot-servicio-productos-0.0.1-SNAPSHOT.jar --server.port=8001
	$ java -jar target/springboot-servicio-productos-0.0.1-SNAPSHOT.jar --server.port=9001
	# OR
	$ SERVER_PORT=8001 mvn spring-boot:run
	$ SERVER_PORT=9001 mvn spring-boot:run
	
04- springboot-servicio-item			[RestTemplate - Feign - Ribbon - Hystrix]
	$ mvn spring-boot:run

05-	springboot-servicio-zuul			[Zuul]
	$ mvn spring-boot:run

