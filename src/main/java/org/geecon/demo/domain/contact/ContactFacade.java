package org.geecon.demo.domain.contact;

import org.geecon.demo.domain.contact.dto.ContactQueryDto;
import org.geecon.demo.domain.contact.dto.NewContactDto;
import org.geecon.demo.domain.contact.dto.UpdateContactDto;
import org.geecon.demo.infrastructure.repositories.ContactRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class ContactFacade {

    private final ContactRepository repository;

    public ContactFacade(ContactRepository repository) {
        this.repository = repository;
    }

    public ContactQueryDto create(NewContactDto newContact) {
        Contact contact = Contact.builder()
            .id(UUID.randomUUID())
            .name(newContact.getName())
            .description(newContact.getDescription())
            .company(newContact.getCompany())
            .address(newContact.getAddress())
            .city(newContact.getCity())
            .zipCode(newContact.getZipCode())
            .phone(newContact.getPhone())
            .email(newContact.getEmail())
            .creationDate(new Date())
            .modificationDate(new Date())
            .build();

        repository.save(contact);
        return contact.toDto();
    }

    public Optional<ContactQueryDto> update(UpdateContactDto updateContact) {
        Optional<Contact> contact = findOne(updateContact.getId())
            .map(r ->
                Contact.builder()
                    .id(r.getId())
                    .name(updateContact.getName())
                    .description(updateContact.getDescription())
                    .company(updateContact.getCompany())
                    .address(updateContact.getAddress())
                    .city(updateContact.getCity())
                    .zipCode(updateContact.getZipCode())
                    .phone(updateContact.getPhone())
                    .email(updateContact.getEmail())
                    .creationDate(r.getCreationDate())
                    .modificationDate(new Date())
                    .build()
            );

        return contact.map(r -> {
            repository.save(r);
            return r.toDto();
        });
    }

    public Optional<ContactQueryDto> findOne(UUID id) {
        return repository.findOne(id).map(Contact::toDto);
    }

    public long countByFilter(String filter) {
        return repository.countByFilter(filter);
    }

    public Stream<ContactQueryDto> findByFilter(String filter, int offset, int size) {
        return repository
            .findByFilter(filter, offset, size)
            .map(Contact::toDto);
    }
}
