package com.devsuperior.aulalazy.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.aulalazy.dto.EmployeeDepartmentDTO;
import com.devsuperior.aulalazy.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// CLAUSULA JOIN FETCH - PERFORMANCE
	// HIBERNATE FAZ APENAS 1 CONSULTA NO BANCO
	// NAO FUNCIONA COM BUSCA PAGINADA
	@Query("SELECT obj FROM Employee obj JOIN FETCH obj.department")
	List<Employee> findEmployeesWithDepartments();

	/*
	 * Hibernate:
	 * select
	 * e1_0.id,
	 * d1_0.id,
	 * d1_0.name,
	 * e1_0.email,
	 * e1_0.name
	 * from
	 * tb_employee e1_0
	 * join
	 * tb_department d1_0
	 * on d1_0.id=e1_0.department_id
	 * 
	 */

	// JPA QUERY METHODS
	List<Employee> findByNameIgnoreCase(String name);

	/*
	 * JPQL - NO SELECT QUANDO NÃO É A CLASSE ENTIDADE (SE FOSSE BASTARIA PASSAR
	 * SELECT obj)
	 * INFORMAMOS O CAMINHO DA CLASSE QUE VAMOS INSTANCIAR
	 * CHAMAMOS O NOME DA CLASSE ENTIDADE NO FROM COM O ALIAS
	 * E USAMOS O CONSTRUTOR DA CLASSE DTO NESSE CASO
	 */
	@Query("SELECT new com.devsuperior.aulalazy.dto.EmployeeDepartmentDTO(obj) "
			+ "FROM Employee obj "
			+ "WHERE obj.department.name = :departmentName")
	List<EmployeeDepartmentDTO> findByDepartmentName(String departmentName);

	/*
	 * EXEMPLO COM JPQL E JOIN FETCH
	 * POR PADRAO JOIN FETCH RETORNA APENAS LISTA
	 * POR ISSO USAMOS O COUNTQUERY PARA CONTAR O TOTAL DE REGISTROS
	 */
	@Query(value = "SELECT obj FROM Employee obj JOIN FETCH obj.department", countQuery = "SELECT COUNT(obj) FROM Employee obj JOIN obj.department")
	Page<Employee> searchAll(Pageable pageable);
}
