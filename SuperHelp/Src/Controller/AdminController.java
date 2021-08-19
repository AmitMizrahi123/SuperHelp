package Controller;

import DB.VolunteeringRepository;
import Logger.SingletonLogger;
import View.AdminUI;

import java.util.logging.Logger;

public class AdminController {
    private AdminUI _theView;
    private VolunteeringRepository _theModel;
    private Logger _logger = SingletonLogger.getInstance().configLogger();

    public AdminController(AdminUI view, VolunteeringRepository model) {
        _theView = view;
        _theModel = model;

        _theView.setVisible(true);
    }
}
