FROM openjdk:12

# OPCIONAL: Algunas aplicaciones de Spring la requieren, Tomcat guarda los logs en ese directoriom aunque para esta app
# los logs se imprimen en consola y no en ese direcitorio.
VOLUME /tmp

EXPOSE 8090

ADD ./target/springboot-servicio-zuul-0.0.1-SNAPSHOT.jar zuul-server.jar

ENTRYPOINT ["java", "-jar", "/zuul-server.jar"]
