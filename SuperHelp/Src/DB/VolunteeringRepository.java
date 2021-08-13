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
    public void add(Volunteering volunteering) throws Exception {
        if (volunteering == null)
            throw new Exception("Volunteering musht have a value");

        if (isVolunteeringExists(volunteering.getVolunteerId()))
            throw new Exception("Volunteering exists");

        _volunteerings.add(volunteering);
        VolunteeringDB.insertData(_db, volunteering);
    }

    @Override
    public void delete(int id) throws Exception {
        Volunteering volunteering = findVolunteeringById(id);

        if (volunteering == null) {
            throw new Exception("The volunteering not found!");
        }

        _volunteerings.remove(volunteering);
        VolunteeringDB.deleteData(_db, volunteering);
    }

    @Override
    public Volunteering findVolunteeringById(int id) {
        for (Volunteering volunteering : _volunteerings) {
            if (id == volunteering.getVolunteerId()) {
                return volunteering;
            }
        }

        return null;
    }

    @Override
    public String getVoluneeringAddress(int id) {
        Volunteering volunteering = findVolunteeringById(id);

        return volunteering.getAddress();
    }

    @Override
    public boolean isVolunteeringExists(int id) {
        return findVolunteeringById(id) != null;
    }
}
