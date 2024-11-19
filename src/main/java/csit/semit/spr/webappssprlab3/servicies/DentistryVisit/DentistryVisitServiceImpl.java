package csit.semit.spr.webappssprlab3.servicies.DentistryVisit;

import csit.semit.spr.webappssprlab3.model.DentistryVisits.DentistryVisit;
import csit.semit.spr.webappssprlab3.repositories.DentistryVisit.DentistryVisitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DentistryVisitServiceImpl implements DentistryVisitService {

    private final DentistryVisitRepository visitRepository;

    public DentistryVisitServiceImpl(DentistryVisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    @Override
    public List<DentistryVisit> getAllVisits() {
        return visitRepository.findAll();
    }

    @Override
    public DentistryVisit getVisitById(Long id) {
        return visitRepository.findById(id).orElse(null);
    }

    @Override
    public DentistryVisit saveVisit(DentistryVisit visit) {
        if (visitRepository.existsByDoctorCodeAndTimeVisit(visit.getDoctorCode(), visit.getTimeVisit())) {
            throw new IllegalArgumentException("The doctor is already booked for this time slot.");
        }

        if (visit.getPayment() < visit.getCost() * 0.3 || visit.getPayment() > visit.getCost()) {
            throw new IllegalArgumentException("Payment must be between 30% and 100% of the cost.");
        }

        return visitRepository.save(visit);
    }
    @Override
    public List<DentistryVisit> getAppointmentsByPatientId(Long patientId) {
        return visitRepository.findByPatientId(patientId);
    }
    @Override
    public DentistryVisit updateVisit(DentistryVisit visit) {
        if (!visitRepository.existsById(visit.getId())) {
            throw new IllegalArgumentException("Visit with ID " + visit.getId() + " does not exist.");
        }

        if (visitRepository.existsByDoctorCodeAndTimeVisitAndIdNot(
                visit.getDoctorCode(),
                visit.getTimeVisit(),
                visit.getId()
        )) {
            throw new IllegalArgumentException("The doctor is already booked for this time slot.");
        }

        if (visit.getPayment() < visit.getCost() * 0.3 || visit.getPayment() > visit.getCost()) {
            throw new IllegalArgumentException("Payment must be between 30% and 100% of the cost.");
        }

        return visitRepository.save(visit);
    }

    @Override
    public boolean isDuplicate(DentistryVisit visit) {
        if (visit.getId() != null) {
            return visitRepository.existsByDoctorCodeAndTimeVisitAndIdNot(
                    visit.getDoctorCode(),
                    visit.getTimeVisit(),
                    visit.getId()
            );
        }
        return visitRepository.existsByDoctorCodeAndTimeVisit(
                visit.getDoctorCode(),
                visit.getTimeVisit()
        );
    }

    @Override
    public void deleteVisit(Long id) {
        if (!visitRepository.existsById(id)) {
            throw new IllegalArgumentException("Visit with ID " + id + " does not exist.");
        }
        visitRepository.deleteById(id);
    }
}
