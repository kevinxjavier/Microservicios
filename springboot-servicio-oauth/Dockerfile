FROM openjdk:12

# OPCIONAL: Algunas aplicaciones de Spring la requieren, Tomcat guarda los logs en ese directoriom aunque para esta app
# los logs se imprimen en consola y no en ese direcitorio.
VOLUME /tmp

EXPOSE 9100

ADD ./target/springboot-servicio-oauth-0.0.1-SNAPSHOT.jar servicio-oauth.jar

ENTRYPOINT ["java", "-jar", "/servicio-oauth.jar"]
