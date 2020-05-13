package employees;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper( EmployeeMapper.class );

    EmployeeDto employeeToEmployeeDto(Employee employee);
    NameDto nameToNameDto(Name name);
    List<EmployeeDto> employeesToEmployeeDtos(List<Employee> employee);
}