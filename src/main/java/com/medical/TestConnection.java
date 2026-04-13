package com.medical;

import com.medical.dao.JPAUtil;

import javax.persistence.EntityManager;

public class TestConnection {

    public static void main(String[] args) {

        try {
            EntityManager em = JPAUtil.getEntityManager();
            System.out.println("✅ Connexion JPA OK");
            em.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}