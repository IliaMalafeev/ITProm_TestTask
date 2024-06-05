package iliamalafeev.itpromtesttask.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProfessionDTO {

    private Long id;

    @NotBlank(message = "Title must contain at least 1 character")
    @Size(max = 100, message = "Title length must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Description must contain at least 1 character")
    @Size(max = 100, message = "Description length must not exceed 100 characters")
    private String description;
}
