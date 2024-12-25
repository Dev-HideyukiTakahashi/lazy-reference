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
