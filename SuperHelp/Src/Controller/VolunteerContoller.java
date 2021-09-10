package Controller;

import DB.ClientRepository;
import DB.VolunteeringRepository;
import Logger.SingletonLogger;
import Model.Volunteering;
import View.Login;
import View.Volunteer;
import org.checkerframework.checker.units.qual.A;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VolunteerContoller {
    private Volunteer _theView;
    private VolunteeringRepository _theModel;
    private String _email;
    private Logger _logger = SingletonLogger.getInstance().configLogger();

    public VolunteerContoller(Volunteer view, VolunteeringRepository model, String email) {
        _theView = view;
        _theModel = model;
        _email = email;
        showAllVolunteering(_theModel._volunteerings);

        if (_theView.getListSize() == 0) {
            _theView.enableClearButton(false);
            _theView.enableSearchButton(false);
            _theView.enableTakeMeButton(false);
        }

        _theView.setVisible(true);
        _theView.addLogoutListener(new logoutListener());
        _theView.addMouseListener(new MouseClick());
        _theView.addSearchListener(new SearchListener());
        _theView.addClearListener(new ClearListener());
        _theView.addTakeVolunteeringListener(new TakeVolunteeringListener());
        _theView.addMyVolunteeringListener(new MyVolunteerings());
    }

    private void showAllVolunteering(ArrayList<Volunteering> volunteerings) {
        _logger.log(Level.INFO, "Show all clients in list");
        if(!volunteerings.isEmpty()) {
            for(Volunteering volunteering : volunteerings) {
                if (volunteering.getTakingVolunteering() == null) {
                    _logger.log(Level.INFO, "Adding {0} to the list", volunteering);
                    _theView.addVolunteeringToList(volunteering);
                }
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
                        _theView.addVolunteeringToList(volunteering);
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

    private class TakeVolunteeringListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (_theView.getSelectedItem() == null) {
                _logger.log(Level.INFO, "The volunteer dont select any volunteering!");
                _theView.displayErrorMessage("You dont select volunteeing!");
                return;
            }

            _logger.log(Level.INFO, "Trying to take volunteering from volunteer screen");
            Volunteering volunteering = _theView.getSelectedItem();
            _logger.log(Level.INFO, "Get volunteering {0} that selected from list", volunteering);
            int index = _theView.getSelectedIndex();
            _logger.log(Level.INFO, "Get index {} from volunteering", index);

            try {
                _theModel.updateTakingVolunteering(volunteering, _email);
                _logger.log(Level.INFO, "update {0} from db", volunteering);
                _theView.removeItemFromList(index);
                _logger.log(Level.INFO, "remove {0} from list", volunteering);

                if (_theView.getListSize() == 0) {
                    _theView.enableSearchButton(false);
                    _theView.enableClearButton(false);
                    _theView.enableTakeMeButton(false);
                }
            } catch (Exception exc) {
                _logger.log(Level.SEVERE, "Cannot delete {0} from db", volunteering);
                _theView.displayErrorMessage("404");
                exc.printStackTrace();
            }
        }
    }

    private class MyVolunteerings implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Volunteering> volunteerings = new ArrayList<>();

            for (Volunteering vol : _theModel._volunteerings) {
                if (vol.getTakingVolunteering() == null) {
                    continue;
                }

                if (vol.getTakingVolunteering().equals(_email)) {
                    volunteerings.add(vol);
                }
            }

            String[] vol = volunteerings.stream()
                    .map(String::valueOf)
                    .toArray(String[]::new);

            if (vol.length > 0) {
                JList list = new JList(vol);
                JScrollPane scrollPane = new JScrollPane(list);
                JOptionPane.showMessageDialog(null, scrollPane, String.format("%s volunteering", _email), JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
