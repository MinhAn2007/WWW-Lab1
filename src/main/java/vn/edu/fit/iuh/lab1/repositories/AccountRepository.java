package vn.edu.fit.iuh.lab1.repositories;


import jakarta.inject.Inject;
import jakarta.persistence.*;
import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.models.Grant;
import vn.edu.fit.iuh.lab1.models.GrantAccess;
import vn.edu.fit.iuh.lab1.models.Role;

import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Inject
    private AccountRepository accountRepository;
    @Inject
    private GrantAccessRepository grantAccessRepository;
    @Inject
    private RoleRepository roleRepository;

    public AccountRepository() {
        entityManager = Persistence.createEntityManagerFactory("lab1").createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public boolean insert(Account account) {
        try {
            entityTransaction.begin();
            entityManager.persist(account);
            entityTransaction.commit();
            return true;

        }  catch (PersistenceException e) {
            return false;
        }
    }

    public boolean update(Account account) {
        try {
            entityTransaction.begin();
            entityManager.merge(account);
            entityTransaction.commit();
            return true;
        } catch (PersistenceException e) {
            return false;
        }
    }

    public boolean delete(String account_id, int status) {
        try {
            entityTransaction.begin();
            Account acc = entityManager.find(Account.class, account_id);
            if (acc != null) acc.setStatus(status);
            entityTransaction.commit();
            return true;
        } catch (Exception exception) {
            entityTransaction.rollback();
            return false;
        }
    }

    public Optional<Account> login(String id, String pw) {
        Account account = entityManager.find(Account.class, id);
        if (account != null) {
            if (account.getPassword().equals(pw)) return Optional.of(account);
        }
        return Optional.empty();
    }

    public List<Account> getAllAcc() {
        return entityManager.createNamedQuery("Account.findAll", Account.class).getResultList();
    }

    public Optional<Account> findbyId(String id) {
        TypedQuery<Account> query = entityManager.createQuery("select a from Account a where a.account_id=:id", Account.class);
        query.setParameter("id", id);
        Account account = query.getSingleResult();
        return account == null ? Optional.empty() : Optional.of(account);
    }

    public List<Account> getAccByRole(String roleId) {
        //select * from mydb.account a join mydb.grantaccess g on a.ACCOUNT_ID = g.ACCOUNT_ID join  mydb.`role` r on r.ROLE_ID = g.ROLE_ID where r.ROLENAME = 'user'
        TypedQuery<Account> query = entityManager.createQuery("select a from Account a join GrantAccess g on a.account_id = g.account_id join Role r on r.role_id = g.role_id where r.role_id =:roleId", Account.class);
        query.setParameter("roleId", roleId);
        return query.getResultList();
    }

    public List<String> getName() {
        return entityManager.createQuery("select DISTINCT a.account_id from Account a", String.class).getResultList();
    }
}