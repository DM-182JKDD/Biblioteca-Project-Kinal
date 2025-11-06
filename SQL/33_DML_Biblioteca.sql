-- CENTRO EDUCATIVO TECNICO LABORAL KINAL
-- INFORMATICA - IN4AM
-- CURSO DE TALLER
-- Actividad de la AGENDA
-- Por Jose Daniel Escobar Macario

use bd_biblioteca;

describe genero;
insert into genero(genero) values ('Masculino');
insert into genero(genero) values ('Femenino');

describe metodo_de_pago;
insert into metodo_de_pago(metodo_de_pago) values ('Tarjeta de Credito o Debito');
insert into metodo_de_pago(metodo_de_pago) values ('Efectivo');

insert into articulo (nombre_articulo, precio) values 
('El Principito', 12.50),
('Cien años de soledad', 18.99),
('Hábitos Atómicos', 21.00),
('1984', 14.50),
('Matar un ruiseñor', 16.00),
('Don Quijote de la Mancha', 13.50),
('Padre Rico, Padre Pobre', 19.99),
('Rompe la barrera del NO', 22.50),
('Ficción Juvenil del Momento', 23.00),
('El arte de la guerra', 9.99),
('El Alquimista', 15.00),
('Donde los árboles cantan', 17.50);

insert into libro (id_articulo, autor) values 
(1, 'Antoine de Saint-Exupéry'),
(2, 'Gabriel García Márquez'),
(3, 'James Clear'),
(4, 'George Orwell'),
(5, 'Harper Lee'),
(6, 'Miguel de Cervantes'),
(7, 'Robert Kiyosaki'),
(8, 'Chris Voss'),
(9, 'Autor Popular'),
(10, 'Sun Tzu'),
(11, 'Paulo Coelho'),
(12, 'Laura Gallego');
