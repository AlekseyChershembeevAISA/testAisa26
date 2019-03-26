package com.AisaTest06.view.windows;

import com.AisaTest06.entity.Company;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


class UserConfirmationCompany extends Window {

    private Label label;
    private Button yes;
    private Button close;
    private FormLayout formLayout;
    private static boolean confirmComapny;


    private static Logger logger = Logger.getLogger(UserConfirmationCompany.class.getName());


    UserConfirmationCompany(Set<Company> companySet, List<Company> listCompany) {

        setStyleUserConfirmation();

        setComponents();

        setContent(formLayout);

        yes.addClickListener(clickEvent -> {
            confirmComapny = true;
            DeleteCompanyWindow.deleteListener(companySet, listCompany);
            logger.info("Одобрено удаление ");
            close();


        });
        close.addClickListener(clickEvent -> {
            confirmComapny = false;
            logger.info("Удаление отменено");
            close();
        });

    }

    /**
    Инициализируем компоненты UserConfirmationCompany и добавляем и properties
    **/
    private void setComponents() {
        label = new Label("Удалить компанию/компании ?");
        yes = new Button("Да");
        yes.setWidth(94, Unit.PERCENTAGE);
        yes.setStyleName(ValoTheme.BUTTON_DANGER);
        yes.setIcon(VaadinIcons.MINUS);
        close = new Button("Отменить");
        close.setWidth(94, Unit.PERCENTAGE);
        formLayout = new FormLayout();
        formLayout.setMargin(true);
        formLayout.addComponents(label, yes, close);
    }


    private void setStyleUserConfirmation() {
        setStyleName("Удалить компанию");
        center();
        setClosable(true);
        setResizable(false);
        setModal(true);
        setDraggable(false);
        setWidth(300, Unit.PIXELS);
        setResizeLazy(false);
    }

    static boolean getBoolean() {
        return confirmComapny;
    }






}
