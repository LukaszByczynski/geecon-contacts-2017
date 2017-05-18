package org.geecon.demo.application.admin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;
import org.geecon.demo.application.admin.contact.ContactView;

@UIScope
@SpringViewDisplay
public class DashboardComponent extends DashboardComponentDesign implements ViewDisplay {

    public DashboardComponent() {
        contactsButton.addClickListener(c -> getUI().getNavigator().navigateTo(ContactView.PATH));
    }

    @Override
    public void showView(View view) {
        contentPanel.setContent((Component)view);
    }
}
