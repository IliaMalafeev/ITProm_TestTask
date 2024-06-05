package iliamalafeev.itpromtesttask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DepartmentDTO {

    private Long id;

    @NotBlank(message = "Title must contain at least 1 character")
    @Size(max = 100, message = "Title length must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Info must contain at least 1 character")
    @Size(max = 100, message = "Info length must not exceed 100 characters")
    private String additionalInfo;

    private DepartmentDTO parentDepartment;
}
