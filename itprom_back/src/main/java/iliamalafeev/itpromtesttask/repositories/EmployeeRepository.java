package iliamalafeev.itpromtesttask.repositories;

import iliamalafeev.itpromtesttask.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
