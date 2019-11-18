# Run

```
	$ mvn spring-boot:run
	Or
	$ java -jar target/*-0.0.1-SNAPSHOT.jar
```

# Go to

```
	# Para refrescar los @Componentes que tengan @RefreshScope. Se invoca manualmente esta URL cuado se cambien datos en Spring Cloud Config
	# de esta manera no es necesario Reiniciar el Servicio.
	# Solo funciona para cambiar configuraciones propias de nosotros en Spring Cloud Config. Puerto y configuraciones de Base de Datos estamos 
	# obligados a Reiniciar el Microservicio. Si el Repo Git esta en local no es necesario hacer commit.
	$ curl -X POST http://localhost:8090/api/items/actuator/refresh

### Usuarios
	[GET]
	$ curl http://localhost:8090/api/usuarios/usuariocrud
	
	$ curl http://localhost:8090/api/usuarios/usuariocrud/search/findByUsername?username=16870609
	
	$ curl http://localhost:8090/api/usuarios/usuariocrud/search/buscar-correo?correo=et.magnis@porttitor.co.uk
	
	$ curl http://localhost:8090/api/usuarios/usuariocrud/search/obtenerPorUsername?username=16870609
	
	----------------------------------
	[POST]	http://localhost:8090/api/usuarios/usuariocrud
	Body | Raw | JSON
		
	$ curl -X POST \
	  http://localhost:8090/api/usuarios/usuariocrud \
	  -H 'Content-Type: application/json' \
	  -d '{
	  "username": "kevinxjavier",
	  "password": "123456",
	  "enabled": true,
	  "nombre": "Kevin",
	  "apellido": "Pina",
	  "email": "kevin@kevinpina.com",
	  "roles": [
	    {
	      "id": 1, "nombre": "ROLE_USER"
	    },
	    {
	      "id": 2, "nombre": "ROLE_ADMIN"
	    }
	  ]
	}'
	
	----------------------------------
	[PUT]	http://localhost:8090/api/usuarios/usuariocrud/101
	Body | Raw | JSON
	
	$ curl -X PUT \
	  http://localhost:8090/api/usuarios/usuariocrud/101 \
	  -H 'Content-Type: application/json' \
	  -d '{
	  "username": "kevinpina",
	  "password": "123456",
	  "enabled": true,
	  "nombre": "Kevin J",
	  "apellido": "Pina Calatrava",
	  "email": "aufwacht@hotmail.com",
	  "roles": [
	    {
	      "id": 1, "nombre": "ROLE_USER"
	    }
	  ]
	}'
	
	----------------------------------
	[DELETE]
	$ curl -X DELETE http://localhost:8090/api/usuarios/usuariocrud/102
	
### OAuth
	Nota: "Basic Auth" Genera en los Headers un parametro -H Authorization: Basic ZnJvbnRlbmRhcHA6MTIzNDU2 que es
		el usuario:password encriptado en base64.

		$ echo "ZnJvbnRlbmRhcHA6MTIzNDU2" | base64 --decode
			frontendapp:123456

	- Generar Token	
	[POST]	http://localhost:8090/api/security/oauth/token
	Authorization | Basic Auth 
		Username: frontendapp 
		Password: 123456

		Preview Request | Headers | Temporary Headers
	Body | x-www-form-urlencoded 
		username: 	tarikxdale
		password:	12345
		grant_type:	password

	$ curl -X POST \
	  http://localhost:8090/api/security/oauth/token \
	  -H 'Authorization: Basic ZnJvbnRlbmRhcHA6MTIzNDU2' \
	  -H 'Content-Type: application/x-www-form-urlencoded' \
	  -d 'username=tarikxdale&password=12345&grant_type=password'
	  
  - Refrescar Token
	[POST]	http://localhost:8090/api/security/oauth/token
	Authorization | Basic Auth 
		Username: frontendapp 
		Password: 123456

		Preview Request | Headers | Temporary Headers
  	Body | x-www-form-urlencoded 
		grant_type:	password
		refresh_token: <COLOCAR_EL_TOKEN_refresh_token_QUE_SE_OBTIENE_DE_- Generar Token>

	$ curl -X POST \
	  http://localhost:8090/api/security/oauth/token \
	  -H 'Authorization: Basic ZnJvbnRlbmRhcHA6MTIzNDU=' \
	  -H 'Content-Type: application/x-www-form-urlencoded' \
	  -d 'grant_type=refresh_token&refresh_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJpcmVuZXhwYXRyaWNrIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImFwZWxsaWRvIjoiUGF0cmljayIsImNvcnJlbyI6ImF0QG1hdXJpcy5jb20iLCJhdGkiOiJlNDIwZGRhZi0zZjQxLTRmYWYtYTEyMS01MzUxOWYzZDQyYzYiLCJleHAiOjE1NzI4MjEzMTgsIm5vbWJyZSI6IklyZW5lIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdLCJqdGkiOiIxNGQwZGI5My0wNDQ0LTQ5NWUtYTQ0ZC02NDZmOTlhNmJmZDciLCJjbGllbnRfaWQiOiJmcm9udGVuZGFwcCJ9.L74HWt__zjvAP499sS3UC6eR42vT72QGtymEzoJRCok'
	  
### ZUUL empleando OAuth
	[POST - PUT - DELETE]	http://localhost:8090/api/productos/ver/2		[Ver Ruta en: ResourceServerConfig.java]
	Authorization | Bearer Token
		Token: <COLOCAMOS _EL_TOKEN_GENERADO_CON_http://localhost:8090/api/security/oauth/token>
		
	$ curl -X GET \
	  http://192.168.1.100:8090/api/usuarios/usuariocrud/2 \
	  -H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ0YXJpa3hkYWxlIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImFwZWxsaWRvIjoiRGFsZSIsImNvcnJlbyI6ImV0Lm1hZ25pc0Bwb3J0dGl0b3IuY28udWsiLCJleHAiOjE1NzI0ODg3NDksIm5vbWJyZSI6IlRhcmlrIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjgxNWQ4N2ZlLTBmYWEtNGQ5Yi1iMGQwLWNjZjQzNGU5ODY3MyIsImNsaWVudF9pZCI6ImZyb250ZW5kYXBwIn0.LG9li2v4Bi6pYTxmmz5uOOryHC1tCN-T6AK4GnyvsUI'
		
### Productos

	[GET]
	$ curl http://localhost:8090/api/productos/listar
	$ curl http://localhost:8090/api/productos/ver/1

	----------------------------------
	[POST]	http://192.168.1.100:8090/api/productos/crear
	Body | Raw | JSON

	$ curl -X POST \
	  http://192.168.1.100:8090/api/productos/crear \
	  -H 'Content-Type: application/json' \
	  -d '{
		"nombre": "Xiomi Mi 7",
		"precio": 1500,
		"fechaCreacion": "2019-10-23"
	}'

	----------------------------------
	[PUT]	http://192.168.1.100:8090/api/productos/editar/1
	Body | Raw | JSON

	$ curl -X PUT \
	  http://192.168.1.100:8090/api/productos/editar/1 \
	  -H 'Content-Type: application/json' \
	  -d '{
		"nombre": "Samsung Galaxy 11",
		"precio": 1500,
		"fechaCreacion": "2019-10-23"
	}'

	----------------------------------
	[DELETE]	http://192.168.1.100:8090/api/productos/eliminar/101

	$ curl -X DELETE http://192.168.1.100:8090/api/productos/eliminar/100

### Items

	[GET]
	$ curl http://localhost:8090/api/items/listar
	$ curl http://localhost:8090/api/items/ver/1/cantidad/5
	$ curl http://localhost:8090/api/items/obtener-config

	----------------------------------
	[POST]	http://192.168.1.100:8090/api/items/crear
	Body | Raw | JSON

	$ curl -X POST \
	  http://192.168.1.100:8090/api/items/crear \
	  -H 'Content-Type: application/json' \
	  -d '{
		"id": null,
		"nombre": "Anzel",
		"precio": 1978,
		"fechaCreacion": "2018-10-09"
	}'

	----------------------------------
	[PUT] 	http://192.168.1.100:8090/api/items/editar/101
	Body | Raw | JSON

	$ curl -X PUT \
	  http://192.168.1.100:8090/api/items/editar/101 \
	  -H 'Content-Type: application/json' \
	  -d '{
		"id": null,
		"nombre": "New Anzel",
		"precio": 2000
	}'

	----------------------------------
	[DELETE]	http://192.168.1.100:8090/api/items/eliminar/103

	$ curl -X DELETE http://192.168.1.100:8090/api/items/eliminar/103
```

