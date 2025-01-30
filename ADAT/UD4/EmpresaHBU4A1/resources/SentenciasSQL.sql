-- Tabla telefono antes de añadir el campo información y la clase teléfono
CREATE TABLE TELEFONO
(
    NSS         VARCHAR(15) NOT NULL,
    Numero      CHAR(9)     NOT NULL,

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


DROP TABLE TELEFONO;