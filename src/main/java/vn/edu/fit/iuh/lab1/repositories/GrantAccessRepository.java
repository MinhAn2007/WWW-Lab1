package vn.edu.fit.iuh.lab1.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.models.GrantAccess;

public class GrantAccessRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public GrantAccessRepository() {
        entityManager = Persistence.createEntityManagerFactory("lab1").createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public boolean insert(GrantAccess grantAccess) {
        try {
            entityTransaction.begin();
            entityManager.persist(grantAccess);
            entityTransaction.commit();
            return true;
        } catch (Exception ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            return false; // Hoặc xử lý ngoại lệ khác ở đây nếu cần.
        }
    }

}
