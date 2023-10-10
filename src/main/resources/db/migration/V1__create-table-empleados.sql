create table empleados(
	id bigint not null auto_increment,
	nombre varchar(100) not null,
	documento varchar(100) not null unique,
	email varchar(100) not null unique,
	cargo varchar(100) not null,
	calle varchar(50),
	numero varchar(20),
	complemento varchar(50),
	
	primary key(id)
	
);