package org.geecon.demo.application.admin;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.geecon.demo.application.admin.contact.ContactView;

@Push
@Theme("rockstar")
@SpringUI(path = "/admin")
public class AdminUI extends UI {

    private final DashboardComponent dashboardComponent;

    public AdminUI(DashboardComponent dashboardComponent) {
        this.dashboardComponent = dashboardComponent;
    }

    @Override
    protected void init(VaadinRequest request) {
        setContent(dashboardComponent);
        getNavigator().navigateTo(ContactView.PATH);
    }
}
