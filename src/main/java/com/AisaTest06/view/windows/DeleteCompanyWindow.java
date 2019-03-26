package com.AisaTest06.view.windows;

import com.AisaTest06.dao.CompanyDaoImpl;
import com.AisaTest06.dao.dao.interfaces.CompanyDao;
import com.AisaTest06.entity.Company;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;


@SuppressWarnings("ALL")
public class DeleteCompanyWindow extends Window {

    private static Logger logger = Logger.getLogger(DeleteCompanyWindow.class.getName());
    static CompanyDao companyDao;
    List<Company> companyList;
    ArrayList<Company> listCompany;
    Set<Company> companySet;
    public DeleteCompanyWindow() {

        setStyleName("Удалить компанию");
        setWidth(270f, Unit.PIXELS);
        center();
        setClosable(true);
        setDraggable(false);
        setModal(true);


        setResizeLazy(false);

        companyDao = new CompanyDaoImpl();


        Button deleteCompanyButton = new Button("Удалить");
        deleteCompanyButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        deleteCompanyButton.setSizeFull();
        deleteCompanyButton.setStyleName(ValoTheme.BUTTON_DANGER);
        deleteCompanyButton.setIcon(VaadinIcons.MINUS);

        Button cancelButton = new Button("Отменить", clickEvent -> close());

        cancelButton.setSizeFull();

        companyList = companyDao.selectAllCompanies();

        CheckBoxGroup<Company> selectAllCompanies = new CheckBoxGroup<>("Выбрать компании");
        selectAllCompanies.setSizeFull();

        selectAllCompanies.setItems(companyList);
        selectAllCompanies.setItemCaptionGenerator(Company::getName);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.addComponents(selectAllCompanies, deleteCompanyButton, cancelButton);

        setContent(verticalLayout);


        selectAllCompanies.addValueChangeListener(valueChangeEvent -> {


            companySet = valueChangeEvent.getValue();

            logger.info("Выбраны компании " + companySet);

            listCompany = new ArrayList<>(companySet);



        });

        /**
         * Если в чекбоксе выбраны компании вызываем диалоговое окно
         * **/

        deleteCompanyButton.addClickListener(clickEvent -> {
            try {
                if (companySet.size()>0) {
                    UserConfirmationCompany userConfirmationCompany = new UserConfirmationCompany(companySet, listCompany);
                    UI.getCurrent().addWindow(userConfirmationCompany);
                    close();
                }
                else {
                    logger.warning("Не выбраны компании в чекбоксе ");
                }
            }catch (NullPointerException ex){
                logger.warning(" NPE deleteCompanyButton "+ ex);
            }







        });
    }







    }







