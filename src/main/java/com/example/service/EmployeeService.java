package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

import java.util.List;

@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository repository;

	/**
	 * 全件取得
	 * 
	 * @return 全ての従業員
	 */
	public List<Employee> showList() {
		List<Employee> employees = repository.findAll();
		return employees;
	}

	/**
	 * 従業員1件取得
	 * 
	 * @param id
	 * @return 従業員データ1件
	 */
	public Employee showDetail(Integer id) {
		Employee employee = repository.load(id);
		return employee;
	}

}
