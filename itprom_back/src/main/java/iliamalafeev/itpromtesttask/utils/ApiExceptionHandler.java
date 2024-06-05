package iliamalafeev.itpromtesttask.utils;

import iliamalafeev.itpromtesttask.utils.error_responses.DepartmentErrorResponse;
import iliamalafeev.itpromtesttask.utils.error_responses.EmployeeErrorResponse;
import iliamalafeev.itpromtesttask.utils.error_responses.ProfessionErrorResponse;
import iliamalafeev.itpromtesttask.utils.exceptions.DepartmentException;
import iliamalafeev.itpromtesttask.utils.exceptions.EmployeeException;
import iliamalafeev.itpromtesttask.utils.exceptions.ProfessionException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DepartmentException.class)
    private ResponseEntity<DepartmentErrorResponse> handleDepartmentException(DepartmentException e) {
        DepartmentErrorResponse response = new DepartmentErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    @ExceptionHandler(EmployeeException.class)
    private ResponseEntity<EmployeeErrorResponse> handleDiscussionException(EmployeeException e) {
        EmployeeErrorResponse response = new EmployeeErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }

    @ExceptionHandler(ProfessionException.class)
    private ResponseEntity<ProfessionErrorResponse> handleGenreException(ProfessionException e) {
        ProfessionErrorResponse response = new ProfessionErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, e.getHttpStatus());
    }
}
