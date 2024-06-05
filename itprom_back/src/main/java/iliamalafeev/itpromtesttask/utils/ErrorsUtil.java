package iliamalafeev.itpromtesttask.utils;

import iliamalafeev.itpromtesttask.utils.exceptions.DepartmentException;
import iliamalafeev.itpromtesttask.utils.exceptions.EmployeeException;
import iliamalafeev.itpromtesttask.utils.exceptions.ProfessionException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {

    public static void returnDepartmentError(String generalMessage, BindingResult bindingResult, HttpStatus httpStatus) {

        throw new DepartmentException(buildErrorMessage(generalMessage, bindingResult), httpStatus);
    }

    public static void returnEmployeeError(String generalMessage, BindingResult bindingResult, HttpStatus httpStatus) {

        throw new EmployeeException(buildErrorMessage(generalMessage, bindingResult), httpStatus);
    }

    public static void returnProfessionError(String generalMessage, BindingResult bindingResult, HttpStatus httpStatus) {

        throw new ProfessionException(buildErrorMessage(generalMessage, bindingResult), httpStatus);
    }

    private static String buildErrorMessage(String generalMessage, BindingResult bindingResult) {

        StringBuilder errorMessage = new StringBuilder();

        if (bindingResult != null) {

            List<FieldError> errors = bindingResult.getFieldErrors();

            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                        .append("; ");
            }

        }

        return generalMessage + " " + errorMessage;
    }
}
