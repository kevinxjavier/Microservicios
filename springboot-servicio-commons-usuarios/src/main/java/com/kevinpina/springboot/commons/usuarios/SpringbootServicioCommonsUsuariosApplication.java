package com.kevinpina.springboot.commons.usuarios;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})	// Excluimos la configuracion de Spring Data JPA, para que asi no nos pida la configuracion de una Conexion de DataSource
public class SpringbootServicioCommonsUsuariosApplication {

}
