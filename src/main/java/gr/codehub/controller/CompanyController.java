package gr.codehub.controller;

import gr.codehub.dto.DepartmentDto;
import gr.codehub.dto.EmployeeDto;
import gr.codehub.exception.DepartmentNotAddedException;
import gr.codehub.exception.DepartmentNotFoundException;
import gr.codehub.exception.EmployeeNotAddedException;
import gr.codehub.exception.EmployeeNotFoundException;
import gr.codehub.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;



    @PostMapping("employee")
    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto)
            throws EmployeeNotAddedException {
        return companyService.addEmployee(employeeDto);
    }


    @GetMapping("employee")
    public List<EmployeeDto> getEmployees(){
        return companyService.getEmployeeByPage(1,10);
    }

    @GetMapping("employee/{id}")
    public EmployeeDto getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        return companyService.getEmployeeById(id);
    }

    @DeleteMapping("employee/{id}")
    public boolean deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        return companyService.removeEmployee(id);
    }


    @PostMapping("department")
    public DepartmentDto addDepartment(@RequestBody  DepartmentDto  departmentDto)
            throws  DepartmentNotAddedException {
        return companyService.addDepartment(departmentDto);
    }


    @GetMapping("department")
    public List<DepartmentDto> getDepartments(){
        return companyService.getDepartmentByPage(1,10);
    }

    @GetMapping("department/{id}")
    public DepartmentDto getDepartment(@PathVariable int id) throws
            DepartmentNotFoundException,
            DepartmentNotFoundException {
        return companyService.getDepartmentById(id);
    }

    @DeleteMapping("department/{id}")
    public boolean deleteDepartment(@PathVariable int id) throws DepartmentNotFoundException {
        return companyService.removeDepartment(id);
    }

    @PostMapping("employee/{employeeId}/department/{departmentId}")
    public EmployeeDto assignEmployeeToDepartment(@PathVariable int employeeId,
                                                  @PathVariable int departmentId)
            throws EmployeeNotFoundException,DepartmentNotFoundException {
        return companyService.assignEmployeeToDepartment(employeeId, departmentId);
    }

    @PostMapping("employee/{employeeId}/nodepartment")
    public EmployeeDto assignEmployeeToDepartment(@PathVariable int employeeId)
            throws EmployeeNotFoundException {
        return companyService.removeEmployeeFromDepartment(employeeId);
    }


}
