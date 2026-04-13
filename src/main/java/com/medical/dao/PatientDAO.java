package com.medical.dao;

import com.medical.model.Patient;
import com.medical.util.JPAUtil;

import javax.persistence.EntityManager;

public class PatientDAO {

    public void save(Patient patient) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(patient);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public Patient getByUtilisateurId(Long userId) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT p FROM Patient p WHERE p.utilisateur.idUtilisateur = :id",
                            Patient.class)
                    .setParameter("id", userId)
                    .getSingleResult();
        } finally {
            em.close();
        }
    }
}