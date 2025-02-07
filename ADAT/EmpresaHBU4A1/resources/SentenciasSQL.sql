-- Tabla telefono antes de añadir el campo información y la clase teléfono
CREATE TABLE TELEFONO
(
    NSS    VARCHAR(15) NOT NULL,
    Numero CHAR(9)     NOT NULL,

    CONSTRAINT PK_NSS_Telefono PRIMARY KEY (NSS, Numero),
    CONSTRAINT FK_NSS_Telefono FOREIGN KEY (NSS) REFERENCES Empregado (NSS),
    CONSTRAINT CK_Telefono_Numeros CHECK (Numero LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

-- Tabla telefono después de añadir el campo información y la clase teléfono
CREATE TABLE TELEFONO
(
    NSS         VARCHAR(15) NOT NULL,
    Numero      CHAR(9)     NOT NULL,
    Informacion VARCHAR(25) NULL,

    CONSTRAINT PK_NSS_Telefono PRIMARY KEY (NSS, Numero),
    CONSTRAINT FK_NSS_Telefono FOREIGN KEY (NSS) REFERENCES Empregado (NSS),
    CONSTRAINT CK_Telefono_Numeros CHECK (Numero LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]')
);

-- Tabla familiar
CREATE TABLE FAMILIAR
(
    NSS_Empregado VARCHAR(15) NOT NULL,
    Numero        INT         NOT NULL,
    NSS           VARCHAR(15) NOT NULL,
    Nome          VARCHAR(25) NOT NULL,
    Apelido1      VARCHAR(25) NOT NULL,
    Apelido2      VARCHAR(25) NULL,
    Datanacemento DATE        NULL,
    Parentesco    VARCHAR(20) NULL,
    Sexo          CHAR(1)     NULL,

    CONSTRAINT PK_NSS_Empregado_Numero PRIMARY KEY (NSS_Empregado, Numero),
    CONSTRAINT FK_NSS_Empregado FOREIGN KEY (NSS_Empregado) REFERENCES Empregado (NSS),
    CONSTRAINT UQ_NSS_Familiar UNIQUE (NSS),
    CONSTRAINT CK_Sexo_Familiar CHECK ((Sexo='H' OR Sexo='M'))
);