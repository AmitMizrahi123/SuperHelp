package Controller;

import DB.ClientRepository;
import View.Contact;
import View.Login;
import View.Manager;
import View.Register;
import Logger.SingletonLogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    private Login _theView;
    private ClientRepository _theModel;
    private Logger _logger = SingletonLogger.getInstance().getLogger();

    public LoginController(Login view, ClientRepository model) {
        _theView = view;
        _theModel = model;

        _theView.setVisible(true);
        _theView.addLoginListener(new LoginListener());
        _theView.addRegisterListener(new RegisterListener());
        _theView.addContactListener(new ContactListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email, password, permission;

            _logger.log(Level.INFO, "Trying to log in");

            try {
                email = _theView.getEmailField();
                password = _theView.getPasswordField();

                if (!_theModel.isEmailExist(email)) {

                    if (_theModel.validateUserPassword(email, password)) {
                        permission = _theModel.getClientPermission(email);

                        if (permission.equals("Admin")) {
                            // TODO Admin screen
                            _logger.log(Level.INFO, "Log in success, going to Admin screen");
                            _theView.displaySuccessMessage("Admin");
                        } else if (permission.equals("volunteer")) {
                            // TODO Volunteer screen
                            _logger.log(Level.INFO, "Log in success, going to Volunteer screen");
                            _theView.displaySuccessMessage("Volunteer");
                        } else if (permission.equals("Manager")) {
                            _logger.log(Level.INFO, "Log in success, going to Manager screen");

                            Manager view = new Manager();
                            view.setTitle("Welcome to Super help - " + email);
                            ClientRepository model = new ClientRepository(_theModel._db);
                            ManagerController controller = new ManagerController(view, model);
                        }

                        _theView.dispose();
                    } else {
                        _theView.displayErrorMessage("Invalid password");
                        _logger.log(Level.WARNING, "Log in failed, invalid password");
                    }

                } else {
                    _logger.log(Level.WARNING, "Log in failed, invalid email");
                    _theView.displayErrorMessage("Invalid email");
                }
            } catch (Exception exc) {
                _logger.log(Level.WARNING, "Failed to get data from user!!");
                _theView.displayErrorMessage("404");
                exc.printStackTrace();
            }
        }
    }

    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                _logger.log(Level.INFO, "Going to Register screen");

                Register view = new Register();
                ClientRepository model = new ClientRepository(_theModel._db);
                RegisterController controller = new RegisterController(view, model);

                _theView.dispose();
            } catch (Exception exc) {
                _logger.log(Level.WARNING, "Failed to create Register screen");
                _theView.displayErrorMessage("404");
                exc.printStackTrace();
            }
        }
    }

    class ContactListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                _logger.log(Level.INFO, "Going to Contact screen");

                Contact view = new Contact();
                ClientRepository model = new ClientRepository(_theModel._db);
                ContactController controller = new ContactController(view, model);

                _theView.dispose();
            } catch (Exception exc) {
                _logger.log(Level.WARNING, "Failed to create Contact screen");
                _theView.displayErrorMessage("404");
                exc.printStackTrace();
            }
        }
    }
}