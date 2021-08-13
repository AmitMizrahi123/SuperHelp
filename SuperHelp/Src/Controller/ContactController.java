package Controller;

import LoggerApp.SingletonLogger;
import DB.ClientRepository;
import View.Contact;
import View.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactController {
    Contact theView;
    ClientRepository theModel;
    Logger _logger = SingletonLogger.getInstance().getLogger();

    public ContactController(Contact view, ClientRepository model) {
        theView = view;
        theModel = model;

        theView.setVisible(true);
        theView.addGoBackListener(new GoBackListener());
    }

    private class GoBackListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                _logger.log(Level.INFO, "Go Back to Login screen from Contact screen");

                Login view = new Login();
                ClientRepository model = new ClientRepository(theModel.conn);
                LoginController controller = new LoginController(view, model);

                theView.dispose();
            } catch (Exception exception) {
                _logger.log(Level.SEVERE, "Failed to create Login screen");
                theView.displayErrorMessage("404");
                exception.printStackTrace();
            }
        }
    }
}
