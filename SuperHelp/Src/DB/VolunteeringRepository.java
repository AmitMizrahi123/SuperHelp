package DB;

import Model.Volunteering;

import java.sql.Connection;
import java.util.ArrayList;

public class VolunteeringRepository implements VolunteeringRepositoryInterface {
    public Connection _db;
    public ArrayList<Volunteering> _volunteerings;

    public VolunteeringRepository(Connection db) {
        _db = db;
        // Todo get data from database
    }

    @Override
    public ArrayList<Volunteering> getAllVoluneering() {
        return _volunteerings;
    }

    @Override
    public void add(Volunteering volunteering) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(int id) {

    }

    @Override
    public Volunteering findVolunteeringById(int id) {
        return null;
    }

    @Override
    public String getVoluneeringAddress(int id) {
        return null;
    }
}
