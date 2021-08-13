package Controller;

import DB.ClientRepository;
import LoggerApp.SingletonLogger;
import Model.Client;
import View.Login;
import View.Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterController {
    Register theView;
    ClientRepository theModel;
    Logger _logger = SingletonLogger.getInstance().getLogger();

    public RegisterController(Register view, ClientRepository model) {
        this.theView = view;
        this.theModel = model;

        this.theView.setVisible(true);
        this.theView.addGoBackListener(new GoBackListener());
        this.theView.addRegisterListener(new RegisterListener());
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                _logger.log(Level.INFO, "Go Back to Login screen from Register screen");

                Login view = new Login();
                ClientRepository model = new ClientRepository(theModel.conn);
                LoginController controller = new LoginController(view, model);

                theView.dispose();
            } catch (Exception exception) {
                _logger.log(Level.WARNING, "Failed to create Login screen");
                theView.displayErrorMessage("404");
                exception.printStackTrace();
            }
        }
    }

    private class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email, password, firstName, lastName, address, phoneNumber, permission;
            int counterValid = 0;

            try {
                email = theView.getEmailField();
                password = theView.getPasswordField();
                firstName = theView.getFirstNameField();
                lastName = theView.getLastNameField();
                phoneNumber = theView.getPhoneNumberField();
                address = theView.getAddressField();
                permission = theView.getPermissionComboBox();

                if (!theModel.isValidEmail(email)) {
                    theView.emailError();
                } else {
                    theView.removeEmailError();
                    counterValid++;
                }

                if (!theModel.isValidPassword(password)) {
                    theView.passwordError();
                } else {
                    theView.removePasswordError();
                    counterValid++;
                }

                if (!theModel.isValidName(firstName)) {
                    theView.firstNameError();
                } else {
                    theView.removeFirstNameError();
                    counterValid++;
                }

                if (!theModel.isValidName(lastName)) {
                    theView.lastNameError();
                } else {
                    theView.removeLastNameError();
                    counterValid++;
                }

                if (!theModel.isValidAddress(address)) {
                    theView.addressError();
                } else {
                    theView.removeAddressError();
                    counterValid++;
                }

                if (!theModel.isValidPhoneNumber(phoneNumber)) {
                    theView.phoneNumberError();
                } else {
                    theView.removePhoneNumberError();
                    counterValid++;
                }

                if (counterValid == 6) {
                    if (theModel.isEmailExist(email)) {
                        theModel.add(new Client(email, password, firstName, lastName, address, phoneNumber, permission));

                        theView.displaySuccessMessage("You have successfully registered");

                        if (permission.equals("Admin")) {
                            // Todo admin UI
                            theView.displaySuccessMessage("Admin");
                        } else {
                            // Todo client UI
                            theView.displaySuccessMessage("Client");
                        }

                        theView.dispose();
                    } else {
                        _logger.log(Level.WARNING, "Register failed, email exists");
                        theView.displayErrorMessage("Email exists");
                    }
                } else {
                    _logger.log(Level.WARNING, "Register failed, input wrong");
                    counterValid = 0;
                }
            } catch (Exception exception) {
                _logger.log(Level.WARNING, "Failed to get data from user!!");
                theView.displayErrorMessage("404");
                exception.printStackTrace();
            }
        }
    }
}
