select e.nss, concat(e.apelido1, ' ', e.apelido2, ', ', e.nome) as nome_completo, e.departamento.nomeDepartamento,
       case type(e)
           when Empregadofixo then 'fijo'
           when Empregadotemporal then 'temporal'
           end as tipo_empregado,
       e.telefonos.size
from Empregado e
order by e.apelido1, e.apelido2, e.nome
------------------------------------------------------------------------------------------------------------------------
select e.nss, concat(e.apelido1, ' ', e.apelido2, ', ', e.nome) as nome_completo, e.departamento.nomeDepartamento,
       case type(e)
           when Empregadofixo then 'fijo'
           when Empregadotemporal then 'temporal'
           end as tipo_empregado,
       e.telefonos.size
from Empregado e
where e.class in (Empregadofixo)
order by e.apelido1, e.apelido2, e.nome
------------------------------------------------------------------------------------------------------------------------
