package gr.codehub.service;

import gr.codehub.dto.DepartmentDto;
import gr.codehub.dto.EmployeeDto;
import gr.codehub.exception.DepartmentNotAddedException;
import gr.codehub.exception.DepartmentNotFoundException;
import gr.codehub.exception.EmployeeNotAddedException;
import gr.codehub.exception.EmployeeNotFoundException;

import java.util.List;

public interface CompanyService {
    EmployeeDto addEmployee(EmployeeDto employeeDto) throws EmployeeNotAddedException;
    EmployeeDto updateEmployee(int employeeId, EmployeeDto employeeDto) throws EmployeeNotFoundException;
    boolean removeEmployee(int employeeId) throws EmployeeNotFoundException;
    EmployeeDto getEmployeeById(int id) throws EmployeeNotFoundException;
    List<EmployeeDto> getEmployeeByPage(int pageNumber, int pageSize);

    DepartmentDto addDepartment(DepartmentDto departmentDto) throws DepartmentNotAddedException;
    DepartmentDto updateDepartment(int departmentId, DepartmentDto departmentDto) throws DepartmentNotFoundException;
    boolean removeDepartment(int departmentId) throws DepartmentNotFoundException;
    DepartmentDto getDepartmentById(int departmentId) throws DepartmentNotFoundException;
    List<DepartmentDto> getDepartmentByPage(int pageNumber, int pageSize);

    EmployeeDto assignEmployeeToDepartment(int employeeId, int departmentId)
            throws EmployeeNotFoundException, DepartmentNotFoundException;
    EmployeeDto removeEmployeeFromDepartment(int employeeId)throws EmployeeNotFoundException;
}
