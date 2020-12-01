package gr.codehub.dto;

import gr.codehub.model.Department;
import lombok.Data;

import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
public class DepartmentDto {
    private int id;

    private String title;
    private String location;



    public static Department getDepartment(DepartmentDto departmentDto){
        if (departmentDto==null) return null;
        Department department = new Department();
        department.setTitle(departmentDto.getTitle());
        department.setLocation(departmentDto.getLocation());
        department.setId(departmentDto.getId());
        return department;
    }

    public static DepartmentDto getDepartmentDto(Department department){
        if (department==null) return null;
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setTitle(department.getTitle());
        departmentDto.setLocation(department.getLocation());
        departmentDto.setId(department.getId());
        return departmentDto;
    }
}
