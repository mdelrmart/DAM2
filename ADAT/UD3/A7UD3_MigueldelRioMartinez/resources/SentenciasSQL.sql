CREATE TABLE EMPREGADO_FAMILIAR
(

    NSS_Empregado  VARCHAR(15) NOT NULL,
    Numero         SMALLINT    NOT NULL,
    NSS            VARCHAR(15) NOT NULL,
    Nome           VARCHAR(15) NOT NULL,
    Apelido_1      VARCHAR(25) NOT NULL,
    Apelido_2      VARCHAR(25) NULL,
    Data_nacemento DATE        NULL,
    Parentesco     VARCHAR(20) NOT NULL,
    Sexo           CHAR(1)     NOT NULL DEFAULT 'M',
)