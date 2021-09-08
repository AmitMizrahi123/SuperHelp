package Tests.DBTests;

import DB.VolunteeringDB;
import DB.VolunteeringRepository;
import Model.Volunteering;
import Utilites.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestingVolunteering extends TestBase {
    private static String _selectAllDb = "select * from dbso.volunteering";

    private static Volunteering _volunteering = new Volunteering(Integer.MAX_VALUE, "Admin Admin", 22, "Male",
            "0000000001", "Admin", "Admin");

    @Test
    public void testInsertData() throws Exception {
        int flag = 0;
        _volunteeringRepository.add(_volunteering);
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
        _testResult.setTestResult(true);
    }

    @Test
    public void testDeleteData() throws Exception {
        int flag = 0;
        _volunteeringRepository.delete(_volunteering.getVolunteerId());
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
        _testResult.setTestResult(true);
    }
}
