package Controller;

import DB.ClientRepository;
import Logger.SingletonLogger;
import Model.Client;
import View.Login;
import View.Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterController {
    Register _theView;
    ClientRepository _theModel;
    Logger _logger = SingletonLogger.getInstance().getLogger();

    public RegisterController(Register view, ClientRepository model) {
        _theView = view;
        _theModel = model;

        _theView.setVisible(true);
        _theView.addGoBackListener(new GoBackListener());
        _theView.addRegisterListener(new RegisterListener());
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                _logger.log(Level.INFO, "Go Back to Login screen from Register screen");

                Login view = new Login();
                ClientRepository model = new ClientRepository(_theModel._db);
                LoginController controller = new LoginController(view, model);

                _theView.dispose();
            } catch (Exception exception) {
                _logger.log(Level.WARNING, "Failed to create Login screen");
                _theView.displayErrorMessage("404");
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
                email = _theView.getEmailField();
                password = _theView.getPasswordField();
                firstName = _theView.getFirstNameField();
                lastName = _theView.getLastNameField();
                phoneNumber = _theView.getPhoneNumberField();
                address = _theView.getAddressField();
                permission = _theView.getPermissionComboBox();

                if (!_theModel.isValidEmail(email)) {
                    _theView.emailError();
                } else {
                    _theView.removeEmailError();
                    counterValid++;
                }

                if (!_theModel.isValidPassword(password)) {
                    _theView.passwordError();
                } else {
                    _theView.removePasswordError();
                    counterValid++;
                }

                if (!_theModel.isValidName(firstName)) {
                    _theView.firstNameError();
                } else {
                    _theView.removeFirstNameError();
                    counterValid++;
                }

                if (!_theModel.isValidName(lastName)) {
                    _theView.lastNameError();
                } else {
                    _theView.removeLastNameError();
                    counterValid++;
                }

                if (!_theModel.isValidAddress(address)) {
                    _theView.addressError();
                } else {
                    _theView.removeAddressError();
                    counterValid++;
                }

                if (!_theModel.isValidPhoneNumber(phoneNumber)) {
                    _theView.phoneNumberError();
                } else {
                    _theView.removePhoneNumberError();
                    counterValid++;
                }

                if (counterValid == 6) {
                    if (_theModel.isEmailExist(email)) {
                        _theModel.add(new Client(email, password, firstName, lastName, address, phoneNumber, permission));

                        _theView.displaySuccessMessage("You have successfully registered");

                        if (permission.equals("Admin")) {
                            // Todo admin UI
                            _theView.displaySuccessMessage("Admin");
                        } else {
                            // Todo client UI
                            _theView.displaySuccessMessage("Client");
                        }

                        _theView.dispose();
                    } else {
                        _logger.log(Level.WARNING, "Register failed, email exists");
                        _theView.displayErrorMessage("Email exists");
                    }
                } else {
                    _logger.log(Level.WARNING, "Register failed, input wrong");
                    counterValid = 0;
                }
            } catch (Exception exception) {
                _logger.log(Level.WARNING, "Failed to get data from user!!");
                _theView.displayErrorMessage("404");
                exception.printStackTrace();
            }
        }
    }
}
