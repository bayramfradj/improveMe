package com.improveme.administrationservice.controller;

import com.improveme.administrationservice.entities.Contact;
import com.improveme.administrationservice.entities.Mail;
import com.improveme.administrationservice.exception.ResourceNotFoundException;
import com.improveme.administrationservice.dao.ContactRepository;
import com.improveme.administrationservice.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MailService mailService;

    @GetMapping
    public List<Contact> getAllContacts()
    {
        return contactRepository.findAll();
    }

    @PostMapping
    public Contact createContact(@RequestBody Contact contact)
    {
        contact.setIsDone(false);
        return contactRepository.save(contact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable("id") long contactId)
    {
        Contact existingContact = contactRepository.findById(contactId)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id :" + contactId));
        contactRepository.delete(existingContact);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/mail")
    public Boolean sendMail(@RequestBody Mail mail){

        Boolean etat = mailService.sendMail(mail);
        if (etat)
        {
            Contact contact = contactRepository.findById(mail.getIdContact())
                    .orElseThrow(() -> new ResourceNotFoundException("Contact not found with id :" + mail.getIdContact()));
            contact.setIsDone(etat);
            contactRepository.save(contact);
        }
        return etat;
    }

}
