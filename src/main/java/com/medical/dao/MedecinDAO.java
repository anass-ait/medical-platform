package com.medical.dao;

import com.medical.model.Medecin;
import com.medical.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class MedecinDAO {

    public List<Medecin> getAllMedecins() {
        EntityManager em = JPAUtil.getEntityManager();

        return em.createQuery("SELECT m FROM Medecin m", Medecin.class)
                .getResultList();
    }
}