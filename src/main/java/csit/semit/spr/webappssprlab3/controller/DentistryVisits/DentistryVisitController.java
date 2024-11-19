package csit.semit.spr.webappssprlab3.controller.DentistryVisits;

import csit.semit.spr.webappssprlab3.model.DentistryVisits.DentistryVisit;
import csit.semit.spr.webappssprlab3.servicies.DentistryVisit.DentistryVisitService;
import csit.semit.spr.webappssprlab3.servicies.DentistryPatientService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping("/visits")
public class DentistryVisitController {
    private final DentistryVisitService visitService;
    private final DentistryPatientService patientService;

    public DentistryVisitController(DentistryVisitService visitService, DentistryPatientService patientService) {
        this.visitService = visitService;
        this.patientService = patientService;
    }

    @GetMapping
    public String showTable(Model model) {
        model.addAttribute("visits", visitService.getAllVisits());
        model.addAttribute("showDot", "v");
        return "DentistryVisit/DentistryVisitTable";
    }

    @GetMapping("/new")
    public String showNewVisitForm(Model model) {
        model.addAttribute("formData", new DentistryVisit());
        model.addAttribute("patients", patientService.getAllPatients());
        model.addAttribute("today", LocalDate.now());
        return "DentistryVisit/AddVisit";
    }


    @PostMapping("/new")
    public String addNewVisit(@Valid @ModelAttribute("formData") DentistryVisit visit,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patients", patientService.getAllPatients());
            model.addAttribute("today", LocalDate.now());
            return "DentistryVisit/AddVisit";
        }
        try {
            if (visitService.isDuplicate(visit)) {
                model.addAttribute("duplication", "Visit already exists or doctor is booked for this time slot.");
                model.addAttribute("patients", patientService.getAllPatients());
                model.addAttribute("today", LocalDate.now());
                return "DentistryVisit/AddVisit";
            }
            visitService.saveVisit(visit);
            return "redirect:/visits";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("patients", patientService.getAllPatients());
            model.addAttribute("today", LocalDate.now());
            return "DentistryVisit/AddVisit";
        }
    }
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        DentistryVisit visit = visitService.getVisitById(id);
        if (visit == null) {
            return "redirect:/visits";
        }
        model.addAttribute("visit", visit);
        model.addAttribute("patients", patientService.getAllPatients());
        return "DentistryVisit/UpdateVisit";
    }

    @PostMapping("/update")
    public String updateVisit(@Valid @ModelAttribute("visit") DentistryVisit visit,
                              BindingResult bindingResult,
                              Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("patients", patientService.getAllPatients());
            return "DentistryVisit/UpdateVisit";
        }

        try {
            if (visitService.isDuplicate(visit)) {
                model.addAttribute("duplication", "Visit already exists");
                model.addAttribute("patients", patientService.getAllPatients());
                return "DentistryVisit/UpdateVisit";
            }

            visitService.updateVisit(visit);
            return "redirect:/visits";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("patients", patientService.getAllPatients());
            return "DentistryVisit/UpdateVisit";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteVisit(@PathVariable Long id) {
        visitService.deleteVisit(id);
        return "redirect:/visits";
    }

    @ModelAttribute("newVisit")
    public DentistryVisit getDefaultVisit() {
        return new DentistryVisit();
    }
}
