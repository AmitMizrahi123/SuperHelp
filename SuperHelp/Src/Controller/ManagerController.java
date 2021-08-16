package Controller;

import DB.ClientRepository;
import DB.VolunteeringRepository;
import Logger.SingletonLogger;
import Model.Client;
import View.Login;
import View.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerController {
    private static Manager _theView;
    private static ClientRepository _theModel;
    private static final Logger _logger = SingletonLogger.getInstance().getLogger();

    public ManagerController(Manager view, ClientRepository model) {
        _theView = view;
        _theModel = model;
        showAllUsers(_theModel._clients);

        _theView.setVisible(true);
        _theView.addGoBackListener(new GoBackListener());
        _theView.deleteClientListener(new DeleteClientListener());
        _theView.addClientListener(new AddClientListener());
    }

    private void showAllUsers(ArrayList<Client> clients) {
        _logger.log(Level.INFO, "Show all users in list");
        if(!clients.isEmpty()) {
            for(Client client : clients) {
                _logger.log(Level.INFO, "Adding {0} to the list", client);
                _theView.addClientToListElement(client);
            }
            _theView.setListResult();
        }
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                _logger.log(Level.INFO, "Go back to Login screen from Manager Screnn");

                Login log = new Login();
                ClientRepository model = new ClientRepository(_theModel._db);
                LoginController logController = new LoginController(log,model);

                _theView.dispose();
            }
            catch(Exception error) {
                _logger.log(Level.SEVERE, "Cannot go back to Login Screen from Manager Screen");
                _theView.displayErrorMessage("404");
            }
        }
    }

    private class DeleteClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            _logger.log(Level.INFO, "Trying to delete client from Manager screen");
            Client client = _theView.getSelectedItem();
            _logger.log(Level.INFO, "Get client {0} that selected from list", client);
            int index = _theView.getSelectedIndex();
            _logger.log(Level.INFO, "Get index {} from client", index);
            try {
                _theModel.delete(client.getEmail());
                _logger.log(Level.INFO, "delete {0} from db", client);
                _theView.removeItemFromList(index);
                _logger.log(Level.INFO, "delete {0} from list", client);
            } catch (Exception e) {
                _logger.log(Level.SEVERE, "Cannot delete {0} from db", client);
                _theView.displayErrorMessage("404");
                e.printStackTrace();
            }
        }
    }

    private class AddClientListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            _logger.log(Level.INFO, "Trying to add new client from Manager screen");
            String email = validEmail();
            if (email == null) { return; }
            String password = validPassword();
            if (password == null) { return; }
            String firstName = validFirsName();
            if (firstName == null) { return; }
            String lastName = validLastName();
            if (lastName == null) { return; }
            String address = validAdress();
            if (address == null) { return; }
            String permission = validPermission();
            if (permission == null) { return; }
            String phoneNumber = validPhoneNumber();
            if (phoneNumber == null) { return; }

            Client client = new Client(email, password, firstName, lastName, address, phoneNumber, permission);

            try {
                _logger.log(Level.INFO, "Insert {0} to db", client);
                _theModel.add(client);
                _logger.log(Level.INFO, "Insert {0} to list", client);
                _theView.addClientToListElement(client);
            } catch (Exception e) {
                _logger.log(Level.SEVERE, "Cannot insert {0} to db", client);
                _theView.displayErrorMessage("404");
                e.printStackTrace();
            }
        }
    }

    private String validEmail() {
        _logger.log(Level.SEVERE, "Check if email valid for create new client from manager");
        String email = _theView.addClientEmail();

        if (email != null) {
            while (!_theModel.isValidEmail(email)) {
                _logger.log(Level.SEVERE, "Manager insert invalid email");
                _theView.displayErrorMessage("Inavalid email");
                email = _theView.addClientEmail();
            }
        }

        return email;
    }

    private String validPassword() {
        _logger.log(Level.SEVERE, "Check if password valid for create new client from manager");
        String password = _theView.addClientPassword();

        if (password != null) {
            while (!_theModel.isValidPassword(password)) {
                _logger.log(Level.SEVERE, "Manager insert invalid password");
                _theView.displayErrorMessage("Inavalid password");
                password = _theView.addClientPassword();
            }
        }

        return password;
    }

    private String validFirsName() {
        _logger.log(Level.SEVERE, "Check if first name valid for create new client from manager");
        String firstName = _theView.addClientFirstName();

        if (firstName != null) {
            while (!_theModel.isValidName(firstName)) {
                _logger.log(Level.SEVERE, "Manager insert invalid first name");
                _theView.displayErrorMessage("Inavalid first name");
                firstName = _theView.addClientFirstName();
            }
        }

        return firstName;
    }

    private String validLastName() {
        _logger.log(Level.SEVERE, "Check if last name valid for create new client from manager");
        String lastName = _theView.addClientLastName();

        if (lastName != null) {
            while (!_theModel.isValidName(lastName)) {
                _logger.log(Level.SEVERE, "Manager insert invalid last name");
                _theView.displayErrorMessage("Inavalid last name");
                lastName = _theView.addClientLastName();
            }
        }

        return lastName;
    }

    private String validAdress() {
        _logger.log(Level.SEVERE, "Check if address valid for create new client from manager");
        String address = _theView.addClientAddress();

        if (address != null) {
            while (!_theModel.isValidAddress(address)) {
                _logger.log(Level.SEVERE, "Manager insert invalid address");
                _theView.displayErrorMessage("Inavalid address");
                address = _theView.addClientAddress();
            }
        }

        return address;
    }

    private String validPermission() {
        _logger.log(Level.SEVERE, "Check if permission valid for create new client from manager");
        String permission = _theView.addClientPermission();

        if (permission != null) {
            while (_theModel.isValidPermission(permission)) {
                _logger.log(Level.SEVERE, "Manager insert invalid permission");
                _theView.displayErrorMessage("Inavalid permission");
                permission = _theView.addClientPermission();
            }
        }

        return permission;
    }

    private String validPhoneNumber() {
        _logger.log(Level.SEVERE, "Check if phone number valid for create new client from manager");
        String phoneNumber = _theView.addClientPhoneNumber();

        if (phoneNumber != null) {
            while (!_theModel.isValidPhoneNumber(phoneNumber)) {
                _logger.log(Level.SEVERE, "Manager insert invalid phone number");
                _theView.displayErrorMessage("Inavalid phone number");
                phoneNumber = _theView.addClientPhoneNumber();
            }
        }

        return phoneNumber;
    }
}
