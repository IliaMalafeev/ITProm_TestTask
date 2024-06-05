package iliamalafeev.itpromtesttask.services;

import iliamalafeev.itpromtesttask.dto.DepartmentDTO;
import iliamalafeev.itpromtesttask.entities.Department;
import iliamalafeev.itpromtesttask.repositories.DepartmentRepository;
import iliamalafeev.itpromtesttask.utils.ErrorsUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final ModelMapper modelMapper;
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(ModelMapper modelMapper, DepartmentRepository departmentRepository) {
        this.modelMapper = modelMapper;
        this.departmentRepository = departmentRepository;
    }

//  <------------------------------------------------------------------------------->
//  <-------------------- Service public methods for controller -------------------->
//  <------------------------------------------------------------------------------->

    public List<DepartmentDTO> findAll() {

        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map(this::convertToDepartmentDTO).toList();
    }

    public DepartmentDTO findById(Long id) {

        Department department = departmentRepository.findById(id).orElse(null);

        return convertToDepartmentDTO(department);
    }

    public DepartmentDTO addDepartment(DepartmentDTO departmentDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnDepartmentError("Some fields are invalid.", bindingResult, HttpStatus.FORBIDDEN);
        }

        Department department = convertToDepartment(departmentDTO);

        Department savedDepartment = departmentRepository.save(department);

        return convertToDepartmentDTO(savedDepartment);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnDepartmentError("Some fields are invalid.", bindingResult, HttpStatus.FORBIDDEN);
        }

        Optional<Department> department = departmentRepository.findById(departmentDTO.getId());

        if (department.isEmpty()) {
            ErrorsUtil.returnDepartmentError("Department not found", null, HttpStatus.NOT_FOUND);
        }

        Department departmentToUpdate = department.get();

        departmentToUpdate.setTitle(departmentDTO.getTitle());
        departmentToUpdate.setAdditionalInfo(departmentDTO.getAdditionalInfo());

        return convertToDepartmentDTO(departmentRepository.save(departmentToUpdate));
    }

    public void deleteDepartmentById(Long id) {

        departmentRepository.deleteById(id);
    }

//  <-------------------------------------------------------------------------------------------->
//  <-------------------- Service private methods for some code re-usability -------------------->
//  <-------------------------------------------------------------------------------------------->

    private Department convertToDepartment(DepartmentDTO departmentDTO) {
        return modelMapper.map(departmentDTO, Department.class);
    }

    private DepartmentDTO convertToDepartmentDTO(Department department) {
        return department == null ? null : modelMapper.map(department, DepartmentDTO.class);
    }
}
