package Controller;

import Logger.SingletonLogger;
import DB.ClientRepository;
import View.Contact;
import View.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactController {
    private Contact _theView;
    private ClientRepository _theModel;
    private Logger _logger = SingletonLogger.getInstance().configLogger();

    public ContactController(Contact view, ClientRepository model) {
        _theView = view;
        _theModel = model;

        _theView.setVisible(true);
        _theView.addGoBackListener(new GoBackListener());
    }

    class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                _logger.log(Level.INFO, "Go Back to Login screen from Contact screen");

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
}
