FROM openjdk:12

# OPCIONAL: Algunas aplicaciones de Spring la requieren, Tomcat guarda los logs en ese directoriom aunque para esta app
# los logs se imprimen en consola y no en ese direcitorio.
VOLUME /tmp

EXPOSE 8002

ADD ./target/springboot-servicio-item-0.0.1-SNAPSHOT.jar servicio-item.jar

ENTRYPOINT ["java", "-jar", "/servicio-item.jar"]
