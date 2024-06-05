package iliamalafeev.itpromtesttask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeDTO {

    private Long id;

    @NotBlank(message = "Name must contain at least 1 character")
    @Size(max = 100, message = "Name length must not exceed 100 characters")
    private String fullName;

    @NotBlank(message = "Info must contain at least 1 character")
    @Size(max = 100, message = "Info length must not exceed 100 characters")
    private String additionalInfo;

    @NotNull(message = "Profession must be assigned")
    private ProfessionDTO professionDTO;

    @NotNull(message = "Department must be assigned")
    private DepartmentDTO departmentDTO;
}
