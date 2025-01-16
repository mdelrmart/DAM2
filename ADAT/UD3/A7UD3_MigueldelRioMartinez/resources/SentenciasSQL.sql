CREATE TABLE EMPREGADO_FAMILIAR
(

    NSS_Empregado  VARCHAR(15) NOT NULL,
    Numero         SMALLINT    NOT NULL,
    NSS            VARCHAR(15) NOT NULL,
    Nome           VARCHAR(15) NOT NULL,
    Apelido_1      VARCHAR(25) NOT NULL,
    Apelido_2      VARCHAR(25) NULL,
    Data_nacemento DATE NULL,
    Parentesco     VARCHAR(20) NOT NULL,
    Sexo           CHAR(1)     NOT NULL DEFAULT 'M',
)

-- Agregar la clave primaria
ALTER TABLE EMPREGADO_FAMILIAR
    ADD CONSTRAINT PK_EMPREGADO_FAMILIAR PRIMARY KEY (NSS_Empregado, Numero);

-- Agregar la clave foránea
ALTER TABLE EMPREGADO_FAMILIAR
    ADD CONSTRAINT FK_EMPREGADO_FAMILIAR FOREIGN KEY (NSS_Empregado) REFERENCES EMPREGADO (NSS);

-- Agregar clave única para garantizar que no haya duplicados de NSS de familiares para cada empleado
ALTER TABLE EMPREGADO_FAMILIAR
    ADD CONSTRAINT UQ_EMPREGADO_FAMILIAR_NSS UNIQUE (NSS_Empregado, NSS);

-- Restricción CHECK para garantizar que el sexo solo puede ser 'H' o 'M'
ALTER TABLE EMPREGADO_FAMILIAR
    ADD CONSTRAINT CHK_SEXO CHECK (Sexo IN ('H', 'M'));
--------------------------------------------------------------------------------
CREATE PROCEDURE pr_ExisteFamiliar
    @NSSempleado VARCHAR(25),
    @NSSfamiliar VARCHAR(25),
    @Resultado BIT OUTPUT
AS
BEGIN
    -- Inicializamos el valor del parámetro de salida
    SET @Resultado = 0;

    -- Comprobamos si existe la relación entre el empleado y el familiar
    IF EXISTS (
        SELECT 1
        FROM EMPREGADO e
        INNER JOIN EMPREGADO_FAMILIAR ef
            ON e.NSS = ef.NSS_Empregado
        WHERE e.NSS = @NSSempleado AND ef.NSS = @NSSfamiliar
    )
BEGIN
        SET @Resultado = 1; -- Existe la relación
END
END;
GO
----------------------------------------------------------------------------------
CREATE FUNCTION fn_UltimoNumeroFamiliar(@NSSempleado VARCHAR(10))
    RETURNS INT
AS
BEGIN
    DECLARE @resultado INT;

    SELECT @resultado = COUNT(*)
    FROM EMPREGADO_FAMILIAR
    WHERE NSS_Empregado = @NSSempleado

    RETURN @resultado;
END;