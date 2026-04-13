package com.medical.controller;

import com.medical.dao.PatientDAO;
import com.medical.dao.UtilisateurDAO;
import com.medical.model.Patient;
import com.medical.model.Utilisateur;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class RegisterController {

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String role;

    public String register() {

        try {
            UtilisateurDAO utilisateurDAO = new UtilisateurDAO();

            // 🔥 1. créer utilisateur
            Utilisateur u = new Utilisateur();
            u.setNom(nom);
            u.setPrenom(prenom);
            u.setEmail(email);
            u.setMotDePasse(motDePasse);
            // 🔥 FIX CRITIQUE
            if (role == null || role.isEmpty()) {
                role = "PATIENT";
            }

            u.setRole(role);
            System.out.println("ROLE = " + role);

            utilisateurDAO.save(u);

            System.out.println("USER CREATED ✅");

            // 🔥 2. RECHARGER depuis DB (TRÈS IMPORTANT)
            // 🔥 création patient
            if ("PATIENT".equalsIgnoreCase(role)) {

                Utilisateur savedUser = utilisateurDAO.findByEmail(email);

                Patient p = new Patient();
                p.setUtilisateur(savedUser);

                new PatientDAO().save(p);

                System.out.println("PATIENT CREATED ✅");
            }

            return "login.xhtml?faces-redirect=true";

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // getters setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}