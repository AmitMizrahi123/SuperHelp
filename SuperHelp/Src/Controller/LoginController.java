package Controller;

import DB.ClientRepository;
import DB.VolunteeringRepository;
import View.*;
import Logger.SingletonLogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    private Login _theView;
    private ClientRepository _theModel;
    private Logger _logger = SingletonLogger.getInstance().configLogger();

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
                            _logger.log(Level.INFO, "Log in success, going to Admin screen");

                            AdminUI view = new AdminUI();
                            view.setTitle("Welcome to Super Help - " + email);
                            VolunteeringRepository model = new VolunteeringRepository(_theModel._db);
                            AdminController controller = new AdminController(view, model);
                        } else if (permission.equals("Volunteer")) {
                            _logger.log(Level.INFO, "Log in success, going to Volunteer screen");

                            VolunteerUI view = new VolunteerUI();
                            view.setTitle("Welcome to Super Help - " + email);
                            VolunteeringRepository model = new VolunteeringRepository(_theModel._db);
                            VolunteerContoller contoller = new VolunteerContoller(view, model);
                        } else if (permission.equals("Manager")) {
                            _logger.log(Level.INFO, "Log in success, going to Manager screen");

                            ManagerUI view = new ManagerUI();
                            view.setTitle("Welcome to Super help - " + email);
                            ClientRepository model = new ClientRepository(_theModel._db);
                            ManagerController controller = new ManagerController(view, model);
                        }

                        _theView.dispose();
                    } else {
                        new ErrorMessageUI("Invalid password");
                        _logger.log(Level.WARNING, "Log in failed, invalid password");
                    }

                } else {
                    _logger.log(Level.WARNING, "Log in failed, invalid email");
                    new ErrorMessageUI("Invalid email");
                }
            } catch (Exception exc) {
                _logger.log(Level.WARNING, "Failed to get data from user!!");
                new ErrorMessageUI("404");
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
                new ErrorMessageUI("404");
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
                new ErrorMessageUI("404");
                exc.printStackTrace();
            }
        }
    }
}