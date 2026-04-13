package com.medical.controller;

import com.medical.dao.UtilisateurDAO;
import com.medical.model.Utilisateur;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    private String email;
    private String motDePasse;

    private Utilisateur utilisateurConnecte;

    public String login() {

        UtilisateurDAO dao = new UtilisateurDAO();
        Utilisateur u = dao.login(email, motDePasse);

        if (u != null) {
            utilisateurConnecte = u;
            System.out.println("LOGIN SUCCESS ✅");

            return "patientDashboard.xhtml?faces-redirect=true";
        }

        System.out.println("LOGIN FAILED ❌");
        return null;
    }

    public Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMotDePasse() { return motDePasse; }
    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }
}