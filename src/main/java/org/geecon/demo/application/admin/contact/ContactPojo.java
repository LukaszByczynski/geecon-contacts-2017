package org.geecon.demo.application.admin.contact;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ContactPojo {
    private UUID id;
    private String name;
    private String description;
    private String company;
    private String address;
    private String city;
    private String zipCode;
    private String phone;
    private String email;

}
