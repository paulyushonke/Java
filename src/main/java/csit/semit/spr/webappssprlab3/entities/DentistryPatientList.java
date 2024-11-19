package csit.semit.spr.webappssprlab3.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class DentistryPatientList extends ArrayList<DentistryPatient> {
    private static final long serialVersionUID = 1L;
    private static DentistryPatientList instance;

    private DentistryPatientList() {
    }

    public static DentistryPatientList getInstance() {
        if (instance == null) {
            instance = new DentistryPatientList();
            instance.add(new DentistryPatient(1L, "Smith", "John", "Robert", LocalDate.of(1985, 3, 15), true));
            instance.add(new DentistryPatient(2L, "Johnson", "Emma", "Marie", LocalDate.of(1990, 9, 22), false));
            instance.add(new DentistryPatient(3L, "Brown", "Michael", "James", LocalDate.of(1978, 11, 7), true));
            instance.add(new DentistryPatient(4L, "Davis", "Olivia", "Grace", LocalDate.of(1992, 6, 30), false));
            instance.add(new DentistryPatient(5L, "Wilson", "William", "Thomas", LocalDate.of(1987, 4, 18), true));
            instance.add(new DentistryPatient(6L, "Martinez", "Sophia", "Elena", LocalDate.of(1993, 12, 9), false));
            instance.add(new DentistryPatient(7L, "Anderson", "James", "Edward", LocalDate.of(1981, 8, 25), true));
            instance.add(new DentistryPatient(8L, "Taylor", "Isabella", "Rose", LocalDate.of(1989, 2, 3), false));
            instance.add(new DentistryPatient(9L, "Thomas", "Benjamin", "Alexander", LocalDate.of(1986, 7, 11), true));
            instance.add(new DentistryPatient(10L, "Garcia", "Ava", "Sophia", LocalDate.of(1994, 10, 28), false));

            // Add comments for all patients
            instance.get(0).setStateComments("Regular check-up, no major issues. Next appointment in 6 months.");
            instance.get(1).setStateComments("Requires root canal treatment on lower right molar. Scheduled for next week.");
            instance.get(2).setStateComments("Orthodontic consultation needed. Referred to Dr. Johnson for braces evaluation.");
            instance.get(3).setStateComments("Routine cleaning completed. Advised to floss more regularly.");
            instance.get(4).setStateComments("Filling on upper left premolar. Follow-up in 2 weeks to check sensitivity.");
            instance.get(5).setStateComments("Wisdom teeth extraction recommended. Scheduled for next month.");
            instance.get(6).setStateComments("Gum disease treatment ongoing. Improvement noted, continue current regimen.");
            instance.get(7).setStateComments("Cosmetic consultation for teeth whitening. Procedure scheduled for next month.");
            instance.get(8).setStateComments("Crown replacement needed on front tooth. Temporary crown placed, final fitting in 2 weeks.");
            instance.get(9).setStateComments("New patient examination completed. Overall good oral health, routine cleaning scheduled.");
        }
        return instance;
    }
}