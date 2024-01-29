create database sgm;
use sgm;
create table userr(
id_userr varchar(15) not null,
email varchar(80) not null,
clave_userr varchar(50) not null,
rol varchar (10) not null,
constraint userr_pk primary key(id_userr));
create table paciente(
id_paciente varchar(15) not null,
nombre_paciente varchar(50) not null,
apellido_paciente varchar(50) not null,
sexo VARCHAR(10) not null,
fecha_nacimiento date not null,
id_userr varchar(15) not null,
constraint paciente_pk primary key(id_paciente),
constraint paciente_id_userr_fk foreign key(id_userr) references userr(id_userr));
create table medico(
id_medico varchar(15) not null,
nombre_medico varchar(50) not null,
apellido_medico varchar(50) not null,
especialidad VARCHAR(50) not null,
id_userr varchar(15) not null,
constraint medico_pk primary key(id_medico),
constraint medico_id_userr_fk foreign key(id_userr) references userr(id_userr));
create table cita(
id_cita INT auto_increment not null,
fecha_hora DATETIME not null,
observacion varchar(255),
estado varchar(10) not null,
id_medico varchar(15) not null,
id_paciente varchar(15) not null,
constraint cita_pk primary key(id_cita),
constraint cita_id_paciente_fk foreign key(id_paciente) references paciente(id_paciente),
constraint cita_id_medico_fk foreign key(id_medico) references medico(id_medico));
create table administrador(
id_administrador varchar(15) not null,
nombre_administrador varchar(80) not null,
clave_administrador varchar(50) not null,
constraint administrador_pk primary key(id_administrador));
insert into administrador(id_administrador,nombre_administrador,clave_administrador) values('1','Admin','7c4a8d09ca3762af61e59520943dc26494f8941b');
insert into userr(id_userr,clave_userr,email,rol) VALUES ("1","3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d","paciente1@gmail.com","P");
insert into userr(id_userr,clave_userr,email,rol) VALUES ("2","273a0c7bd3c679ba9a6f5d99078e36e85d02b952","paciente2@gmail.com","P");
insert into userr(id_userr,clave_userr,email,rol) VALUES ("3","77bce9fb18f977ea576bbcd143b2b521073f0cd6","paciente3@gmail.com","P");
insert into userr(id_userr,clave_userr,email,rol) VALUES ("4","42cfe854913594fe572cb9712a188e829830291f","medico1@gmail.com","M");
insert into userr(id_userr,clave_userr,email,rol) VALUES ("5","b7c40b9c66bc88d38a59e554c639d743e77f1b65","medico2@gmail.com","M");
insert into userr(id_userr,clave_userr,email,rol) VALUES ("6","1411678a0b9e25ee2f7c8b2f7ac92b6a74b3f9c5","medico3@gmail.com","M");
insert into paciente(id_paciente, nombre_paciente, apellido_paciente, sexo, fecha_nacimiento, id_userr) VALUES ("1", "Peter", "Parker", "M", "1988-09-09","1");
insert into paciente(id_paciente, nombre_paciente, apellido_paciente, sexo, fecha_nacimiento, id_userr) VALUES ("2", "Joseph", "Joestar", "M", "1965-03-05","2");
insert into paciente(id_paciente, nombre_paciente, apellido_paciente, sexo, fecha_nacimiento, id_userr) VALUES ("3", "Jolyne", "Cruz", "F", "1999-05-13","3");
insert into medico(id_medico, nombre_medico, apellido_medico, especialidad, id_userr) VALUES ("1", "Dio", "Brando", "Medicina General","4");
insert into medico(id_medico, nombre_medico, apellido_medico, especialidad, id_userr) VALUES ("2", "Wally", "West", "Radiologia","5");
insert into cita(fecha_hora, observacion, estado , id_medico, id_paciente) VALUES ("2022-12-28", null, "A", "1", "1");
insert into cita(fecha_hora, observacion, estado , id_medico, id_paciente) VALUES ("2023-01-05", null, "A", "2", "1");
insert into cita(fecha_hora, observacion, estado , id_medico, id_paciente) VALUES ("2023-01-05", null, "C", "2", "1");
insert into cita(fecha_hora, observacion, estado , id_medico, id_paciente) 
VALUES ("2022-08-16", "Texto de prueba de una observación hecha por un médico. Uno. Dos. Tres. Cuatro. Cinco. Seis. Siete. Ocho. 
Nueve. Diez. Once. Doce. Trece. Catorce. Quince. Dieciséis. Diecisiete. Dieciocho. Diecinueve. Veinte. Veintiuno. Veintidos. Veintitres.
", "F", "1", "1");
insert into cita(fecha_hora, observacion, estado , id_medico, id_paciente) VALUES ("2022-12-18", null, "A", "1", "2");
insert into cita(fecha_hora, observacion, estado , id_medico, id_paciente) VALUES ("2022-12-08", null, "A", "1", "3");
