import DB.ClientRepository;
import DB.Utilities;
import DB.VolunteeringDB;
import DB.VolunteeringRepository;
import Model.Client;
import Model.Volunteering;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class TestingVolunteering {
    private static String _selectAllDb = "select * from dbso.volunteering";
    private static Connection _db;
    private static VolunteeringRepository _model;

    private static Volunteering _volunteering = null;

    @BeforeAll
    public static void setUp() throws SQLException {
        _db = Utilities.connectToMySql();
        _model = new VolunteeringRepository(_db);
        _volunteering = new Volunteering(Integer.MAX_VALUE, "Admin Admin", 22, "Male",
                "0000000001", "Admin", "Admin");
    }

    @Test
    void testInsertData() throws Exception {
        int flag = 0;
        VolunteeringDB.insertData(_db, _volunteering);
        PreparedStatement ps = _db.prepareStatement(_selectAllDb);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("volunteerId");
            if (id == _volunteering.getVolunteerId()) {
                flag = 1;
                break;
            }
        }

        Assert.assertTrue("Somthing wrong with insert data", flag != 0);
    }

    @Test
    void testDeleteData() throws Exception {
        int flag = 0;
        VolunteeringDB.deleteData(_db, _volunteering);
        PreparedStatement ps = _db.prepareStatement(_selectAllDb);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("volunteerId");
            if (id == _volunteering.getVolunteerId()) {
                flag = 1;
                break;
            }
        }

        Assert.assertTrue("Somthing wrong with delete data", flag == 0);
    }
}
