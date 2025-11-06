	-- CENTRO EDUCATIVO TECNICO LABORAL KINAL
	-- INFORMATICA - IN4AM
	-- CURSO DE TALLER
	-- Actividad de la AGENDA
	-- Por Jose Daniel Escobar Macario

	use bd_biblioteca;

	delimiter $$

	delimiter $$

	create procedure sp_guardar_usuario(
		in p_nombre varchar(64),
		in p_apellido varchar(64),
		in p_telefono int,
		in p_genero varchar(50),
		in p_fecha_nacimiento date,
		in p_direccion varchar(128),
		in p_email varchar(64),
		in p_contrasenia varchar(128)
	)
	begin
		declare v_id_genero int;
		select id_genero into v_id_genero
		from genero
		where genero = p_genero
		limit 1;
		
		insert into usuario(
			id_genero,
			nombre,
			apellido,
			fecha_nacimiento,
			direccion,
			email,
			contrasenia,
			telefono,
			creado,
			actualizado
		) values (
			v_id_genero,
			p_nombre,
			p_apellido,
			p_fecha_nacimiento,
			p_direccion,
			p_email,
			md5(p_contrasenia),
			p_telefono,
			now(),
			now()
		);

		select last_insert_id() as id_usuario;
	end$$

	delimiter ;

	DELIMITER //
	CREATE PROCEDURE sp_buscar_genero()
	BEGIN
		SELECT * FROM genero;
	END //
	DELIMITER ;

	DELIMITER //
	CREATE PROCEDURE sp_buscar_metodo_de_pago()
	BEGIN
		SELECT id_metodo_de_pago, metodo_de_pago FROM metodo_de_pago;
	END //
	DELIMITER ;

	delimiter $$

	create procedure sp_iniciar_sesion(
		in p_email varchar(64),
		in p_contrasenia varchar(128)
	)
	begin
		select 
			u.id_usuario,
			u.email,
			u.contrasenia,
			u.telefono,
			u.nombre,
			u.apellido,
			g.genero,
			u.fecha_nacimiento,
			u.direccion
		from usuario u
		inner join genero g on u.id_genero = g.id_genero
		where u.email = p_email
		  and u.contrasenia = md5(p_contrasenia)
		limit 1;
	end$$
	delimiter ;

	DELIMITER //
	CREATE PROCEDURE sp_leer_libros()
	BEGIN
		SELECT 
			a.id_articulo,
			a.nombre_articulo,
			a.precio,
			l.autor
		FROM 
			articulo a
		INNER JOIN 
			libro l ON a.id_articulo = l.id_articulo;
	END //
	DELIMITER ;
  
 DELIMITER //

DELIMITER //

CREATE PROCEDURE sp_guardar_compra(
    IN p_id_usuario INT,
    IN p_id_articulo INT,
    IN p_id_pago INT,
    IN p_cantidad_comprada INT
)
BEGIN
    INSERT INTO compra (id_usuario, id_articulo, id_pago, cantidad_comprada)
    VALUES (p_id_usuario, p_id_articulo, p_id_pago, p_cantidad_comprada);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_registrar_pago(
    IN p_id_metodo_de_pago INT,
    OUT p_id_pago INT
)
BEGIN
    INSERT INTO pago (id_metodo_de_pago)
    VALUES (p_id_metodo_de_pago);
    SET p_id_pago = LAST_INSERT_ID();
END //

DELIMITER ;



