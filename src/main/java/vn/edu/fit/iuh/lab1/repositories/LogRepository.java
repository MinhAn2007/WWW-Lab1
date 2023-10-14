package vn.edu.fit.iuh.lab1.repositories;

import jakarta.persistence.*;
import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.models.Logs;

import java.util.List;
import java.util.Optional;

public class LogRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public LogRepository() {
        entityManager = Persistence.createEntityManagerFactory("lab1").createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }


    public Optional<Logs> findbyId(Long id) {
        TypedQuery<Logs> query = entityManager.createQuery("select l from Logs l where l.id=:id", Logs.class);
        query.setParameter("id", id);
        Logs logs = query.getSingleResult();
        return logs == null ? Optional.empty() : Optional.of(logs);
    }


    public boolean logLogout(Logs logoutLog) {
        try {
            entityTransaction.begin();
            entityManager.persist(logoutLog);
            entityTransaction.commit();
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }
    public List<Logs> getAllLogs() {
        return entityManager.createNamedQuery("Logs.findAll", Logs.class).getResultList();
    }

    public boolean delete(Long logs_id, String note) {
        try {
            entityTransaction.begin();
            Logs logs = entityManager.find(Logs.class, logs_id);
            if (logs != null) logs.setNote(note);
            entityTransaction.commit();
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
}
