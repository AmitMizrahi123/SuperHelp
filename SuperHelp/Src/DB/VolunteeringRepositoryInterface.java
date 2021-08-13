package DB;

import Model.Volunteering;

import java.util.ArrayList;

public interface VolunteeringRepositoryInterface {
    ArrayList<Volunteering> getAllVoluneering();

    void add(Volunteering volunteering);

    void delete(int id);

    void update(int id);

    Volunteering findVolunteeringById(int id);

    String getVoluneeringAddress(int id);
}
