package csit.semit.spr.webappssprlab3.repositories;

import csit.semit.spr.webappssprlab3.model.DentistryPatients.DentistryPatient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DentistryPatientRepository extends JpaRepository<DentistryPatient, Long> {
    @Query("SELECT p FROM DentistryPatient p WHERE p.surname = :surname AND p.name = :name AND p.dr = :dr")
    Optional<DentistryPatient> findByKey(@Param("surname") String surname,
                                         @Param("name") String name,
                                         @Param("dr") String dr);

    boolean existsBySurnameAndNameAndPnameAndIdNot(String surname, String name, String pname, Long id);
    boolean existsBySurnameAndNameAndPname(String surname, String name, String pname);
}
