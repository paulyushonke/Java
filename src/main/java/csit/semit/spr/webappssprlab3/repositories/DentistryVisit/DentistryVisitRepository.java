package csit.semit.spr.webappssprlab3.repositories.DentistryVisit;

import csit.semit.spr.webappssprlab3.model.DentistryVisits.DentistryVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DentistryVisitRepository extends JpaRepository<DentistryVisit, Long> {
    boolean existsByDoctorCodeAndTimeVisit(String doctorCode, LocalDateTime timeVisit);
    boolean existsByDoctorCodeAndTimeVisitAndIdNot(String doctorCode, LocalDateTime timeVisit, Long id);
    List<DentistryVisit> findByPatientId(Long patientId);
}
