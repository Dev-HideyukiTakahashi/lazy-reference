package com.devsuperior.aulalazy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}
