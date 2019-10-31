# Run

```
$ mvn spring-boot:run
```
Or
```
$ java -jar target/*-0.0.1-SNAPSHOT.jar
```

# Go to

```
----------------------------------
[POST]	ttp://localhost:9100/oauth/token
Authorization | Basic Auth 
	Username: frontendapp 
	Password: 123456

	Preview Request | Headers | Temporary Headers
Body | x-www-form-urlencoded 
	username: 	tarikxdale
	password:	12345
	grant_type:	password

$ curl -X POST \
  http://localhost:9100/oauth/token \
  -H 'Authorization: Basic ZnJvbnRlbmRhcHA6MTIzNDU2' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  -d 'username=tarikxdale&password=12345&grant_type=password'
```

