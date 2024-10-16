package com.example.contacts.service;

import com.example.contacts.dao.ContactDAO;
import com.example.contacts.entity.Contact;
import jakarta.ejb.*;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import java.util.List;

@Stateless
public class ContactService {

    @Inject
    private ContactDAO contactDAO;

    public List<Contact> getAllContacts() {
        return contactDAO.findAll();
    }

    public Contact getContact(Long id) {
        return contactDAO.findById(id);
    }

    public void saveContact(Contact contact) {
        contactDAO.save(contact);
    }

    public void updateContact(Contact contact) throws OptimisticLockException {
        try {
            contactDAO.update(contact);
        } catch (EJBTransactionRolledbackException e) {
//            System.out.println("ContactService: e = " + e.getClass());
//            System.out.println("ContactService: e.getCause() = " + e.getCause().getClass());
            if (e.getCause() instanceof OptimisticLockException cause) {
                throw cause;
            } else {
                throw e;
            }
        }
    }

    public void deleteContact(Long id) {
        contactDAO.delete(id);
    }

}
