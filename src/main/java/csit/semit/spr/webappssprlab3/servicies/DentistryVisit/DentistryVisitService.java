package csit.semit.spr.webappssprlab3.servicies.DentistryVisit;

import csit.semit.spr.webappssprlab3.model.DentistryVisits.DentistryVisit;

import java.util.List;

public interface DentistryVisitService {
    List<DentistryVisit> getAllVisits();
    DentistryVisit getVisitById(Long id);
    DentistryVisit saveVisit(DentistryVisit visit);
    DentistryVisit updateVisit(DentistryVisit visit);
    boolean isDuplicate(DentistryVisit visit);
    void deleteVisit(Long id);
    List<DentistryVisit> getAppointmentsByPatientId(Long patientId);

}
