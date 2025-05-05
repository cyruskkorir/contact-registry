package com.cyrus.contact_registry.controller;

import com.cyrus.contact_registry.model.Contact;
import com.cyrus.contact_registry.service.ContactService;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/contacts")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/all")
    public String getAllContactsPage(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "contacts"; // Render the Thymeleaf template named "contacts.html"
    }

    @GetMapping("/{id}")
    @ResponseBody // Ensures the response is treated as JSON
    public Contact getContactById(@PathVariable Long id) {
        Contact contact = contactService.getContactById(id);
        if (contact == null) {
            return null;
        }
        return contact;
    }

    @PostMapping
    @ResponseBody // Ensures the response is treated as JSON
    public Contact createContact(@RequestBody Contact contact) {
        return contactService.saveContact(contact);
    }

    @PutMapping("/{id}")
    @ResponseBody // Ensures the response is treated as JSON
    public Contact updateContact(@PathVariable Long id, @RequestBody Contact contact) {
        contact.setId(id);
        return contactService.saveContact(contact);
    }

    @DeleteMapping("/{id}")
    @ResponseBody // Ensures the response is treated as a body, not a view name
    public ResponseEntity<String> deleteContact(@PathVariable Long id) {
        try {
            contactService.deleteContact(id);
            return ResponseEntity.ok("Contact deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to delete contact: " + e.getMessage());
        }
    }

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        Map<String, Object> stats = contactService.getDashboardStats();
        model.addAttribute("genderStats", stats.get("genderStats"));
        model.addAttribute("countyStats", stats.get("countyStats"));
        model.addAttribute("recentContacts", stats.get("recentContacts"));
        return "dashboard"; // Render the Thymeleaf template named "dashboard.html"
    }

    @GetMapping("/report")
    public String generateReport(@RequestParam(required = false) String county, Model model) {
        List<Contact> contacts = contactService.getContactsByCounty(county);
        model.addAttribute("contacts", contacts);
        model.addAttribute("selectedCounty", county);
        return "report"; // Render the Thymeleaf template named "report.html"
    }

    @GetMapping("/manage")
    public String manageContacts(Model model) {
        List<Contact> contacts = contactService.getAllContacts();
        model.addAttribute("contacts", contacts);
        return "manage-contacts"; // Render the Thymeleaf template named "manage.html"
    }
}
