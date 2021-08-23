package Controller;

import DB.VolunteeringRepository;
import Logger.SingletonLogger;
import View.VolunteerUI;

import java.util.logging.Logger;

public class VolunteerContoller {
    private VolunteerUI _theView;
    private VolunteeringRepository _theModel;
    private Logger _logger = SingletonLogger.getInstance().configLogger();

    public VolunteerContoller(VolunteerUI view, VolunteeringRepository model) {
        _theView = view;
        _theModel = model;

        _theView.setVisible(true);
    }
}
