package iliamalafeev.itpromtesttask.services;

import iliamalafeev.itpromtesttask.dto.DepartmentDTO;
import iliamalafeev.itpromtesttask.dto.EmployeeDTO;
import iliamalafeev.itpromtesttask.dto.ProfessionDTO;
import iliamalafeev.itpromtesttask.entities.Department;
import iliamalafeev.itpromtesttask.entities.Employee;
import iliamalafeev.itpromtesttask.entities.Profession;
import iliamalafeev.itpromtesttask.repositories.DepartmentRepository;
import iliamalafeev.itpromtesttask.repositories.EmployeeRepository;
import iliamalafeev.itpromtesttask.repositories.ProfessionRepository;
import iliamalafeev.itpromtesttask.utils.ErrorsUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final ModelMapper modelMapper;
    private final EmployeeRepository employeeRepository;
    private final ProfessionRepository professionRepository;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public EmployeeService(ModelMapper modelMapper, EmployeeRepository employeeRepository, ProfessionRepository professionRepository, DepartmentRepository departmentRepository) {
        this.modelMapper = modelMapper;
        this.employeeRepository = employeeRepository;
        this.professionRepository = professionRepository;
        this.departmentRepository = departmentRepository;
    }

//  <------------------------------------------------------------------------------->
//  <-------------------- Service public methods for controller -------------------->
//  <------------------------------------------------------------------------------->

    public List<EmployeeDTO> findAll() {

        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            return null;
        }

        List<EmployeeDTO> response = new ArrayList<>();

        for (Employee employee : employees) {

            EmployeeDTO employeeDTO = convertToEmployeeDTO(employee);

            if (employee.getProfession() != null) {
                employeeDTO.setProfessionDTO(convertToProfessionDTO(employee.getProfession()));
            } else {
                employeeDTO.setProfessionDTO(null);
            }

            if (employee.getDepartment() != null) {
                employeeDTO.setDepartmentDTO(convertToDepartmentDTO(employee.getDepartment()));
            } else {
                employeeDTO.setDepartmentDTO(null);
            }

            response.add(employeeDTO);
        }

        return response;
    }

    public EmployeeDTO findById(Long id) {

        Employee employee = employeeRepository.findById(id).orElse(null);

        if (employee == null) {
            return null;
        }

        EmployeeDTO employeeDTO = convertToEmployeeDTO(employee);

        if (employee.getProfession() != null) {
            employeeDTO.setProfessionDTO(convertToProfessionDTO(employee.getProfession()));
        } else {
            employeeDTO.setProfessionDTO(null);
        }

        if (employee.getDepartment() != null) {
            employeeDTO.setDepartmentDTO(convertToDepartmentDTO(employee.getDepartment()));
        } else {
            employeeDTO.setDepartmentDTO(null);
        }

        return employeeDTO;
    }

    public EmployeeDTO addEmployee(EmployeeDTO employeeDTO, BindingResult bindingResult) {

        // Validate binding result to check if DTO is correct
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnEmployeeError("Some fields are invalid.", bindingResult, HttpStatus.FORBIDDEN);
        }

        // Check if profession exists in DB
        Optional<Profession> profession = professionRepository.findById(employeeDTO.getProfessionDTO().getId());

        if (profession.isEmpty()) {
            ErrorsUtil.returnProfessionError("Profession not found", null, HttpStatus.NOT_FOUND);
        }

        // Check if department exists in DB
        Optional<Department> department = departmentRepository.findById(employeeDTO.getDepartmentDTO().getId());

        if (department.isEmpty()) {
            ErrorsUtil.returnDepartmentError("Department not found", null, HttpStatus.NOT_FOUND);
        }

        // Get Employee object to save
        Employee employee = convertToEmployee(employeeDTO);
        Profession professionToAssign = profession.get();
        Department departmentToAssign = department.get();
        employee.setProfession(professionToAssign);
        employee.setDepartment(departmentToAssign);

        // Save new employee
        Employee savedEmployee = employeeRepository.save(employee);

        // Update hibernate cache and persist changes
        professionToAssign.getEmployees().add(savedEmployee);
        departmentToAssign.getEmployees().add(savedEmployee);
        professionRepository.save(professionToAssign);
        departmentRepository.save(departmentToAssign);

        // Return DTO
        EmployeeDTO savedEmployeeDTO = convertToEmployeeDTO(savedEmployee);
        savedEmployeeDTO.setProfessionDTO(convertToProfessionDTO(professionToAssign));
        savedEmployeeDTO.setDepartmentDTO(convertToDepartmentDTO(departmentToAssign));

        return savedEmployeeDTO;
    }

    public EmployeeDTO updateEmployee(EmployeeDTO employeeDTO, BindingResult bindingResult) {

        // Validate binding result to check if DTO is correct
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnEmployeeError("Some fields are invalid.", bindingResult, HttpStatus.FORBIDDEN);
        }

        // Check if employee exists in DB
        Optional<Employee> employee = employeeRepository.findById(employeeDTO.getId());

        if (employee.isEmpty()) {
            ErrorsUtil.returnEmployeeError("Employee not found", null, HttpStatus.NOT_FOUND);
        }

        // Check if profession exists in DB
        Optional<Profession> profession = professionRepository.findById(employeeDTO.getProfessionDTO().getId());

        if (profession.isEmpty()) {
            ErrorsUtil.returnProfessionError("Profession not found", null, HttpStatus.NOT_FOUND);
        }

        // Check if department exists in DB
        Optional<Department> department = departmentRepository.findById(employeeDTO.getDepartmentDTO().getId());

        if (department.isEmpty()) {
            ErrorsUtil.returnDepartmentError("Department not found", null, HttpStatus.NOT_FOUND);
        }

        // Update employee with DTO data
        Employee employeeToUpdate = employee.get();
        Profession professionToAssign = profession.get();
        Department departmentToAssign = department.get();

        employeeToUpdate.setFullName(employeeDTO.getFullName());
        employeeToUpdate.setAdditionalInfo(employeeDTO.getAdditionalInfo());
        employeeToUpdate.setDepartment(departmentToAssign);
        employeeToUpdate.setProfession(professionToAssign);

        // Save updated employee
        Employee savedEmployee = employeeRepository.save(employeeToUpdate);

        // Update hibernate cache and persist changes
        professionToAssign.getEmployees().add(savedEmployee);
        departmentToAssign.getEmployees().add(savedEmployee);
        professionRepository.save(professionToAssign);
        departmentRepository.save(departmentToAssign);

        // Return DTO
        EmployeeDTO savedEmployeeDTO = convertToEmployeeDTO(savedEmployee);
        savedEmployeeDTO.setProfessionDTO(convertToProfessionDTO(savedEmployee.getProfession()));
        savedEmployeeDTO.setDepartmentDTO(convertToDepartmentDTO(savedEmployee.getDepartment()));

        return savedEmployeeDTO;
    }

    public void deleteEmployeeById(Long id) {

        employeeRepository.deleteById(id);
    }

//  <-------------------------------------------------------------------------------------------->
//  <-------------------- Service private methods for some code re-usability -------------------->
//  <-------------------------------------------------------------------------------------------->

    private Employee convertToEmployee(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }

    private EmployeeDTO convertToEmployeeDTO(Employee employee) {
        return employee == null ? null : modelMapper.map(employee, EmployeeDTO.class);
    }

    private DepartmentDTO convertToDepartmentDTO(Department department) {
        return modelMapper.map(department, DepartmentDTO.class);
    }

    private ProfessionDTO convertToProfessionDTO(Profession profession) {
        return modelMapper.map(profession, ProfessionDTO.class);
    }
}
