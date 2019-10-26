INSERT INTO usuarios (username,password,enabled,nombre,apellido,email) VALUES ('16870609','GXU32DLA6LS',1,'Tarik','Dale','et.magnis@porttitor.co.uk'),('16461029 1298','TPH47FKM9CO',1,'Irene','Patrick','at@mauris.com'),('16891023','MEF26HOV9JM',1,'Avye','Nieves','elementum@eteros.co.uk'),('16851117 1418','TCK04ALD2BI',1,'Hanae','Murphy','vitae.aliquet.nec@suscipitest.ca'),('16260129','HJZ71YBP4ID',1,'Lance','Valenzuela','dictum.eleifend@urna.net'),('16220814','OUJ21ZFC7WE',1,'Amity','Wooten','non.bibendum.sed@quamvel.com'),('16830726','LLH43EWY2DI',1,'Keaton','Jacobs','neque.In.ornare@magnaPhasellus.ca'),('16830116','WJX23UFE6VK',1,'Ainsley','Knowles','molestie.Sed@ligulaNullam.org'),('16400717','HVQ09EDP9HZ',1,'Victor','Langley','Nulla.eget@augue.org'),('16030224','JUZ21LEW9OM',1,'Kelsey','Petersen','purus.in.molestie@Aliquamerat.com');

INSERT INTO rol (nombre) VALUES ('ROLE_USER');
INSERT INTO rol (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO usuarios_to_roles (id_usuario, id_rol) VALUES (1, 1);
INSERT INTO usuarios_to_roles (id_usuario, id_rol) VALUES (2, 1);
INSERT INTO usuarios_to_roles (id_usuario, id_rol) VALUES (2, 2);
