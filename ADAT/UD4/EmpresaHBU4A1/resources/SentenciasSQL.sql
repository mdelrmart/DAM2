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
    CONSTRAINT CK_Sexo_Familiar CHECK ((Sexo = 'H' OR Sexo = 'M'))
);

-- Tabla Aficion
CREATE TABLE AFICION
(
    NSS_Empregado VARCHAR(15) NOT NULL,
    Aficion       VARCHAR(50) NOT NULL,
    CONSTRAINT PK_AFICION PRIMARY KEY (NSS_Empregado, Aficion),
    CONSTRAINT FK_AFICION_EMPREGADO FOREIGN KEY (NSS_Empregado) REFERENCES EMPREGADO (NSS)
)

-- Tabla Lugar
CREATE TABLE LUGAR
(
    ID               INT         NOT NULL,
    Num_departamento INT         NOT NULL,
    Lugar            VARCHAR(15) NOT NULL,
    CONSTRAINT PK_IDLUGAR PRIMARY KEY (ID ASC),
    CONSTRAINT UK_NUMDPTO UNIQUE (Num_departamento, Lugar),
    CONSTRAINT FK_NUMDPTOLUGAR FOREIGN KEY (Num_departamento) REFERENCES DEPARTAMENTO (Num_departamento)
)

-- Tabla Horas Extra
CREATE TABLE HORASEXTRA
(
    NSS_Empregado VARCHAR(15) NOT NULL,
    Data          DATE        NOT NULL,
    Horas         FLOAT       NOT NULL,
    CONSTRAINT PK_HORAS_EXTRA PRIMARY KEY (NSS_Empregado, Data),
    CONSTRAINT FK_NSS_EMPREGADO_HORASEXTRA FOREIGN KEY (NSS_Empregado) REFERENCES EMPREGADO (NSS)
)

ALTER TABLE EMPREGADO
    ADD
        Rua VARCHAR(30) NULL,
        CP CHAR(5) NULL,
        Localidade VARCHAR(30) NULL,
        Provincia VARCHAR(30) NULL;