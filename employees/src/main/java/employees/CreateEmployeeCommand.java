package employees;

import lombok.Data;

@Data
public class CreateEmployeeCommand {
    private Name employeeName;
    private Name motherName;
}
