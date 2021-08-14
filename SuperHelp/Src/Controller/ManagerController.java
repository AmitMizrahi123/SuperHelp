package Controller;

import DB.ClientRepository;
import Logger.SingletonLogger;
import Model.Client;
import View.Login;
import View.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ManagerController {
    private static Manager _theView;
    private static ClientRepository _theModel;
    private static final Logger _logger = SingletonLogger.getInstance().getLogger();
    private static ArrayList<Client> clients;

    public ManagerController(Manager view, ClientRepository model) {
        _theView = view;
        _theModel = model;
        clients = _theModel.getAllClients();
        showAllUsers(clients);

        _theView.setVisible(true);
        _theView.addGoBackListener(new ContactController.GoBackListener());
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {

            try {
                Login log = new Login();
                ClientRepository model = new ClientRepository(_theModel._db);
                LoginController logController = new LoginController(log,model);

                _theView.dispose();
            }
            catch(Exception error) {
                _theView.displayErrorMessage("Ops ! Something went wrong");
            }
        }
    }

    private void showAllUsers(ArrayList<Client> clients) {
        if(!clients.isEmpty()) {
            for(Client client : clients) {
                _theView.addClientToListElement(client);
            }
            _theView.setListResult();
        }
    }
}
