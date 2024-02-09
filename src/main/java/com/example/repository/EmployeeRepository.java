package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

import java.util.List;

@Repository
public class EmployeeRepository {
    @Autowired
    NamedParameterJdbcTemplate template;

    private static final String EMPLOYEE_FINDALL_SQL = """
            SELECT
                id,
                name,
                image,
                gender,
                hire_date,
                mail_address,
                zip_code,
                address,
                telephone,
                salary,
                characteristics,
                dependents_count
            FROM
                employees
            """;

    private static final String EMPLOYEE_FINDBYID_SQL = """
            SELECT
                id,
                name,
                image,
                gender,
                hire_date,
                mail_address,
                zip_code,
                address,
                telephone,
                salary,
                characteristics,
                dependents_count
            FROM
                employees
            WHERE
                id = :id
            """;

    private static final String EMPLOYEE_UPDATE_SQL = """
            UPDATE employees
            SET
                name = :name,
                image = :image,
                gender = :gender,
                hire_date = :hireDate,
                mail_address = :mailAddress,
                zip_code = :zipCode,
                address = :address,
                telephone = :telephone,
                salary = :salary,
                characteristics = :characteristics,
                dependents_count = :dependentsCount
            WHERE id = :id
            """;

    private static RowMapper<Employee> EMPROYEE_ROM_MAPPER = (rs, index) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    };

    /**
     * 全件取得
     * 
     * @return 全ての従業員
     */
    public List<Employee> findAll() {
        List<Employee> employees = template.query(EMPLOYEE_FINDALL_SQL, EMPROYEE_ROM_MAPPER);
        return employees;
    }

    /**
     * 従業員1件取得
     * 
     * @param id
     * @return 従業員データ1件
     */

    public Employee load(Integer id) {
        SqlParameterSource params = new MapSqlParameterSource().addValue("id", id);
        Employee employee = template.queryForObject(EMPLOYEE_FINDBYID_SQL, params, EMPROYEE_ROM_MAPPER);
        return employee;
    }

    /**
     * 従業員情報
     * 
     * @param employee
     * @param id
     */
    public void update(Employee employee) {
        SqlParameterSource params = new BeanPropertySqlParameterSource(employee);
        template.update(EMPLOYEE_UPDATE_SQL, params);
    }
}
