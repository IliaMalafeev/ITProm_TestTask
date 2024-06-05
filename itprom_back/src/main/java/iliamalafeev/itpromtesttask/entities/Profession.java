package iliamalafeev.itpromtesttask.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "profession")
public class Profession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Title must contain at least 1 character")
    @Size(max = 100, message = "Title length must not exceed 100 characters")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Description must contain at least 1 character")
    @Size(max = 100, message = "Description length must not exceed 100 characters")
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "profession", fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Employee> employees;
}
