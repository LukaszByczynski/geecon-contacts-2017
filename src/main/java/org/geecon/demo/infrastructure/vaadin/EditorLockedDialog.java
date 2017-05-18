package org.geecon.demo.infrastructure.vaadin;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

public class EditorLockedDialog extends EditorLockedDialogDesign {

    private final Window window;

    public interface DiscardListener {
        void apply();
    }

    public EditorLockedDialog(DiscardListener discardListener) {
        window = new Window();
        window.setWidth(400, Unit.PIXELS);
        window.setHeight(300, Unit.PIXELS);
        window.setContent(this);
        window.setModal(true);

        discardButton.addClickListener(c -> {
            discardListener.apply();
            hide();
        });
    }

    public void show() {
        UI.getCurrent().addWindow(window);
        window.center();

    }

    public void hide() {
        UI.getCurrent().removeWindow(window);
    }

    public boolean isShown() {
        return window.isAttached();
    }


}
