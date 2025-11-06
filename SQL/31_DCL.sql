-- CENTRO EDUCATIVO TECNICO LABORAL KINAL
-- Informatica
-- IN4AM
-- CURSO DE TALLER 
-- Tema: Proyecto Final U4
-- Por: Jose Daniel Escobar Macario 
drop database academia;
create database if not exists academia;
use academia;

-- USUARIOS
create user desarrollador identified by 'clave_desarrollador';
create user digitador identified by 'clave_digitador';
create user consultor identified by 'clave_consultor';
create user administrador identified by 'clave_administrador';

-- PRIVILEGIOS
grant all privileges on academia.* to desarrollador;
grant insert, update on academia.* to digitador;
grant select on academia.* to consultor;
grant all privileges on academia.* to administrador with grant option;
