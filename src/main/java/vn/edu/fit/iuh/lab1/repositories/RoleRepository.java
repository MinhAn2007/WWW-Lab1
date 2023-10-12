package vn.edu.fit.iuh.lab1.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import vn.edu.fit.iuh.lab1.models.Account;
import vn.edu.fit.iuh.lab1.models.Role;
import vn.edu.fit.iuh.lab1.models.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleRepository {
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;

    public RoleRepository() {
        entityManager = Persistence.createEntityManagerFactory("lab1").createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }


    public List<String> getName() {
        return entityManager.createQuery("select DISTINCT r.role_id from Role r", String.class).getResultList();
    }

    public boolean checkRole(String id) {
        //select r.ROLENAME  from mydb.`role`r join mydb.grantaccess g on r.ROLE_ID = g.ROLE_ID join mydb.account a on g.ACCOUNT_ID =a.ACCOUNT_ID where  a.ACCOUNT_ID = 'teo'
        TypedQuery<String> query = entityManager.createQuery("select r.role_id  from Role r join GrantAccess g on r.role_id = g.role_id join Account  a on g.account_id =a.account_id where  a.account_id =:id", String.class);
        query.setParameter("id", id);
        List<String> string = query.getResultList();
        for (String s : string) {
            if (s.equalsIgnoreCase("admin")) return true;
        }
        return false;
    }

    public List<Role> getRoleOfAcc(String id) {
        //select r.ROLENAME  from mydb.`role`r join mydb.grantaccess g on r.ROLE_ID = g.ROLE_ID join mydb.account a on g.ACCOUNT_ID =a.ACCOUNT_ID where  a.ACCOUNT_ID = 'teo'
        TypedQuery<Role> query = entityManager.createQuery("select r  from Role r join GrantAccess g on r.role_id = g.role_id join Account  a on g.account_id =a.account_id where  a.account_id =:id", Role.class);
        query.setParameter("id", id);
        return query.getResultList();

    }
    public List<Role> getAll (){
        return entityManager.createNamedQuery("Account.findAll", Role.class).getResultList();
    }
    public boolean add(Role role) {
        try {
            entityTransaction.begin();
            entityManager.persist(role);
            entityTransaction.commit();
            return true;
        } catch (Exception ex) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            return false;
        }

    }

}
