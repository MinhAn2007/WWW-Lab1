package vn.edu.fit.iuh.lab1.repositories;


import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.fit.iuh.lab1.models.Account;

import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    @Inject
    private AccountRepository accountRepository;
    @Inject
    private RoleRepository roleRepository;

    public AccountRepository() {
        entityManager = Persistence.createEntityManagerFactory("lab1").createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public void insert(Account acc) {
        try {
            entityTransaction.begin();
            entityManager.persist(acc);
            entityTransaction.commit();
        } catch (Exception ex) {
            entityTransaction.rollback();
        }
    }

    public Optional<Account> login (String id,String pw){
        Account account = entityManager.find(Account.class,id);
        if (account != null){
            if (account.getPassword().equals(pw))
                return Optional.of(account);
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
    public List<Account> getAccByRole(String nameRole) {
        //select * from mydb.account a join mydb.grantaccess g on a.ACCOUNT_ID = g.ACCOUNT_ID join  mydb.`role` r on r.ROLE_ID = g.ROLE_ID where r.ROLENAME = 'user'
        TypedQuery<Account> query = entityManager.createQuery("select a from Account a join GrantAccess g on a.account_id = g.account_id join Role r on r.role_id = g.role_id where r.roleName =:nameRole", Account.class);
        query.setParameter("nameRole", nameRole);
        return query.getResultList();
    }


}