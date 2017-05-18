package org.geecon.demo.domain.contact;

import org.geecon.demo.infrastructure.repositories.ContactRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ContactConfiguration {

    @Bean
    ContactFacade roomFacade(ContactRepository roomRepository) {
        return new ContactFacade(roomRepository);
    }
}
