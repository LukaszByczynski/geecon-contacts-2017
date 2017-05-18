package org.geecon.demo.infrastructure.repositories;

import lombok.val;
import org.geecon.demo.domain.contact.Contact;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Component
public class ContactRepository {

    private final ConcurrentHashMap<UUID, Contact> storage = new ConcurrentHashMap<>();

    public ContactRepository() {
        addContact("Joe Black", "Boss", "joe@bank.com");
        addContact("John Doe", "IT manager", "john@bank.com");
    }

    public void save(Contact contact) {
        storage.put(contact.getId(), contact);
    }

    public Optional<Contact> findOne(UUID uuid) {
        return Optional.ofNullable(storage.get(uuid));
    }

    public void delete(UUID id) {
        storage.remove(id);

    }

    public long countByFilter(String filter) {
        return storage
            .values()
            .stream()
            .filter(r -> filter == null || (
                    r.getName().contains(filter)
                        || r.getDescription().contains(filter)
                        || r.getCompany().contains(filter)
                        || r.getAddress().contains(filter)
                        || r.getCity().contains(filter)
                        || r.getEmail().contains(filter)
                )
            )
            .count();
    }

    public Stream<Contact> findByFilter(String filter, int offset, int size) {
        return storage
            .values()
            .stream()
            .filter(r -> filter == null || (
                    r.getName().contains(filter)
                        || r.getDescription().contains(filter)
                        || r.getCompany().contains(filter)
                        || r.getAddress().contains(filter)
                        || r.getCity().contains(filter)
                        || r.getEmail().contains(filter)
                )
            )
            .skip(offset)
            .limit(size);
    }

    private void addContact(String name, String description, String email) {
        val uuid = UUID.randomUUID();
        storage.put(
            uuid,
            Contact.builder()
                .id(uuid)
                .name(name)
                .description(description)
                .email(email)
                .creationDate(new Date())
                .modificationDate(new Date())
                .build()
        );
    }
}
