package iliamalafeev.itpromtesttask.controllers;

import iliamalafeev.itpromtesttask.dto.ProfessionDTO;
import iliamalafeev.itpromtesttask.services.ProfessionService;
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
@RequestMapping("/api/professions")
@Tag(name = "Profession Controller")
public class ProfessionController {

    private final ProfessionService professionService;

    @Autowired
    public ProfessionController(ProfessionService professionService) {
        this.professionService = professionService;
    }

    @Operation(summary = "Get list of professions.",
            description = "Returns a list containing ProfessionDTO objects.")
    @GetMapping
    public ResponseEntity<List<ProfessionDTO>> findAll() {

        List<ProfessionDTO> responseBody = professionService.findAll();
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Get profession by it's ID.",
            description = "Returns a JSON value of type ProfessionDTO.")
    @GetMapping("/{professionId}")
    public ResponseEntity<ProfessionDTO> findById(@PathVariable("professionId") Long professionId) {

        ProfessionDTO responseBody = professionService.findById(professionId);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Add new profession to DataBase.",
            description = "Requires a ProfessionDTO object as a request body.")
    @PostMapping("/add-profession")
    public ResponseEntity<ProfessionDTO> addProfession(@RequestBody @Valid ProfessionDTO professionDTO,
                                                       BindingResult bindingResult) {

        ProfessionDTO responseBody = professionService.addProfession(professionDTO, bindingResult);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    @Operation(summary = "Update profession in DataBase.",
            description = "Requires a ProfessionDTO object as a request body.")
    @PutMapping("/update-profession")
    public ResponseEntity<ProfessionDTO> updateProfession(@RequestBody @Valid ProfessionDTO professionDTO,
                                                          BindingResult bindingResult) {

        ProfessionDTO responseBody = professionService.updateProfession(professionDTO, bindingResult);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @Operation(summary = "Delete a profession from a DataBase.",
            description = "Permanently deletes a profession entity from a DataBase.")
    @DeleteMapping("/delete-profession/{professionId}")
    public ResponseEntity<HttpStatus> deleteProfession(@PathVariable("professionId") Long professionId) {

        professionService.deleteProfessionById(professionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
