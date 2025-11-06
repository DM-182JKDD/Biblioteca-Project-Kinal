-- CENTRO EDUCATIVO TECNICO EDUCATIVO LABORAL
-- informatica - IN4AM
-- curso de taller
-- biblioteca
-- por jose Daniel Escobar Macario
-- uso de snake case para base de datos

-- creacion de la base de datos
drop database if exists bd_biblioteca;
create database if not exists bd_biblioteca;
use bd_biblioteca;

-- creacion de las tablas de la base de datos
create table genero (
    id_genero int not null primary key auto_increment,
    genero varchar(50) unique not null,
    creado datetime default now(),
    actualizado datetime default now() on update now()
) engine = innodb;

create table metodo_de_pago (
    id_metodo_de_pago int not null primary key auto_increment,
    metodo_de_pago varchar(64) not null
) engine = innodb;


create table articulo (
    id_articulo int not null primary key auto_increment,
    nombre_articulo varchar(128) not null,
    precio DECIMAL(6, 2) NOT NULL
) engine = innodb;

create table libro (
    id_articulo int not null primary key,
    autor varchar(128),
    constraint fk_articulo_libro foreign key (id_articulo) references articulo(id_articulo)
) engine = innodb;

create table usuario (
    id_usuario int not null primary key auto_increment,
    id_genero int not null,
    nombre varchar(64) not null,
    apellido varchar(64) not null,
    direccion varchar(128) not null,
    fecha_nacimiento date not null,
    email varchar(64) unique not null,
    contrasenia varchar(128) not null,
    telefono varchar(15) not null,
    creado datetime default now(),
    actualizado datetime default now() on update now(),
    constraint fk_genero_usuario foreign key (id_genero) references genero(id_genero)
) engine = innodb;

create table pago (
    id_pago int not null primary key auto_increment,
    id_metodo_de_pago int not null,
    constraint fk_metodo_de_pago_pago foreign key (id_metodo_de_pago) references metodo_de_pago(id_metodo_de_pago)
) engine = innodb;

create table compra (
    id_compra int not null primary key auto_increment,
    id_usuario int not null,
    id_articulo int not null,
    id_pago int not null,
    cantidad_comprada INT NOT NULL,
    constraint fk_usuario_compra foreign key (id_usuario) references usuario(id_usuario),
    constraint fk_articulo_compra foreign key (id_articulo) references articulo(id_articulo),
    constraint fk_pago_compra foreign key (id_pago) references pago(id_pago)
) engine = innodb;