SELECT NOME, APELIDO_1, APELIDO_2, LOCALIDADE, SALARIO, DATA_NACEMENTO, Nome_departamento,
       (SELECT Nome
        FROM EMPREGADO jefe
        WHERE jefe.NSS = e.NSS_Supervisa) AS Nome_jefe
FROM EMPREGADO e INNER JOIN DEPARTAMENTO d ON e.Num_departamento_pertenece = d.Num_departamento
WHERE LOCALIDADE = 'Vigo';

UPDATE proxecto
SET Num_departamento_controla = (SELECT Num_departamento
                                 FROM departamento
                                 WHERE Nome_departamento = ?)
WHERE Nome_proxecto = ?;

DELETE FROM proxecto
WHERE Num_proxecto = 'recibimos';

DELETE FROM empregado_proxecto
WHERE Num_proxecto = 'recibimos';

SELECT *
FROM proxecto p
INNER JOIN departamento d ON p.Num_departamento_controla = d.Num_departamento
WHERE d.Nome_departamento = 'INFORMÁTICA';

SELECT d.Num_departamento, d.Nome_departamento, d.NSS_dirige, d.Data_direccion
FROM departamento d
INNER JOIN proxecto p
ON d.Num_departamento=p.Num_departamento_controla
GROUP BY Num_departamento
HAVING COUNT(d.Num_departamento) >= 2;

SELECT COUNT(e.Num_departamento_pertenece)
FROM empregado e
INNER JOIN departamento d
ON e.Num_departamento_pertenece=d.Num_departamento
WHERE d.Nome_departamento = 'INFORMÁTICA'
GROUP BY e.Num_departamento_pertenece
