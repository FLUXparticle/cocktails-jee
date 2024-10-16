package com.example.contacts.dao;

import com.example.contacts.entity.Contact;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ContactDAO {

    @PersistenceContext(unitName = "ContactsPU")
    private EntityManager em;

    public List<Contact> findAll() {
        TypedQuery<Contact> query = em.createQuery("SELECT c FROM Contact c", Contact.class);
        return query.getResultList();
    }

    public Contact findById(Long id) {
        return em.find(Contact.class, id);
    }

    public void save(Contact contact) {
        em.persist(contact);
    }

    public void update(Contact contact) {
//        try {
            em.merge(contact);
//        } catch (Exception e) {
//            System.out.println("ContactDAO: e = " + e.getClass());
//            System.out.println("ContactDAO: e.getCause() = " + e.getCause().getClass());
//            throw e;
//        }
    }

    public void delete(Long id) {
        Contact contact = findById(id);
        if (contact != null) {
            em.remove(contact);
        }
    }

    public List<Contact> search(String queryStr) {
        TypedQuery<Contact> query = em.createQuery(
            "SELECT c FROM Contact c WHERE c.name LIKE :query OR c.email LIKE :query OR c.address LIKE :query",
            Contact.class);
        query.setParameter("query", "%" + queryStr + "%");
        return query.getResultList();
    }

}
