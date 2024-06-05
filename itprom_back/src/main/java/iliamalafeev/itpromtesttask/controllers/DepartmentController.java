package iliamalafeev.itpromtesttask.controllers;

import iliamalafeev.itpromtesttask.dto.DepartmentDTO;
import iliamalafeev.itpromtesttask.services.DepartmentService;
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
@RequestMapping("/api/departments")
@Tag(name = "Department Controller")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Operation(summary = "Get list of departments.",
            description = "Returns a list containing DepartmentDTO objects.")
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> findAll() {

        List<DepartmentDTO> responseBody = departmentService.findAll();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Get department by it's ID.",
            description = "Returns a JSON value of type DepartmentDTO.")
    @GetMapping("/{departmentId}")
    public ResponseEntity<DepartmentDTO> findById(@PathVariable("departmentId") Long departmentId) {

        DepartmentDTO responseBody = departmentService.findById(departmentId);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Add new department to DataBase.",
            description = "Requires a DepartmentDTO object as a request body.")
    @PostMapping("/add-department")
    public ResponseEntity<DepartmentDTO> addDepartment(@RequestBody @Valid DepartmentDTO departmentDTO,
                                                       BindingResult bindingResult) {

        DepartmentDTO responseBody = departmentService.addDepartment(departmentDTO, bindingResult);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Operation(summary = "Update department in DataBase.",
            description = "Requires a DepartmentDTO object as a request body.")
    @PutMapping("/update-department")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid DepartmentDTO departmentDTO,
                                                          BindingResult bindingResult) {

        DepartmentDTO responseBody = departmentService.updateDepartment(departmentDTO, bindingResult);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Delete a department from a DataBase.",
            description = "Permanently deletes a department entity from a DataBase.")
    @DeleteMapping("/delete-department/{departmentId}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("departmentId") Long departmentId) {

        departmentService.deleteDepartmentById(departmentId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
