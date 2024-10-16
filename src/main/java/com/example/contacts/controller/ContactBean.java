package com.example.contacts.controller;

import com.example.contacts.entity.Contact;
import com.example.contacts.service.ContactService;
import jakarta.enterprise.context.*;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.*;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;

import java.io.Serializable;
import java.util.List;

@Named
@RequestScoped
public class ContactBean implements Serializable {

    @Inject
    private ContactService contactService;

    private Long contactId;

    private Contact contact = new Contact();

    private List<Contact> contacts;

    // Methode, um den Kontakt anhand der ID zu laden
    public void loadContact() {
        System.out.println("loadContact(" + contactId + ")");
        if (contactId != null) {
            contact = contactService.getContact(contactId);
        }
    }

    // Getter und Setter

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Contact> getContacts() {
        if (contacts == null) {
            contacts = contactService.getAllContacts();
        }
        return contacts;
    }

    // Aktionen

    public String addContact() {
        contactService.saveContact(contact);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact added successfully."));
        contact = new Contact(); // Formular zurücksetzen
        contacts = null; // Liste aktualisieren
        return "contacts?faces-redirect=true"; // Navigation zur Kontaktliste
    }

    public String updateContact() {
        try {
            System.out.println("contact = " + contact);
            contactService.updateContact(contact);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact updated successfully."));
            contacts = null; // Liste aktualisieren
            return "contacts?faces-redirect=true";
        } catch (Exception e) {
            System.out.println("ContactBean: e = " + e.getClass());
            System.out.println("ContactBean: e.getCause() = " + e.getCause().getClass());
            if (e.getCause() instanceof OptimisticLockException) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Update failed: Contact was modified by another user.", null));
                return null; // Auf der gleichen Seite bleiben
            } else {
                throw e;
            }
        }
    }

    public String deleteContact(Long id) {
        contactService.deleteContact(id);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contact deleted successfully."));
        contacts = null; // Liste aktualisieren
        return "contacts?faces-redirect=true";
    }

}
