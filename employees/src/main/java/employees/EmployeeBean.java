package employees;

import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@TransactionManagement(TransactionManagementType.CONTAINER)
public class EmployeeBean {

    @Inject
    private EmployeeRepository employeeRepository;

    @Transactional
    public EmployeeDto createEmployee(CreateEmployeeCommand command) {
        var employee = new Employee();

        Name name = new Name();
        name.setForename(command.getEmployeeName().getForename());
        name.setSurename(command.getEmployeeName().getSurename());
        employee.setEmployeeName(name);

        name = new Name();
        name.setForename(command.getMotherName().getForename());
        name.setSurename(command.getMotherName().getSurename());
        employee.setMotherName(name);

        return EmployeeMapper.INSTANCE.employeeToEmployeeDto(employeeRepository.save(employee));
    }

    public List<EmployeeDto> listEmployees() {
        return EmployeeMapper.INSTANCE.employeesToEmployeeDtos(employeeRepository.findAll());
    }
}
