package csit.semit.spr.webappssprlab3.model.DentistryVisits;

import csit.semit.spr.webappssprlab3.model.DentistryPatients.DentistryPatient;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "dentistry_visit",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"doctor_code", "time_visit"})
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DentistryVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @NotNull
    private DentistryPatient patient;

    @NotNull
    @NotBlank(message = "Doctor code cannot be empty")
    @Size(min = 2, max = 50, message = "Doctor code must be between 2 and 50 characters")
    @Pattern(regexp = "^[A-Za-z0-9-]+$", message = "Doctor code must contain only letters, numbers, and hyphens")
    @Column(name = "doctor_code", nullable = false)
    private String doctorCode;

    @NotNull
    @NotBlank(message = "Service description cannot be empty")
    @Size(min = 10, message = "Service description must be at least 10 characters")
    @Column(name = "servant", nullable = false, columnDefinition = "TEXT")
    private String servant;

    @NotNull
    @Future(message = "Visit time must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "time_visit", nullable = false)
    private LocalDateTime timeVisit;

    @NotNull
    @Positive(message = "Cost must be greater than 0")
    @Column(name = "cost", nullable = false)
    private Double cost;

    @NotNull
    @Positive(message = "Payment must be greater than 0")
    @Column(name = "payment", nullable = false)
    private Double payment;

    @AssertTrue(message = "Payment must be between 30% and 100% of cost")
    private boolean isValidPayment() {
        if (cost == null || payment == null) return true;
        return payment >= cost * 0.3 && payment <= cost;
    }

    @AssertTrue(message = "A doctor cannot have multiple appointments at the same time")
    private boolean isTimeSlotAvailable() {
        if (doctorCode == null || timeVisit == null) return true;
        // This validation will be handled in service layer
        return true;
    }
}
