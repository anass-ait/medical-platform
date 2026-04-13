package com.medical.controller;

import com.medical.dao.MedecinDAO;
import com.medical.model.Medecin;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

@ManagedBean(name = "medecinController")
@RequestScoped
public class MedecinController {

    public List<Medecin> getMedecins() {
        return new MedecinDAO().getAllMedecins();
    }
}