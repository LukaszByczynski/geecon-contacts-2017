package org.geecon.demo.application.admin.contact;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import org.geecon.demo.domain.contact.ContactFacade;
import org.geecon.demo.domain.contact.dto.ContactQueryDto;
import org.geecon.demo.domain.contact.dto.NewContactDto;
import org.geecon.demo.domain.contact.dto.UpdateContactDto;

@SpringView(name = ContactView.PATH)
public class ContactView extends ContactViewDesign implements View {

    public final static String PATH = "contacts";
    private final ContactFacade contactFacade;
    private final Binder<ContactPojo> binder = new Binder<>();

    public ContactView(ContactFacade contactFacade) {
        this.contactFacade = contactFacade;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setupBinder();

        grid.setDataProvider(
            (sortOrder, offset, limit) -> contactFacade.findByFilter(filterField.getValue(), offset, limit),
            () -> (int) contactFacade.countByFilter(filterField.getValue())
        );
        grid.addItemClickListener(c -> bindItemToEditor(c.getItem()));

        filterField.addValueChangeListener(l -> grid.getDataProvider().refreshAll());

        editButton.addClickListener(c -> updateUIButtons(true));

        discardButton.addClickListener(this::onDiscardClick);

        saveButton.addClickListener(this::onSaveButtonClick);

        addButton.addClickListener(this::onAddButtonClick);

        updateUIButtons(false);
    }

    private void onAddButtonClick(Button.ClickEvent e) {
        binder.setBean(ContactPojo.builder().build());
        binder.validate();
        updateUIButtons(true);
    }

    private void onSaveButtonClick(Button.ClickEvent e) {
        if (binder.isValid()) {
            ContactPojo item = binder.getBean();
            if (item.getId() == null) {
                ContactQueryDto newItem = contactFacade.create(
                    NewContactDto
                        .builder()
                        .name(item.getName())
                        .description(item.getDescription())
                        .company(item.getCompany())
                        .address(item.getAddress())
                        .city(item.getCity())
                        .zipCode(item.getZipCode())
                        .email(item.getEmail())
                        .phone(item.getPhone())
                        .build()
                );
                bindItemToEditor(newItem);
                grid.getDataProvider().refreshAll();
                grid.select(newItem);
            } else {
                contactFacade.update(
                    UpdateContactDto
                        .builder()
                        .id(item.getId())
                        .name(item.getName())
                        .description(item.getDescription())
                        .company(item.getCompany())
                        .address(item.getAddress())
                        .city(item.getCity())
                        .zipCode(item.getZipCode())
                        .email(item.getEmail())
                        .phone(item.getPhone())
                        .build()
                ).ifPresent(updatedItem -> {
                    bindItemToEditor(updatedItem);
                    grid.getDataProvider().refreshAll();
                });
            }
            updateUIButtons(false);
        }
    }

    private void onDiscardClick(Button.ClickEvent e) {
        ContactPojo item = binder.getBean();
        binder.removeBean();
        updateUIButtons(false);

        if (item.getId() != null)
            contactFacade.findOne(item.getId()).ifPresent(this::bindItemToEditor);
    }

    private void setupBinder() {
        binder
            .forField(nameField)
            .withValidator(new StringLengthValidator("Name cannot be empty", 1, 255))
            .bind(ContactPojo::getName, ContactPojo::setName);

        binder
            .forField(descriptionField)
            .bind(ContactPojo::getDescription, ContactPojo::setDescription);

        binder
            .forField(companyField)
            .bind(ContactPojo::getCompany, ContactPojo::setCompany);


        binder
            .forField(addressField)
            .bind(ContactPojo::getAddress, ContactPojo::setAddress);

        binder
            .forField(cityField)
            .bind(ContactPojo::getCity, ContactPojo::setCity);

        binder
            .forField(zipCodeField)
            .bind(ContactPojo::getZipCode, ContactPojo::setZipCode);

        binder
            .forField(emailField)
            .bind(ContactPojo::getEmail, ContactPojo::setEmail);

        binder
            .forField(phoneField)
            .bind(ContactPojo::getPhone, ContactPojo::setPhone);

    }

    private void bindItemToEditor(ContactQueryDto item) {
        binder.setBean(
            ContactPojo
                .builder()
                .id(item.getId())
                .name(item.getName())
                .description(item.getDescription())
                .company(item.getCompany())
                .address(item.getAddress())
                .city(item.getCity())
                .zipCode(item.getZipCode())
                .email(item.getEmail())
                .phone(item.getPhone())
                .build()
        );
        editButton.setEnabled(true);
    }

    private void updateUIButtons(Boolean editedState) {
        editButton.setVisible(!editedState);
        saveButton.setVisible(editedState);
        discardButton.setVisible(editedState);
        binder.setReadOnly(!editedState);
        editButton.setEnabled(binder.getBean() != null);
    }
}
