package org.geecon.demo.domain.contact.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Value;

@Value
public class NewContactDto {
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

    public NewContactDto(String name, String description, String company, String address, String city, String zipCode, String phone, String email) {
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
