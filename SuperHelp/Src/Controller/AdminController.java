package Controller;

import DB.ClientRepository;
import DB.VolunteeringRepository;
import Logger.SingletonLogger;
import Model.Volunteering;
import View.Admin;
import View.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminController {
    private Admin _theView;
    private VolunteeringRepository _theModel;
    private Logger _logger = SingletonLogger.getInstance().configLogger();

    public AdminController(Admin view, VolunteeringRepository model) {
        _theView = view;
        _theModel = model;
        showAllVolunteering(_theModel._volunteerings);

        if (_theView.getListSize() == 0) {
            _theView.enabledDeleteButton(false);
            _theView.enabledyUpdateButton(false);
        }

        _theView.setVisible(true);
        _theView.addLogoutListener(new logoutListener());
        _theView.addVolunteeringListener(new addVolunteeringListener());
        _theView.deleteVolunteeringListener(new DeleteVolunteeringListener());
        _theView.addMouseListener(new MouseClick());
        _theView.updateVolunteeringListener(new UpdateListener());
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
                    _theView.clearFields();
                    _theView.setListResult();
                    _logger.log(Level.INFO, "{0} has been added", volunteering);

                    if (_theView.getListSize() > 0) {
                        _theView.enabledDeleteButton(true);
                        _theView.enabledyUpdateButton(true);
                    }

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

    private class DeleteVolunteeringListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _logger.log(Level.INFO, "Trying to delete volunteering from Admin screen");
            Volunteering volunteering = _theView.getSelectedItem();
            _logger.log(Level.INFO, "Get volunteering {0} that selected from list", volunteering);
            int index = _theView.getSelectedIndex();
            _logger.log(Level.INFO, "Get index {} from volunteering", index);

            try {
                _theModel.delete(volunteering.getVolunteerId());
                _logger.log(Level.INFO, "delete {0} from db", volunteering);
                _theView.removeItemFromList(index);
                _logger.log(Level.INFO, "delete {0} from list", volunteering);

                if (_theView.getListSize() == 0) {
                    _theView.enabledDeleteButton(false);
                    _theView.enabledyUpdateButton(false);
                }
            } catch (Exception exc) {
                _logger.log(Level.SEVERE, "Cannot delete {0} from db", volunteering);
                _theView.displayErrorMessage("404");
                exc.printStackTrace();
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

    private class UpdateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            _logger.log(Level.INFO, "Trying to update volunteering from Admin screen");
            Volunteering volunteering = _theView.getSelectedItem();
            _theView.setDetailsVolunteeringForEdit(volunteering);
            try {
                _theModel.delete(volunteering.getVolunteerId());
                int index = _theView.getSelectedIndex();
                _theView.removeItemFromList(index);
            } catch (Exception exception) {
                _logger.log(Level.SEVERE, "Cannot update {0} from db", volunteering);
                _theView.displayErrorMessage("404");
                exception.printStackTrace();
            }
        }
    }
}
