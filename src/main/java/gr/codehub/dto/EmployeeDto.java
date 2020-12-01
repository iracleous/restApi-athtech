package gr.codehub.dto;

import gr.codehub.model.Department;
import gr.codehub.model.Employee;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class EmployeeDto {
    private int id;

    private String firstName;
    private String lastName;
    private String address;
    private Date dateOfBirth;
    private Date registrationDate;


    private String departmentTitle;


    public static Employee getEmployee(EmployeeDto employeeDto){
        if (employeeDto==null) return null;
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAddress(employeeDto.getAddress());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setRegistrationDate(employeeDto.getRegistrationDate());

        return employee;
    }

    public static EmployeeDto getEmployeeDto(Employee employee){

        if (employee==null) return null;
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setAddress(employee.getAddress());
        employeeDto.setDateOfBirth(employee.getDateOfBirth());
        employeeDto.setRegistrationDate(employee.getRegistrationDate());
        if(employee.getDepartment()!=null)
            employeeDto.setDepartmentTitle(employee.getDepartment().getTitle());
        return employeeDto;
    }




}
