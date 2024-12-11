-- a)
CREATE PROCEDURE pr_CambioDomicilio
    @NSSempregado VARCHAR(10),
    @calle NVARCHAR(100),
    @numero INT,
    @numPiso NVARCHAR(5),
    @codPostal INT,
    @localidad NVARCHAR(30)
AS
BEGIN
UPDATE empregado
SET Rua = @calle,
    Numero_rua = @numero,
    Piso = @numPiso,
    CP = @codPostal,
    Localidade = @localidad
WHERE NSS = @NSSempregado;
END;
GO

-- b)
CREATE PROCEDURE pr_DatosProxectos
    @numProyecto INT,
    @p_nomeProxecto NVARCHAR(100) OUTPUT,
    @p_Lugar NVARCHAR(50) OUTPUT,
    @p_Num_departamento_controla INT OUTPUT
AS
BEGIN
SELECT @p_nomeProxecto = Nome_proxecto,
       @p_Lugar = Lugar,
       @p_Num_departamento_controla = Num_departamento_controla
FROM proxecto
WHERE Num_proxecto = @numProyecto;
END;
GO

-- c)
CREATE PROCEDURE pr_DepartControlaProxec
    @numProyectos INT
AS
BEGIN
SELECT d.Num_departamento,
       d.Nome_departamento,
       d.NSS_dirige,
       d.Data_direccion
FROM departamento d
        INNER JOIN proxecto p
ON d.Num_departamento = p.Num_departamento_controla
GROUP BY d.Num_departamento, d.Nome_departamento, d.NSS_dirige, d.Data_direccion
HAVING COUNT(d.Num_departamento) >= @numProyectos;
END;
GO

-- d)
CREATE FUNCTION fn_nEmpDepart(@nomeDepartamento NVARCHAR(25))
    RETURNS INT
AS
BEGIN
    DECLARE @total_empleados INT;

SELECT @total_empleados = COUNT(e.Num_departamento_pertenece)
FROM empregado e
        INNER JOIN departamento d
        ON e.Num_departamento_pertenece = d.Num_departamento
WHERE d.Nome_departamento = @nomeDepartamento
GROUP BY e.Num_departamento_pertenece;

RETURN @total_empleados;
END;
GO