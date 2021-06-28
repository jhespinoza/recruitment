package com.espinoza.controller;

import com.espinoza.model.Contact;
import com.espinoza.model.ListResponse;
import com.espinoza.model.ObjectResponse;
import com.espinoza.repository.ContactRepository;
import com.espinoza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@EnableAutoConfiguration
@RequestMapping("/api/v1/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;
    /**
     * Get all contact
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ListResponse getAllContact(HttpServletResponse http) {
        ListResponse response = new ListResponse();
        response.setMessage("Successfully Retrieved");
        response.setStatusCode(http.getStatus());
        List<Contact> contacts = (List<Contact>) contactRepository.findAll();
        response.setData(contacts);
        return response;
    }

    /**
     * Create new contact

     */
    @RequestMapping(method = RequestMethod.POST)
    public ListResponse saveContact(@RequestBody final Contact contact, HttpServletResponse http) {
        contactRepository.save(contact);
        ListResponse response = new ListResponse();
        response.setMessage("Successfully Created");
        response.setStatusCode(http.getStatus());
        List<Contact> contacts = (List<Contact>) contactRepository.findAll();
        response.setData(contacts);
        return response;
    }

    /**
     * Get a specific contact
     *

     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ObjectResponse getContact(@PathVariable("id") Integer contactId, HttpServletResponse http) {
        ObjectResponse response = new ObjectResponse();
        if (contactRepository.exists(contactId)) {
            response.setMessage("Successfully Retrieved");
            response.setStatusCode(http.getStatus());
            response.setData(contactRepository.findOne(contactId));
        } else {
            response.setMessage("Record not found");
            response.setStatusCode(404);
            response.setData(null);
        }
        return response;
    }

    /**
     * Find and update a contact
     *
     * @param contact
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ObjectResponse updateUser(@RequestBody final Contact contact, HttpServletResponse http) {
        ObjectResponse response = new ObjectResponse();
        if (contactRepository.exists(contact.getId())) {
            contactRepository.updateContact(contact.isActive(),contact.getAddress(),contact.getCity(),contact.getContract(),contact.getCountry(),
                    contact.getEmail(),contact.getLast_name(),contact.getMobile(),contact.getPhoto(),contact.getState(),contact.getSalary(),
                    contact.getId());

            response.setMessage("Successfully Updated");
            response.setStatusCode(http.getStatus());
            response.setData(contactRepository.findOne(contact.getId()));
        } else {
            response.setMessage("Record not found");
            response.setStatusCode(404);
            response.setData(null);
        }
        return response;
    }

    /**
     * Delete a contact
     *
     * @param contactId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ListResponse deleteUser(@PathVariable("id") Integer contactId, HttpServletResponse http) {
        ListResponse response = new ListResponse();
        if(contactRepository.exists(contactId)) {
            contactRepository.delete(contactId);
            response.setStatusCode(http.getStatus());
            response.setMessage("Successfully Deleted");
        } else {
            response.setStatusCode(404);
            response.setMessage("Record not found");
        }
        List<Contact> users = (List<Contact>) contactRepository.findAll();
        response.setData(users);
        return response;
    }
}
