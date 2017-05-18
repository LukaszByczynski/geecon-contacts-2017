package org.geecon.demo.domain.contact.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Value
public class UpdateContactDto {
    private UUID id;
    private String name;
    private String description;
    private final String company;
    private final String address;
    private final String city;
    private final String zipCode;
    private final String phone;
    private final String email;

    @Builder
    @JsonCreator
    public UpdateContactDto(UUID id, String name, String description, String company, String address, String city, String zipCode, String phone, String email) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.company = company;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.phone = phone;
        this.email = email;
    }
}