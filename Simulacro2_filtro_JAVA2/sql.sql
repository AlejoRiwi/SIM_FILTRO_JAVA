create database aeropuerto;

use aeropuerto;

create table avion (
	id int primary key auto_increment,
    modelo varchar (10) not null,
    capacidad int not null
);

Insert into avion (modelo,capacidad) Values ("HK-270", 20);
select * from avion;