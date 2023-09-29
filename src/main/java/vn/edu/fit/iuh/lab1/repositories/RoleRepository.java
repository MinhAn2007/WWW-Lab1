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
        return entityManager.createQuery("select DISTINCT r.roleName from Role r", String.class).getResultList();
    }

}
