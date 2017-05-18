package org.geecon.demo.application.admin;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Component;

@UIScope
@SpringViewDisplay
public class DashboardComponent extends DashboardComponentDesign implements ViewDisplay {

    public DashboardComponent() {

    }

    @Override
    public void showView(View view) {
        contentPanel.setContent((Component)view);
    }
}
