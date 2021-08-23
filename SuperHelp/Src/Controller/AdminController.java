package Controller;

import DB.ClientRepository;
import DB.VolunteeringRepository;
import Logger.SingletonLogger;
import View.AdminUI;
import View.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController {
    private AdminUI _theView;
    private VolunteeringRepository _theModel;
    private Logger _logger = SingletonLogger.getInstance().configLogger();

    public AdminController(AdminUI view, VolunteeringRepository model) {
        _theView = view;
        _theModel = model;

        _theView.setVisible(true);
        _theView.addLogoutListener(new logoutListener());
    }

    class logoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            try {
                _logger.log(Level.INFO, "Go back to Login screen from Admin Screnn");

                Login log = new Login();
                ClientRepository model = new ClientRepository(_theModel._db);
                LoginController logController = new LoginController(log,model);

                _theView.dispose();
            }
            catch(Exception error) {
                _logger.log(Level.SEVERE, "Cannot go back to Login Screen from Admin Screen");
                _theView.displayErrorMessage("404");
            }
        }
    }
}
