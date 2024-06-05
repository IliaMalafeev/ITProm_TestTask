package iliamalafeev.itpromtesttask.services;

import iliamalafeev.itpromtesttask.dto.ProfessionDTO;
import iliamalafeev.itpromtesttask.entities.Profession;
import iliamalafeev.itpromtesttask.repositories.ProfessionRepository;
import iliamalafeev.itpromtesttask.utils.ErrorsUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessionService {

    private final ModelMapper modelMapper;
    private final ProfessionRepository professionRepository;

    @Autowired
    public ProfessionService(ModelMapper modelMapper, ProfessionRepository professionRepository) {
        this.modelMapper = modelMapper;
        this.professionRepository = professionRepository;
    }

//  <------------------------------------------------------------------------------->
//  <-------------------- Service public methods for controller -------------------->
//  <------------------------------------------------------------------------------->

    public List<ProfessionDTO> findAll() {

        List<Profession> professions = professionRepository.findAll();

        return professions.stream().map(this::convertToProfessionDTO).toList();
    }

    public ProfessionDTO findById(Long id) {

        Profession profession = professionRepository.findById(id).orElse(null);

        return convertToProfessionDTO(profession);
    }

    public ProfessionDTO addProfession(ProfessionDTO professionDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnProfessionError("Some fields are invalid.", bindingResult, HttpStatus.FORBIDDEN);
        }

        Profession profession = convertToProfession(professionDTO);

        Profession savedProfession = professionRepository.save(profession);

        return convertToProfessionDTO(savedProfession);
    }

    public ProfessionDTO updateProfession(ProfessionDTO professionDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnProfessionError("Some fields are invalid.", bindingResult, HttpStatus.FORBIDDEN);
        }

        Optional<Profession> profession = professionRepository.findById(professionDTO.getId());

        if (profession.isEmpty()) {
            ErrorsUtil.returnProfessionError("Profession not found", null, HttpStatus.NOT_FOUND);
        }

        Profession professionToUpdate = profession.get();

        professionToUpdate.setTitle(professionDTO.getTitle());
        professionToUpdate.setDescription(professionDTO.getDescription());

        return convertToProfessionDTO(professionRepository.save(professionToUpdate));
    }

    public void deleteProfessionById(Long id) {

        professionRepository.deleteById(id);
    }

//  <-------------------------------------------------------------------------------------------->
//  <-------------------- Service private methods for some code re-usability -------------------->
//  <-------------------------------------------------------------------------------------------->

    private Profession convertToProfession(ProfessionDTO professionDTO) {
        return modelMapper.map(professionDTO, Profession.class);
    }

    private ProfessionDTO convertToProfessionDTO(Profession profession) {
        return profession == null ? null : modelMapper.map(profession, ProfessionDTO.class);
    }
}
