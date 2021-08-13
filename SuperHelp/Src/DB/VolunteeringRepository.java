package DB;

import Model.Volunteering;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class VolunteeringRepository implements VolunteeringRepositoryInterface {
    public Connection _db;
    public ArrayList<Volunteering> _volunteerings;

    public VolunteeringRepository(Connection db) throws SQLException {
        _db = db;
        _volunteerings = VolunteeringDB.getAllVolunteeringData(_db);
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
