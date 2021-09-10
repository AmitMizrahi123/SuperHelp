package DB;

import Model.Volunteering;

import java.util.ArrayList;

public interface VolunteeringRepositoryInterface {
    ArrayList<Volunteering> getAllVoluneering();

    void add(Volunteering volunteering) throws Exception;

    void delete(int id) throws Exception;

    Volunteering findVolunteeringById(int id);

    String getVoluneeringAddress(int id);

    boolean isVolunteeringExists(int id);

    boolean isValidName(String name);

    boolean isValidPhoneNumber(String phoneNumber);

    void updateTakintVoluneering(Volunteering volunteering) throws Exception;
}
