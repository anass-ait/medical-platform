package com.medical.controller;

import com.medical.dao.PatientDAO;
import com.medical.dao.RendezVousDAO;
import com.medical.model.Medecin;
import com.medical.model.Patient;
import com.medical.model.RendezVous;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@ManagedBean(name = "rendezVousController")
@SessionScoped
public class RendezVousController implements Serializable {

    private String date;
    private String heure;
    private Long medecinId;

    public String goToRdv(Long id) {
        this.medecinId = id;
        return "rendezvous.xhtml?faces-redirect=true";
    }

    public String save() {
        try {

            // 🔥 récupérer user connecté
            LoginController loginController = (LoginController)
                    FacesContext.getCurrentInstance()
                            .getExternalContext()
                            .getSessionMap()
                            .get("loginController");

            Long userId = loginController.getUtilisateurConnecte().getIdUtilisateur();

            // 🔥 récupérer patient
            Patient patient = new PatientDAO().getByUtilisateurId(userId);

            // 🔥 date
            LocalDateTime dateTime = LocalDateTime.parse(date + "T" + heure);

            RendezVous rdv = new RendezVous();
            rdv.setDateHeure(dateTime);
            rdv.setStatut("EN_ATTENTE");

            // 🔥 médecin
            Medecin medecin = new Medecin();
            medecin.setIdMedecin(medecinId);

            rdv.setPatient(patient);
            rdv.setMedecin(medecin);

            new RendezVousDAO().save(rdv);

            System.out.println("RDV SAVED ✅");

            return "patientDashboard.xhtml?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 🔥 NOUVELLE MÉTHODE (dashboard)
    public List<RendezVous> getMesRendezVous() {

        LoginController loginController = (LoginController)
                FacesContext.getCurrentInstance()
                        .getExternalContext()
                        .getSessionMap()
                        .get("loginController");

        Long userId = loginController.getUtilisateurConnecte().getIdUtilisateur();

        Patient patient = new PatientDAO().getByUtilisateurId(userId);

        return new RendezVousDAO().getByPatientId(patient.getIdPatient());
    }

    // getters setters
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getHeure() { return heure; }
    public void setHeure(String heure) { this.heure = heure; }

    public Long getMedecinId() { return medecinId; }
}