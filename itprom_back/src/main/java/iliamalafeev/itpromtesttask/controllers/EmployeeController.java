package iliamalafeev.itpromtesttask.controllers;

import iliamalafeev.itpromtesttask.dto.EmployeeDTO;
import iliamalafeev.itpromtesttask.services.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:8081/")
@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Controller")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get list of employees.",
            description = "Returns a list containing EmployeeDTO objects.")
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> findAll() {

        List<EmployeeDTO> responseBody = employeeService.findAll();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Get employee by it's ID.",
            description = "Returns a JSON value of type EmployeeDTO.")
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDTO> findById(@PathVariable("employeeId") Long employeeId) {

        EmployeeDTO responseBody = employeeService.findById(employeeId);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Add new employee to DataBase.",
            description = "Requires a EmployeeDTO object as a request body.")
    @PostMapping("/add-employee")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody @Valid EmployeeDTO employeeDTO,
                                                   BindingResult bindingResult) {

        EmployeeDTO responseBody = employeeService.addEmployee(employeeDTO, bindingResult);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Operation(summary = "Update employee in DataBase.",
            description = "Requires an EmployeeDTO object as a request body.")
    @PutMapping("/update-employee")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO,
                                                      BindingResult bindingResult) {

        EmployeeDTO responseBody = employeeService.updateEmployee(employeeDTO, bindingResult);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Delete an employee from a DataBase.",
            description = "Permanently deletes an employee entity from a DataBase.")
    @DeleteMapping("/delete-employee/{employeeId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("employeeId") Long employeeId) {

        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
