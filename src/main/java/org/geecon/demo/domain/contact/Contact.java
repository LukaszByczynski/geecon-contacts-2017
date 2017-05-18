package org.geecon.demo.domain.contact;

import lombok.Builder;
import lombok.Value;
import org.geecon.demo.domain.contact.dto.ContactQueryDto;

import java.util.Date;
import java.util.UUID;

@Value
@Builder
public class Contact {
    private final UUID id;
    private final String name;
    private final String description;
    private final String company;
    private final String address;
    private final String city;
    private final String zipCode;
    private final String phone;
    private final String email;
    private final Date creationDate;
    private final Date modificationDate;

    ContactQueryDto toDto() {
        return new ContactQueryDto(
            id,
            name,
            description,
            company,
            address,
            city,
            zipCode,
            phone,
            email,
            creationDate,
            modificationDate);
    }
}
