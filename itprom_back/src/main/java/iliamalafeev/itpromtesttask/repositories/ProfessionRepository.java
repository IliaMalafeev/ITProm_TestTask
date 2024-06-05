package iliamalafeev.itpromtesttask.repositories;

import iliamalafeev.itpromtesttask.entities.Profession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionRepository extends JpaRepository<Profession, Long> {
}
