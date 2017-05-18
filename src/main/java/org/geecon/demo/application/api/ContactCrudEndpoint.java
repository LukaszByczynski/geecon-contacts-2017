package org.geecon.demo.application.api;

import lombok.val;
import org.geecon.demo.domain.contact.ContactFacade;
import org.geecon.demo.domain.contact.dto.ContactQueryDto;
import org.geecon.demo.domain.contact.dto.NewContactDto;
import org.geecon.demo.domain.contact.dto.UpdateContactDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping(ContactCrudEndpoint.PATH)
class ContactCrudEndpoint {

    final static String PATH = "/api/contact";

    private final ContactFacade contactFacade;

    public ContactCrudEndpoint(ContactFacade contactFacade) {
        this.contactFacade = contactFacade;
    }

    @PostMapping
    public ResponseEntity<ContactQueryDto> create(@RequestBody NewContactDto newContact) {
        val result = contactFacade.create(newContact);

        return ResponseEntity
            .created(URI.create(String.format("%s/%s", PATH, result.getId())))
            .body(result);
    }

    @PutMapping
    public ResponseEntity<ContactQueryDto> update(@RequestBody UpdateContactDto updateContact) {
        return contactFacade
            .update(updateContact)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<ContactQueryDto> findOne(@RequestParam UUID id) {
        return contactFacade
            .findOne(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}