package iliamalafeev.itpromtesttask.utils.error_responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentErrorResponse {

    private String message;
    private long timestamp;
}
