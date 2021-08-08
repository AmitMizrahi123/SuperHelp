package Controller;

import DB.ClientRepository;
import View.Contact;
import View.Login;
import View.Register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    Login theView;
    ClientRepository theModel;

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

            try {
                email = theView.getEmailField();
                password = theView.getPasswordField();

                if (theModel.validateUserLogin(email, password)) {
                    permission = theModel.getClientPermission(email);

                    if (permission.equals("Admin")) {
                        // TODO Admin screen
                        theView.displaySuccessMessage("Admin");
                    } else if (permission.equals("volunteer")) {
                        // TODO Volunteer screen
                        theView.displaySuccessMessage("Volunteer");
                    }

                    theView.dispose();
                } else {
                    theView.displayErrorMessage("Invalid email or password");
                }
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    class RegisterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Register view = new Register();
                ClientRepository model = new ClientRepository(theModel.conn);
                RegisterController controller = new RegisterController(view, model);

                theView.dispose();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    class ContactListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Contact view = new Contact();
                ClientRepository model = new ClientRepository(theModel.conn);
                ContactController controller = new ContactController(view, model);

                theView.dispose();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }
}