DROP TABLE IF EXISTS EMPREGADO;

CREATE TABLE EMPREGADO
(

    Nome                       VARCHAR(25) NOT NULL,
    Apelido_1                  VARCHAR(25) NOT NULL,
    Apelido_2                  VARCHAR(25) NOT NULL,
    NSS                        VARCHAR(15) NOT NULL,
    Rua                        VARCHAR(30) NULL,
    Numero_rua                 INT         NULL,
    Piso                       VARCHAR(4)  NULL,
    CP                         CHAR(5)     NULL,
    Localidade                 VARCHAR(25) NULL,
    Data_nacemento             DATE        NULL,
    Salario                    FLOAT       NULL,
    Sexo                       CHAR(1)     NULL,
    NSS_Supervisa              VARCHAR(15) NULL,
    Num_departamento_pertenece INT         NULL,
    CONSTRAINT PK_Empregado_NSS PRIMARY KEY (NSS),
    CONSTRAINT FK_EMPREGADO_EMPREGADO FOREIGN KEY (NSS_Supervisa) REFERENCES EMPREGADO (NSS)
);

-- COMPROBAR SI EXISTE LA TABLA DEPARTAMENTO Y BORRARLA EN CASO DE QUE EXISTA
DROP TABLE IF EXISTS DEPARTAMENTO;

CREATE TABLE DEPARTAMENTO
(

    Num_departamento  INT         NOT NULL,
    Nome_departamento VARCHAR(25) NOT NULL,
    NSS_dirige        VARCHAR(15) NULL,
    Data_direccion    DATE        NULL,
    CONSTRAINT PK_Departamento_Num_departamento PRIMARY KEY (Num_departamento),
    CONSTRAINT U_Departamento_Nome_departamento UNIQUE (Nome_departamento),
    CONSTRAINT FK_DEPARTAMENTO_EMPREGADO FOREIGN KEY (NSS_dirige) REFERENCES EMPREGADO
);

-- COMPROBAR SI EXISTE LA TABLA PROXECTO Y BORRARLA EN CASO DE QUE EXISTA
DROP TABLE IF EXISTS PROXECTO;

CREATE TABLE PROXECTO
(

    Num_proxecto              INT         NOT NULL,
    Nome_proxecto             VARCHAR(25) NOT NULL,
    Lugar                     VARCHAR(25) NOT NULL,
    Num_departamento_controla INT         NOT NULL,
    CONSTRAINT PK_Proyecto_Num_proxecto PRIMARY KEY (Num_proxecto),
    CONSTRAINT U_Proxecto_Nome_proxecto UNIQUE (Nome_proxecto),
    CONSTRAINT FK_PROXECTO_DEPARTAMENTO FOREIGN KEY (Num_departamento_controla) REFERENCES DEPARTAMENTO
);

-- COMPROBAR SI EXISTE LA TABLA EMPREGADO_PROXECTO Y BORRARLA EN CASO DE QUE EXISTA
DROP TABLE IF EXISTS EMPREGADO_PROXECTO;

CREATE TABLE EMPREGADO_PROXECTO
(

    NSS_empregado  VARCHAR(15) NOT NULL,
    Num_proxecto   INT         NOT NULL,
    Horas_semanais INT         NULL,
    CONSTRAINT PK_EMPREGADO_PROXECTO_NSS_empregadoNum_proxecto PRIMARY KEY (NSS_empregado, Num_proxecto),
    CONSTRAINT FK_EMPREGADO_PROXECTO_EMPREGADO FOREIGN KEY (NSS_empregado) REFERENCES EMPREGADO,
    CONSTRAINT FK_EMPREGADO_PROXECTO_PROXECTO FOREIGN KEY (Num_proxecto) REFERENCES PROXECTO
--  CONSTRAINT FK_EMPREGADO_DEPARTAMENTO FOREIGN KEY (Num_departamento_pertenece) REFERENCES DEPARTAMENTO

);