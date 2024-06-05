package iliamalafeev.itpromtesttask.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Name must contain at least 1 character")
    @Size(max = 100, message = "Name length must not exceed 100 characters")
    @Column(name = "full_name")
    private String fullName;

    @NotBlank(message = "Info must contain at least 1 character")
    @Size(max = 100, message = "Info length must not exceed 100 characters")
    @Column(name = "additional_info")
    private String additionalInfo;

    @NotNull(message = "Profession must be assigned")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profession_id", referencedColumnName = "id")
    @JsonIgnoreProperties("employees")
    private Profession profession;

    @NotNull(message = "Department must be assigned")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @JsonIgnoreProperties("employees")
    private Department department;
}
