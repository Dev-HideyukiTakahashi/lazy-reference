package com.devsuperior.aulalazy.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.aulalazy.dto.EmployeeDepartmentDTO;
import com.devsuperior.aulalazy.dto.EmployeeMinDTO;
import com.devsuperior.aulalazy.entities.Employee;
import com.devsuperior.aulalazy.services.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping(value = "/page")
	public ResponseEntity<Page<Employee>> findAll(Pageable pageable) {
		Page<Employee> page = service.findAll(pageable);
		return ResponseEntity.ok(page);
	}

	@GetMapping(value = "/{id}/min")
	public ResponseEntity<EmployeeMinDTO> findByIdMin(@PathVariable Long id) {
		EmployeeMinDTO obj = service.findByIdMin(id);
		return ResponseEntity.ok(obj);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeDepartmentDTO> findByIdWithDepartment(@PathVariable Long id) {
		EmployeeDepartmentDTO obj = service.findByIdWithDepartment(id);
		return ResponseEntity.ok(obj);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeMinDTO>> findByName(@RequestParam(defaultValue = "") String name) {
		List<EmployeeMinDTO> list = service.findByName(name);
		return ResponseEntity.ok(list);
	}

	@GetMapping("/{departmentName}/department")
	public ResponseEntity<List<EmployeeDepartmentDTO>> findByDepartmentName(
			@PathVariable String departmentName) {
		List<EmployeeDepartmentDTO> list = service.findByDepartmentName(departmentName);
		return ResponseEntity.ok(list);
	}
}
