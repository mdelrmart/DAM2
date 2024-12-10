-- a)
DELIMITER $$

CREATE PROCEDURE pr_CambioDomicilio (IN NSSempregado VARCHAR(10), IN calle VARCHAR(100), IN numero INT, IN numPiso VARCHAR(5), IN codPostal INT, IN localidad VARCHAR(30))

BEGIN
    UPDATE empregado
    SET Rua = calle, Numero_rua = numero, Piso = numPiso, CP = codPostal, Localidade = localidad
    WHERE NSS = NSSempregado;
END $$

DELIMITER ;

-- b)
DELIMITER $$

CREATE PROCEDURE pr_DatosProxectos (IN numProyecto INT, OUT p_nomeProxecto VARCHAR(100), OUT p_Lugar VARCHAR(50), OUT p_Num_departamento_controla INT)

BEGIN
    SELECT Nome_proxecto, Lugar, Num_departamento_controla
    INTO p_nomeProxecto, p_Lugar, p_Num_departamento_controla
    FROM proxecto
    WHERE Num_proxecto = numProyecto;
END $$

DELIMITER ;

-- c)
DELIMITER $$

CREATE PROCEDURE pr_DepartControlaProxec (IN numProyectos INT)
BEGIN
    SELECT d.Num_departamento, d.Nome_departamento, d.NSS_dirige, d.Data_direccion
    -- INTO p_numDepartamento, p_nomeDepartamento, p_NSSdirige, p_dataDireccion
    FROM departamento d
    INNER JOIN proxecto p
    ON d.Num_departamento = p.Num_departamento_controla
    GROUP BY d.Num_departamento
    HAVING COUNT(d.Num_departamento) >= numProyectos;
END $$

DELIMITER ;

-- d)
DELIMITER $$

CREATE FUNCTION fn_nEmpDepart(nomeDepartamento VARCHAR(25))
RETURNS INT
DETERMINISTIC
BEGIN
    DECLARE total_empleados INT;

    SELECT COUNT(e.Num_departamento_pertenece)
    INTO total_empleados
    FROM empregado e
    INNER JOIN departamento d
    ON e.Num_departamento_pertenece=d.Num_departamento
    WHERE d.Nome_departamento = nomeDepartamento
    GROUP BY e.Num_departamento_pertenece;

    RETURN total_empleados;
END
$$