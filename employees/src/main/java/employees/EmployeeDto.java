package employees;

import lombok.Data;

@Data
public class EmployeeDto {
    private long id;
    private NameDto employeeName;
    private NameDto motherName;
}
