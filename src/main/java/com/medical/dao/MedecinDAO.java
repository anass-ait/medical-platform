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

    public Medecin getByUtilisateurId(Long userId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT m FROM Medecin m WHERE m.utilisateur.idUtilisateur = :id",
                            Medecin.class)
                    .setParameter("id", userId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}