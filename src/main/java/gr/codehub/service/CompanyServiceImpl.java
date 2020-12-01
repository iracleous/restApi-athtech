package gr.codehub.service;

import gr.codehub.dto.DepartmentDto;
import gr.codehub.dto.EmployeeDto;
import gr.codehub.exception.DepartmentNotAddedException;
import gr.codehub.exception.DepartmentNotFoundException;
import gr.codehub.exception.EmployeeNotAddedException;
import gr.codehub.exception.EmployeeNotFoundException;
import gr.codehub.model.Department;
import gr.codehub.model.Employee;
import gr.codehub.repository.DepartmentRepository;
import gr.codehub.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws EmployeeNotAddedException {
        Employee employee = EmployeeDto.getEmployee(employeeDto);
        employeeRepository.save(employee);
        return EmployeeDto.getEmployeeDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(int employeeId, EmployeeDto employeeDto) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) throw new EmployeeNotFoundException();
        Employee employee = employeeOptional.get();
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setAddress(employeeDto.getAddress());
        employee.setDateOfBirth(employeeDto.getDateOfBirth());
        employee.setRegistrationDate(employeeDto.getRegistrationDate());

        employeeRepository.save(employee);
        return EmployeeDto.getEmployeeDto(employee);

    }

    @Override
    @Transactional
    public boolean removeEmployee(int employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) throw new EmployeeNotFoundException();
        Employee employee = employeeOptional.get();
        employeeRepository.delete(employee);
        return true;
    }

    @Override
    public EmployeeDto getEmployeeById(int id) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) throw new EmployeeNotFoundException();
        return EmployeeDto.getEmployeeDto(employeeOptional.get());
    }

    @Override
    public List<EmployeeDto> getEmployeeByPage(int pageNumber, int pageSize) {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDto> employeeDtoList = new ArrayList<>();
        employees.forEach(employee -> employeeDtoList.add(EmployeeDto.getEmployeeDto(employee)));
        return employeeDtoList;
    }

    @Override
    public DepartmentDto addDepartment(DepartmentDto departmentDto) throws DepartmentNotAddedException {
        Department department = DepartmentDto.getDepartment(departmentDto);
        departmentRepository.save(department);
        return DepartmentDto.getDepartmentDto(department);
    }

    @Override
    public DepartmentDto updateDepartment(int departmentId, DepartmentDto departmentDto) throws DepartmentNotFoundException {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (!departmentOptional.isPresent()) throw new DepartmentNotFoundException();
        Department department = departmentOptional.get();

        department.setTitle(departmentDto.getTitle());
        department.setLocation(departmentDto.getLocation());

        departmentRepository.save(department);
        return DepartmentDto.getDepartmentDto(department);
    }

    @Override
    @Transactional
    public boolean removeDepartment(int departmentId) throws DepartmentNotFoundException {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (!departmentOptional.isPresent()) throw new DepartmentNotFoundException();
        Department department = departmentOptional.get();
        departmentRepository.delete(department);
        return true;
    }

    @Override
    public DepartmentDto getDepartmentById(int departmentId) throws DepartmentNotFoundException {
        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (!departmentOptional.isPresent()) throw new DepartmentNotFoundException();
        return DepartmentDto.getDepartmentDto(departmentOptional.get());
    }

    @Override
    public List<DepartmentDto> getDepartmentByPage(int pageNumber, int pageSize) {
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        departments.forEach(department -> departmentDtos.add(DepartmentDto.getDepartmentDto(department)));
        return departmentDtos;
    }

    @Override
    @Transactional
    public EmployeeDto assignEmployeeToDepartment(int employeeId, int departmentId)
            throws DepartmentNotFoundException, EmployeeNotFoundException {

        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) throw new EmployeeNotFoundException();
        Employee employee = employeeOptional.get();

        Optional<Department> departmentOptional = departmentRepository.findById(departmentId);
        if (!departmentOptional.isPresent()) throw new DepartmentNotFoundException();
        Department department = departmentOptional.get();
        employee.setDepartment(department);

        return EmployeeDto.getEmployeeDto(employee);
    }

    @Override
    @Transactional
    public EmployeeDto removeEmployeeFromDepartment(int employeeId) throws EmployeeNotFoundException {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (!employeeOptional.isPresent()) throw new EmployeeNotFoundException();
        Employee employee = employeeOptional.get();

        employee.setDepartment(null);

        return EmployeeDto.getEmployeeDto(employee);
    }
}
