USE BDEmpresa;

-- COMPROBAR SI EXISTE LA TABLA VEHICULOS Y BORRARLA EN CASO DE QUE EXISTA
IF DB_ID('VEHICULOS') IS NOT NULL
    BEGIN
        SELECT 'LA TABLA VEHICULOS YA EXISTE, SE VA A BORRAR' AS MENSAJE
        DROP TABLE VEHICULOS
    END
ELSE
    SELECT 'LA TABLA VEHICULOS NO EXISTE, SE VA A CREAR' AS MENSAJE

CREATE TABLE VEHICULOS
(
    codVehiculo INT IDENTITY (1,1) NOT NULL,
    Matricula   CHAR(8)            NOT NULL,
    Marca       VARCHAR(25)        NOT NULL,
    Modelo      VARCHAR(15)        NOT NULL,
    Combustible CHAR(1)            NOT NULL
)

ALTER TABLE VEHICULOS
    ADD CONSTRAINT PK_Vehiculos_codVehiculo PRIMARY KEY (codVehiculo)

ALTER TABLE VEHICULOS
    ADD CONSTRAINT UQ_Vehiculos_Combustible UNIQUE (Matricula)

ALTER TABLE VEHICULOS
    ADD CONSTRAINT CK_Vehiculos_Matricula CHECK (Matricula LIKE '[0-9][0-9][0-9][0-9] [A-Z][A-Z][A-Z]')

ALTER TABLE VEHICULOS
    ADD CONSTRAINT CK_Vehiculos_Combustible CHECK (Combustible IN ('G', 'D'))

-- COMPROBAR SI EXISTE LA TABLA VEHICULOS_RENTING Y BORRARLA EN CASO DE QUE EXISTA
IF DB_ID('VEHICULOS_RENTING') IS NOT NULL
    BEGIN
        SELECT 'LA TABLA VEHICULOS_RENTING YA EXISTE, SE VA A BORRAR' AS MENSAJE
        DROP TABLE VEHICULOS_RENTING
    END
ELSE
    SELECT 'LA TABLA VEHICULOS_RENTING NO EXISTE, SE VA A CREAR' AS MENSAJE

CREATE TABLE VEHICULOS_RENTING
(
    codVehiculo    INT              NOT NULL,
    fechaInicio    DATE             NOT NULL,
    mesesAlquilado TINYINT          NOT NULL,
    precio         DOUBLE PRECISION NOT NULL
)

ALTER TABLE VEHICULOS_RENTING
    ADD CONSTRAINT PK_Vehiculos_codVehiculo PRIMARY KEY (codVehiculo)

ALTER TABLE VEHICULOS_RENTING
    ADD CONSTRAINT FK_Vehiculos_codVehiculo FOREIGN KEY (codVehiculo) REFERENCES VEHICULOS (codVehiculo)

-- COMPROBAR SI EXISTE LA TABLA VEHICULOS_PROPIOS Y BORRARLA EN CASO DE QUE EXISTA
IF DB_ID('VEHICULOS_PROPIOS') IS NOT NULL
    BEGIN
        SELECT 'LA TABLA VEHICULOS_PROPIOS YA EXISTE, SE VA A BORRAR' AS MENSAJE
        DROP TABLE VEHICULOS_PROPIOS
    END
ELSE
    SELECT 'LA TABLA VEHICULOS_PROPIOS NO EXISTE, SE VA A CREAR' AS MENSAJE

CREATE TABLE VEHICULOS_PROPIOS
(
    codVehiculo INT              NOT NULL,
    fechaCompra DATE             NOT NULL,
    precio      DOUBLE PRECISION NOT NULL
)

ALTER TABLE VEHICULOS_PROPIOS
    ADD CONSTRAINT PK_Vehiculos_codVehiculo PRIMARY KEY (codVehiculo)

ALTER TABLE VEHICULOS_PROPIOS
    ADD CONSTRAINT FK_Vehiculos_codVehiculo FOREIGN KEY (codVehiculo) REFERENCES VEHICULOS (codVehiculo)

--DECLARE @NumDepartamento INT; -- Declarar la variable de salida

--EXEC pr_ObtenerNumDepartamento
--    @NombreDepartamento = 'CONTABILIDADE', -- Reemplaza con el nombre deseado
--  @NumDepartamento = @NumDepartamento OUTPUT;

-- Verificar el valor devuelto
--PRINT 'El número del departamento es: ' + CAST(@NumDepartamento AS VARCHAR);

-----------------------------------------------------------------------------------
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
-----------------------------------------------------------------------------------
CREATE PROCEDURE pr_ObtenerNumDepartamento(@NombreDepartamento VARCHAR(25),
                                           @NumDepartamento INT OUTPUT)
AS
BEGIN
    -- Intentamos obtener el número del departamento
    SELECT @NumDepartamento = Num_departamento
    FROM DEPARTAMENTO
    WHERE Nome_departamento = @NombreDepartamento;

    -- Si no se encuentra el departamento, devolvemos -1
    IF @NumDepartamento IS NULL
        BEGIN
            SET @NumDepartamento = -1;
        END
END;
GO
-----------------------------------------------------------------------------------
-- Version Borja
CREATE FUNCTION fn_ComprobarMatricula(@matricula VARCHAR(10))
    RETURNS INT
AS
BEGIN
    DECLARE @resultado INT = (SELECT count(*) from VEHICULOS where Matricula = @matricula);

    RETURN @resultado;
END;

-- Version Normal
CREATE FUNCTION fn_ComprobarMatricula(@matricula VARCHAR(10))
    RETURNS INT
AS
BEGIN
    DECLARE @resultado INT;

    -- Verifica si la matrícula existe en la tabla VEHICULOS
    IF EXISTS (SELECT 1 FROM VEHICULOS WHERE Matricula = @matricula)
        BEGIN
            SET @resultado = 1; -- Si existe, devuelve 1
        END
    ELSE
        BEGIN
            SET @resultado = 0; -- Si no existe, devuelve 0
        END

    RETURN @resultado; -- Devuelve el resultado
END;
-----------------------------------------------------------------------------------
CREATE PROCEDURE pr_EliminarProyecto (@numProyecto INT)
AS
BEGIN
    BEGIN TRY
        -- Iniciar la transacción
        BEGIN TRANSACTION;

        -- Verificar si el proyecto existe
        IF EXISTS (SELECT 1 FROM PROXECTO WHERE Num_proxecto = @numProyecto)
            BEGIN
                -- Eliminar referencias en la tabla Empleado_Proyecto
                DELETE FROM EMPREGADO_PROXECTO
                WHERE Num_proxecto = @numProyecto;

                -- Eliminar el proyecto de la tabla Proyecto
                DELETE FROM PROXECTO
                WHERE Num_proxecto = @numProyecto;
            END
        ELSE
            BEGIN
                -- Lanzar un error si el proyecto no existe
                THROW 50001, 'El proyecto no existe.', 1;
            END

        -- Confirmar la transacción
        COMMIT TRANSACTION;
    END TRY
    BEGIN CATCH
        -- Revertir la transacción en caso de error
        ROLLBACK TRANSACTION;

        -- Rethrow para capturar el error en el cliente
        THROW;
    END CATCH
END
GO