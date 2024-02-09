package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService service;

	/**
	 * 従業員リスト表示
	 * @param model
	 * @return 従業員リスト画面
	 */
	@GetMapping("/showList")
	public String showList(Model model) {
		List<Employee> employees = service.showList();
		model.addAttribute("employees", employees);
		return "employee/list";
	}

	@GetMapping("/showDetail")
	public String showDetail(String id, Model model, UpdateEmployeeForm form) {
		Employee employee = service.showDetail(Integer.parseInt(id));
		model.addAttribute("employee", employee);
		return "employee/detail";
	}
	
	
}
