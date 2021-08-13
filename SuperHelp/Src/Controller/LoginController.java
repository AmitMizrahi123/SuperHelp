package Controller;

import DB.ClientRepository;
import View.Contact;
import View.Login;
import View.Register;
import LoggerApp.SingletonLogger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController {
    Login theView;
    ClientRepository theModel;
    Logger _logger = SingletonLogger.getInstance().getLogger();

    public LoginController(Login view, ClientRepository model) {
        this.theView = view;
        this.theModel = model;

        this.theView.setVisible(true);
        this.theView.addLoginListener(new LoginListener());
        this.theView.addRegisterListener(new RegisterListener());
        this.theView.addContactListener(new ContactListener());
    }

    class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email, password, permission;

            _logger.log(Level.INFO, "Trying to log in");

            try {
                email = theView.getEmailField();
                password = theView.getPasswordField();

                if (!theModel.isEmailExist(email)) {

                    if (theModel.validateUserPassword(email, password)) {
                        permission = theModel.getClientPermission(email);

                        if (permission.equals("Admin")) {
                            // TODO Admin screen
                            _logger.log(Level.INFO, "Log in success, going to Admin screen");
                            theView.displaySuccessMessage("Admin");
                        } else if (permission.equals("volunteer")) {
                            // TODO Volunteer screen
                            _logger.log(Level.INFO, "Log in success, going to Volunteer screen");
                            theView.displaySuccessMessage("Volunteer");
                        }

                        theView.dispose();
                    } else {
                        theView.displayErrorMessage("Invalid password");
                        _logger.log(Level.SEVERE, "Log in failed, invalid password");
                    }

                } else {
                    _logger.log(Level.SEVERE, "Log in failed, invalid email");
                    theView.displayErrorMessage("Invalid email");
                }
            } catch (Exception exc) {
                _logger.log(Level.SEVERE, "Failed to get data from user!!");
                theView.displayErrorMessage("404");
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
                ClientRepository model = new ClientRepository(theModel.conn);
                RegisterController controller = new RegisterController(view, model);

                theView.dispose();
            } catch (Exception exc) {
                _logger.log(Level.SEVERE, "Failed to create Register screen");
                theView.displayErrorMessage("404");
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
                ClientRepository model = new ClientRepository(theModel.conn);
                ContactController controller = new ContactController(view, model);

                theView.dispose();
            } catch (Exception exc) {
                _logger.log(Level.SEVERE, "Failed to create Contact screen");
                theView.displayErrorMessage("404");
                exc.printStackTrace();
            }
        }
    }
}