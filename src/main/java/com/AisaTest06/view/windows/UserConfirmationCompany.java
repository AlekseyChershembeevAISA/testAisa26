package com.AisaTest06.view.windows;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.dao.interfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.AisaTest06.view.components.layouts.MainLayout;
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
    private boolean confirmDeleteComapny;
    private CompanyDao companyDao;


    private static Logger logger = Logger.getLogger(UserConfirmationCompany.class.getName());


    UserConfirmationCompany(Set<Company> companySet, List<Company> listCompany) {

        companyDao = new CompanyDaoImpl();

        setStyleUserConfirmation();

        setComponents();

        setContent(formLayout);

        yes.addClickListener(clickEvent -> {
            confirmDeleteComapny = true;
            deleteListener(companySet, listCompany);
            logger.info("Одобрено удаление ");
            close();


        });
        close.addClickListener(clickEvent -> {
            confirmDeleteComapny = false;
            logger.info("Удаление отменено");
            close();
        });

    }

    /**
     * Инициализируем компоненты UserConfirmationCompany и добавляем и properties
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

    /**
     * Удаляем выбранные компании из комбобокса по ID компании
     **/
    private void deleteListener(Set<Company> companySet, List<Company> listCompany) {

        for (int i = 0; i < companySet.size(); i++) {
            if ((!listCompany.isEmpty()) || confirmDeleteComapny) {

                companyDao.deleteCompany(listCompany.get(i).getCompanyId());
                MainLayout.companyGrid.setItems(companyDao.selectAllCompanies());

                logger.info("компания успешно удалена " + listCompany.get(i));
            } else {
                logger.warning("Невозможно удалить компанию");
            }
        }

    }

   


}
