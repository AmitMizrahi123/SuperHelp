package Controller;

import DB.ClientRepository;
import DB.VolunteeringRepository;
import Logger.SingletonLogger;
import Model.Client;
import Model.Volunteering;
import View.AdminUI;
import View.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController {
    private AdminUI _theView;
    private VolunteeringRepository _theModel;
    private Logger _logger = SingletonLogger.getInstance().configLogger();

    public AdminController(AdminUI view, VolunteeringRepository model) {
        _theView = view;
        _theModel = model;
        showAllVolunteering(_theModel._volunteerings);

        _theView.setVisible(true);
        _theView.addLogoutListener(new logoutListener());
        _theView.addVolunteeringListener(new addVolunteeringListener());
    }

    private void showAllVolunteering(ArrayList<Volunteering> volunteerings) {
        _logger.log(Level.INFO, "Show all clients in list");
        if(!volunteerings.isEmpty()) {
            for(Volunteering volunteering : volunteerings) {
                _logger.log(Level.INFO, "Adding {0} to the list", volunteering);
                _theView.addVolunteeringToList(volunteering);
            }
            _theView.setListResult();
        }
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

    private class addVolunteeringListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _logger.log(Level.INFO, "Trying to add new volunteering");

            String name, gender, phoneNumber, address, problem;
            int age;

            try {
                name = _theView.getName();
                gender = _theView.getGender();
                phoneNumber = _theView.getPhoneNumber();
                address = _theView.getAddress();
                problem = _theView.getProblem();
                age = _theView.getAge();

                if (_theModel.isValidName(name) && _theModel.isValidPhoneNumber(phoneNumber) && !problem.equals("")) {
                    Volunteering volunteering = new Volunteering(name, age, gender, phoneNumber, address, problem);
                    _theModel.add(volunteering);
                    _theView.addVolunteeringToList(volunteering);
                    _theView.displayMessage("Volunteering has been added!");
                    _logger.log(Level.INFO, "{0} has been added", volunteering);
                } else {
                    _theView.displayErrorMessage("Please make sure to fill all fileds!");
                    _logger.log(Level.INFO, "Add new voulnteering fileds, there are empty fills");
                }
            } catch (Exception exc) {
                exc.printStackTrace();
                _theView.displayErrorMessage("404");
                _logger.log(Level.SEVERE, "Error to add new volunteering");
            }
        }
    }
}
