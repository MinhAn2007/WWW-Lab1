package vn.edu.fit.iuh.lab1.repositories;

import jakarta.persistence.*;
import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.models.Grant;
import vn.edu.fit.iuh.lab1.models.GrantAccess;

import java.util.List;
import java.util.Optional;

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
            return false;
        }
    }
    public List<GrantAccess> getAll() {
        return entityManager.createNamedQuery("GrantAccess.findAll", GrantAccess.class).getResultList();
    }
    public boolean update(GrantAccess grantAccess) {
        try {
            entityTransaction.begin();
            entityManager.merge(grantAccess);
            entityTransaction.commit();
            return true;
        } catch (Exception ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            return false;
        }
    }
    public boolean delete(String roleId , String accountId, Grant grant) {
        try {
            entityTransaction.begin();
            entityTransaction.commit();
            Optional<GrantAccess> OpgrantAccess = findGrant(roleId,accountId);
            GrantAccess grantAccess = OpgrantAccess.orElseThrow(() -> new IllegalStateException("Grant not found"));
            if (grantAccess != null) grantAccess.setGrant(grant);
            return true;
        } catch (Exception ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            return false;
        }
    }
    public Optional<GrantAccess> findGrant (String roleId , String accountId){
        TypedQuery<GrantAccess> query = entityManager.createQuery("select ga from GrantAccess ga where ga.account_id=:accountId and ga.role_id=:roleId ", GrantAccess.class);
        query.setParameter("accountId", accountId);
        query.setParameter("roleId", roleId);
        GrantAccess grantAccess = query.getSingleResult();
        return grantAccess == null ? Optional.empty() : Optional.of(grantAccess);
    }
}
