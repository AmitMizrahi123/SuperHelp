package Controller;

import DB.ClientRepository;
import DB.VolunteeringRepository;
import Logger.SingletonLogger;
import Model.Volunteering;
import View.Login;
import View.VolunteerUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VolunteerContoller {
    private VolunteerUI _theView;
    private VolunteeringRepository _theModel;
    private Logger _logger = SingletonLogger.getInstance().configLogger();

    public VolunteerContoller(VolunteerUI view, VolunteeringRepository model) {
        _theView = view;
        _theModel = model;
        showAllVolunteering(_theModel._volunteerings);

        if (_theView.getListSize() == 0) {
            _theView.enableClearButton(false);
            _theView.enableSearchButton(false);
        }

        _theView.setVisible(true);
        _theView.addLogoutListener(new logoutListener());
        _theView.addMouseListener(new MouseClick());
        _theView.addSearchListener(new SearchListener());
        _theView.addClearListener(new ClearListener());
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

    private class logoutListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                _logger.log(Level.INFO, "Go back to Login screen from Volunteer Screnn");

                Login log = new Login();
                ClientRepository model = new ClientRepository(_theModel._db);
                LoginController logController = new LoginController(log,model);

                _theView.dispose();
            }
            catch(Exception error) {
                _logger.log(Level.SEVERE, "Cannot go back to Login Screen from Volunteer Screen");
                _theView.displayErrorMessage("404");
            }
        }
    }

    private class MouseClick implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent event) {
            if (_theView.getListSize() > 0) {
                if (event.getClickCount() == 2 && event.getButton() == MouseEvent.BUTTON1) {
                    Volunteering volunteering = _theView.getSelectedItem();

                    _theView.displayMessage(
                            "Volunteering Information:\n\n\n"
                                    + "Name:  " + volunteering.getName()
                                    + "\n\n" + "Age:  " + volunteering.getAge()
                                    + "\n\n" + "Gender:  " + volunteering.getGender()
                                    + "\n\n" + "Adress:  " + volunteering.getAddress()
                                    + "\n\n" + "Phone Number:  " + volunteering.getPhoneNumber()
                                    + "\n\n" + "Problem:  " + volunteering.getProblem()
                                    + "\n"
                    );
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class SearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String address;
            _theView.clearList();

            try {
                address = _theView.getAddress();

                for (Volunteering volunteering : _theModel._volunteerings) {
                    if (volunteering.getAddress().equals(address)) {
                        _theView.setListElement(volunteering);
                    }
                }

                _theView.setListResult();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
        }
    }

    private class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showAllVolunteering(_theModel._volunteerings);
        }
    }
}
