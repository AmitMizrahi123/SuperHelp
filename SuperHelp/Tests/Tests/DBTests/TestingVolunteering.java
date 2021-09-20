package Tests.DBTests;

import DB.VolunteeringDB;
import DB.VolunteeringRepository;
import Model.Volunteering;
import Utilites.InterfaceVolunteeringData;
import Utilites.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestingVolunteering extends TestBase implements InterfaceVolunteeringData {
    @Test
    public void testInsertData() throws Exception {
        int flag = 0;
        _volunteeringRepository.add(_stamVolunteering);
        PreparedStatement ps = _db.prepareStatement(_selectAllVolunteeringInDb);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("volunteerId");
            if (id == _stamVolunteering.getVolunteerId()) {
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
        _volunteeringRepository.delete(_stamVolunteering.getVolunteerId());
        PreparedStatement ps = _db.prepareStatement(_selectAllVolunteeringInDb);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("volunteerId");
            if (id == _stamVolunteering.getVolunteerId()) {
                flag = 1;
                break;
            }
        }

        Assert.assertTrue("Somthing wrong with delete data", flag == 0);
        _testResult.setTestResult(true);
    }
}
