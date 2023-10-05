package vn.edu.fit.iuh.lab1.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.models.Logs;

import java.util.List;

public class LogRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public LogRepository() {
        entityManager = Persistence.createEntityManagerFactory("lab1").createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public void logLogout(Logs logoutLog) {
        try {
            entityTransaction.begin();
            entityManager.persist(logoutLog);
            entityTransaction.commit();
        } catch (Exception e) {
            entityTransaction.rollback();
            // Xử lý lỗi ghi log
            e.printStackTrace();
        }
    }
    public List<Logs> getAllLogs() {
        return entityManager.createNamedQuery("Logs.findAll", Logs.class).getResultList();
    }
}
