package csit.semit.spr.webappssprlab3.servicies;


import csit.semit.spr.webappssprlab3.model.DentistryPatients.DentistryPatient;
import csit.semit.spr.webappssprlab3.repositories.DentistryPatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DentistryPatientServiceImpl implements DentistryPatientService {
    private final DentistryPatientRepository patientRepository;

    public DentistryPatientServiceImpl(DentistryPatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public List<DentistryPatient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public DentistryPatient getPatientById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    @Override
    public DentistryPatient savePatient(DentistryPatient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public DentistryPatient findByKey(DentistryPatient template) {
        return patientRepository.findByKey(
                template.getSurname(),
                template.getName(),
                String.valueOf(template.getDr())
        ).orElse(null);
    }

    @Override
    public DentistryPatient updatePatient(DentistryPatient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public boolean isDuplicate(DentistryPatient patient) {
        if (patient.getId() != null) {
            return patientRepository.existsBySurnameAndNameAndPnameAndIdNot(
                    patient.getSurname(),
                    patient.getName(),
                    patient.getPname(),
                    patient.getId()
            );
        }
        return patientRepository.existsBySurnameAndNameAndPname(
                patient.getSurname(),
                patient.getName(),
                patient.getPname()
        );
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}