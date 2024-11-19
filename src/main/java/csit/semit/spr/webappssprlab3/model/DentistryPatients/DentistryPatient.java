package csit.semit.spr.webappssprlab3.model.DentistryPatients;

import csit.semit.spr.webappssprlab3.model.RegexMasks;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "dentistry_patient",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"surname", "name","pname"})
        }

)
//@Check(constraints = "dr <= CURRENT_DATE")
////@Check(constraints = "REGEXP_LIKE(surname, '"+ RegexMasks.REGEX_NAMES +"','c') = 1")
//@Check(constraints = "REGEXP_LIKE(name, '"+ RegexMasks.REGEX_NAMES +"','c') = 1")
//@Check(constraints = "REGEXP_LIKE(pname, '"+ RegexMasks.REGEX_NAMES +"','c') = 1")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DentistryPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull()
    @NotBlank(message = "Surname cannot be empty")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    @Pattern(regexp = RegexMasks.REGEX_NAMES, message = RegexMasks.REGEX_NAMES_MESSAGE)
    @Column(name = "surname", nullable = false)
    @Check(constraints = "REGEXP_LIKE(surname, '"+ RegexMasks.REGEX_NAMES +"','c') = 1")
    private String surname;

    @NotNull()
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters")
    @Pattern(regexp = RegexMasks.REGEX_NAMES, message = RegexMasks.REGEX_NAMES_MESSAGE)
    @Check(constraints = "REGEXP_LIKE(name, '"+ RegexMasks.REGEX_NAMES +"','c') = 1")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull()
    @NotBlank(message = "Father's name cannot be empty")
    @Size(min = 2, max = 30, message = "Father's name must be between 2 and 30 characters")
    @Pattern(regexp = RegexMasks.REGEX_NAMES, message = RegexMasks.REGEX_NAMES_MESSAGE)
    @Column(name = "pname", nullable = false)
    @Check(constraints = "REGEXP_LIKE(pname, '"+ RegexMasks.REGEX_NAMES +"','c') = 1")
    private String pname;

    @NotNull()
    @Past(message = "Date of birth must be in the past")
    @Column(name = "dr", nullable = false)
    private LocalDate dr;

    @NotNull()
    @Column(name = "gender", nullable = false)
    private Boolean gender;

    @Column(name = "stateComments", columnDefinition = "TEXT")
    private String stateComments;

    public String getGenderAsString() {
        return gender ? "male" : "female";
    }


    @AssertTrue(message = "Patient must be at least 18 years old")
    private boolean isAdult() {
        if (dr == null) return true; // Let @NotNull handle null validation
        return LocalDate.now().minusYears(18).isAfter(dr);
    }
}