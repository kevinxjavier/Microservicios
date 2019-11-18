FROM openjdk:12

# OPCIONAL: Algunas aplicaciones de Spring la requieren, Tomcat guarda los logs en ese directoriom aunque para esta app
# los logs se imprimen en consola y no en ese direcitorio.
VOLUME /tmp

# No colocamos puerto ya que en application.properties esta defino como Random con server.port = ${PORT:0}
# EXPOSE 8080

ADD ./target/springboot-servicio-usuarios-0.0.1-SNAPSHOT.jar servicio-usuarios.jar

ENTRYPOINT ["java", "-jar", "/servicio-usuarios.jar"]
