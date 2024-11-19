package csit.semit.spr.webappssprlab3.servicies;

import csit.semit.spr.webappssprlab3.model.DentistryPatients.DentistryPatient;
import java.util.List;

public interface DentistryPatientService {
    List<DentistryPatient> getAllPatients();
    DentistryPatient getPatientById(Long id);
    DentistryPatient savePatient(DentistryPatient patient);
    DentistryPatient findByKey(DentistryPatient template);
    DentistryPatient updatePatient(DentistryPatient patient);
    boolean isDuplicate(DentistryPatient patient);
    void deletePatient(Long id);
}