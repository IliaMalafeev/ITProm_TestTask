package iliamalafeev.itpromtesttask.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Title must contain at least 1 character")
    @Size(max = 100, message = "Title length must not exceed 100 characters")
    @Column(name = "title")
    private String title;

    @NotBlank(message = "Ifo must contain at least 1 character")
    @Size(max = 100, message = "Info length must not exceed 100 characters")
    @Column(name = "additional_info")
    private String additionalInfo;

    @ManyToOne
    @JoinColumn(name = "parent_department_id", referencedColumnName = "id")
    private Department parentDepartment;

    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Employee> employees;
}
