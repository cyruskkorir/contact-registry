package com.cyrus.contact_registry.service;

import com.cyrus.contact_registry.model.Contact;
import com.cyrus.contact_registry.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }

    public Contact getContactById(Long id) {
        return contactRepository.findById(id).orElse(null);
    }

    public Map<String, Object> getDashboardStats() {
        List<Contact> contacts = contactRepository.findAll();

        // Group by gender and count
        Map<String, Long> genderStats = contacts.stream()
            .collect(Collectors.groupingBy(Contact::getGender, Collectors.counting()));

        // Group by countyOfResidence and count
        Map<String, Long> countyStats = contacts.stream()
            .collect(Collectors.groupingBy(Contact::getCountyOfResidence, Collectors.counting()));

        // Get the 5 most recently added contacts
        List<Contact> recentContacts = contactRepository.findTop5ByOrderByIdDesc();

        return Map.of(
            "genderStats", genderStats,
            "countyStats", countyStats,
            "recentContacts", recentContacts
        );
    }

    public List<Contact> getContactsByCounty(String county) {
        if (county == null || county.isEmpty()) {
            return contactRepository.findAll(); // Return all contacts if no county is specified
        }
        return contactRepository.findByCountyOfResidence(county);
    }
}
