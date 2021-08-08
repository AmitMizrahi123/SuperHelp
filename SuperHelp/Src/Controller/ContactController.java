package Controller;

import DB.ClientRepository;
import View.Contact;
import View.Login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContactController {
    Contact theView;
    ClientRepository theModel;

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
                Login view = new Login();
                ClientRepository model = new ClientRepository(theModel.conn);
                LoginController controller = new LoginController(view, model);

                theView.dispose();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
