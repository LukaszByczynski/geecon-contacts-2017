package org.geecon.demo.domain.contact.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.UUID;

@Value
public class ContactQueryDto {
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

    @Builder
    @JsonCreator

    public ContactQueryDto(UUID id, String name, String description, String company, String address, String city, String zipCode, String phone, String email, Date creationDate, Date modificationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.company = company;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
    }
}
