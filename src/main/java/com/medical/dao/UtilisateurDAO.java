package com.medical.dao;

import com.medical.model.Utilisateur;
import com.medical.util.JPAUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class UtilisateurDAO {

    public Utilisateur findByEmail(String email) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            return em.createQuery(
                            "SELECT u FROM Utilisateur u WHERE u.email = :email",
                            Utilisateur.class)
                    .setParameter("email", email)
                    .getSingleResult();

        } finally {
            em.close();
        }
    }
    public void save(Utilisateur u) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Utilisateur login(String email, String motDePasse) {

        EntityManager em = JPAUtil.getEntityManager();

        try {
            List<Utilisateur> list = em.createQuery(
                            "SELECT u FROM Utilisateur u WHERE u.email = :email",
                            Utilisateur.class)
                    .setParameter("email", email)
                    .getResultList();

            if (list.isEmpty()) return null;

            Utilisateur u = list.get(0);

            // 🔥 vérification manuelle (plus stable)
            if (u.getMotDePasse().equals(motDePasse)) {
                return u;
            }

            return null;

        } finally {
            em.close();
        }
    }
}