package com.devsuperior.aulalazy.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.aulalazy.dto.EmployeeDepartmentDTO;
import com.devsuperior.aulalazy.dto.EmployeeMinDTO;
import com.devsuperior.aulalazy.entities.Employee;
import com.devsuperior.aulalazy.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Transactional(readOnly = true)
	public EmployeeMinDTO findByIdMin(Long id) {
		Optional<Employee> result = repository.findById(id);
		return new EmployeeMinDTO(result.get());
	}

	@Transactional(readOnly = true)
	public EmployeeDepartmentDTO findByIdWithDepartment(Long id) {
		Optional<Employee> result = repository.findById(id);
		return new EmployeeDepartmentDTO(result.get());
	}

	@Transactional(readOnly = true)
	public List<EmployeeDepartmentDTO> findEmployeesWithDepartments() {
		List<Employee> result = repository.findEmployeesWithDepartments();
		return result.stream().map(x -> new EmployeeDepartmentDTO(x)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public List<EmployeeMinDTO> findByName(String name) {
		List<Employee> result = repository.findByNameIgnoreCase(name);
		return result.stream().map(x -> new EmployeeMinDTO(x)).collect(Collectors.toList());
	}

	public List<EmployeeDepartmentDTO> findByDepartmentName(String departmentName) {
		List<EmployeeDepartmentDTO> result = repository.findByDepartmentName(departmentName);
		return result;
	}

	@Transactional
	public Page<Employee> findAll(Pageable pageable) {
		Page<Employee> result = repository.searchAll(pageable);
		return result;
	}
}
