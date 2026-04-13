package com.medical.dao;

import com.medical.model.RendezVous;
import com.medical.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class RendezVousDAO {

    public void save(RendezVous rdv) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(rdv);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // 🔥 NOUVELLE MÉTHODE
    public List<RendezVous> getByPatientId(Long patientId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT r FROM RendezVous r WHERE r.patient.idPatient = :id",
                            RendezVous.class)
                    .setParameter("id", patientId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public List<RendezVous> getByMedecinId(Long medecinId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT r FROM RendezVous r WHERE r.medecin.idMedecin = :id",
                            RendezVous.class)
                    .setParameter("id", medecinId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void update(RendezVous rdv) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(rdv);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}