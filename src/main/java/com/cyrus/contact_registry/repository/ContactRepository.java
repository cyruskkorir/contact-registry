package com.cyrus.contact_registry.repository;

import com.cyrus.contact_registry.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findTop5ByOrderByIdDesc();
    List<Contact> findByCountyOfResidence(String county);
}
