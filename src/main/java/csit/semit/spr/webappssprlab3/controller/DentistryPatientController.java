package csit.semit.spr.webappssprlab3.controller;


import csit.semit.spr.webappssprlab3.model.DentistryPatients.DentistryPatient;
import csit.semit.spr.webappssprlab3.model.DentistryVisits.DentistryVisit;
import csit.semit.spr.webappssprlab3.servicies.DentistryPatientService;
import csit.semit.spr.webappssprlab3.servicies.DentistryVisit.DentistryVisitService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/patients")
public class DentistryPatientController {
    private final DentistryPatientService patientService;
    private final DentistryVisitService visitService;

    public DentistryPatientController(DentistryPatientService patientService, DentistryVisitService visitService) {
        this.patientService = patientService;
        this.visitService = visitService;
    }


    @GetMapping
    public String showTable(Model model) {
        model.addAttribute("patients", patientService.getAllPatients());
        System.out.println(patientService.getAllPatients());
        model.addAttribute("showDot", "p");
        return "MyTable";
    }


    @GetMapping("/new")
    public String showNewPatientForm(Model model) {
        model.addAttribute("formData", new DentistryPatient());
        model.addAttribute("today", LocalDate.now());
        return "AddPatient";
    }

    @PostMapping("/new")
    public String addNewPatient(@Valid @ModelAttribute("formData") DentistryPatient patient,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("today", LocalDate.now());
            return "AddPatient";
        }
        try {
            if (patientService.isDuplicate(patient)) {
                model.addAttribute("duplication", "Patient already exists");
                model.addAttribute("today", LocalDate.now());
                return "AddPatient";
            }
            patientService.savePatient(patient);
            return "redirect:/patients";
        } catch (Exception e) {
            model.addAttribute("errors", Map.of("error", e.getMessage()));
            model.addAttribute("today", LocalDate.now());
            return "AddPatient";
        }
    }
    @PostMapping("/add")
    public String addPatient(@ModelAttribute DentistryPatient patient) {
        patientService.savePatient(patient);
        return "redirect:/patients";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        DentistryPatient patient = patientService.getPatientById(id);
        if (patient == null) {
            return "redirect:/patients";
        }
        System.out.println("Patient DR: " + patient.getDr()); //
        model.addAttribute("patient", patient);
        model.addAttribute("today", LocalDate.now());
        return "UpdatePatient";
    }

    @PostMapping("/update")
    public String updatePatient(@Valid @ModelAttribute("patient") DentistryPatient patient,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("today", LocalDate.now());
            return "UpdatePatient";
        }

        try {
            if (patientService.isDuplicate(patient)) {
                model.addAttribute("duplication", "Patient already exists");
                model.addAttribute("today", LocalDate.now());
                return "UpdatePatient";
            }

            patientService.updatePatient(patient);
            return "redirect:/patients";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("today", LocalDate.now());
            return "UpdatePatient";
        }
    }
    @GetMapping("/{id}/appointments")
    public String showPatientAppointments(@PathVariable Long id, Model model) {
        DentistryPatient patient = patientService.getPatientById(id);
        if (patient == null) {
            // Handle patient not found, e.g., redirect with an error message
            return "redirect:/patients?error=PatientNotFound";
        }

        List<DentistryVisit> appointments = visitService.getAppointmentsByPatientId(id);

        model.addAttribute("patient", patient);
        model.addAttribute("appointments", appointments);
        return "DentistryVisit/PatientAppointments"; // Thymeleaf template path
    }
    @PostMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "redirect:/patients";
    }

    @ModelAttribute("newPatient")
    public DentistryPatient getDefaultPatient() {
        return new DentistryPatient();
    }
}