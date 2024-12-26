Cláusula JOIN FETCH

Nota: esta cláusula não funciona para buscas paginadas do Spring.

Exemplo:

`public interface EmployeeRepository extends JpaRepository<Employee, Long> {
@Query("SELECT obj FROM Employee obj JOIN FETCH obj.department")
List<Employee> findEmployeesWithDepartments();
}`

---

Transactional e open-in-view no Spring

• A annotation @Transactional assegura:
(1) resolução da transação com o banco de dados
(2) resolução de todas pendências “lazy” com o banco de dados

• A propriedade spring.jpa.open-in-view=false faz com que a sessão
JPA seja encerrada antes de voltar para a camada controller
(camada web)

---

Query methods

https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html

---

JPQL exemplos:

- Buscamos na JPQL pela classe e não pelo nome da tabela

Exemplo 1

```
SQL:
SELECT
FROM tb_employee
WHERE UPPER(name) LIKE 'MARIA%'

JPQL:
SELECT obj
FROM Employee obj
WHERE UPPER(obj.name) LIKE 'MARIA%'
```

Exemplo 2 (JOIN)

```
SQL:
SELECT tb_employee.*
FROM tb_employee
INNER JOIN tb_department ON tb_department.id = tb_employee.department_id
WHERE tb_department.name = 'Financeiro'

JPQL:
SELECT obj
FROM Employee obj
WHERE obj.department.name = 'Financeiro'
```

---

Seleção de exercícios SQL no Beecrowd

Grupo 1: projeção, restrição
2602, 2603, 2604, 2607, 2608, 2615, 2624

Grupo 2: JOIN
2605, 2606, 2611, 2613, 2614, 2617, 2618, 2619, 2620, 2621, 2622, 2623, 2742

Grupo 3: GROUP BY, subconsultas
2609, 2993, 2994, 2995, 2996

Grupo 4: Expressões na projeção
2610, 2625, 2738, 2739, 2741, 2743, 2744, 2745, 2746, 3001

Grupo 5: Diferença, União
2616, 2737, 2740, 2990

Grupo 6: Difíceis
2988, 2989, 2991, 2992, 2997, 2998, 2999
