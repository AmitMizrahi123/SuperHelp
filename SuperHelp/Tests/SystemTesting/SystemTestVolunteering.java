package SystemTesting;

import DB.VolunteeringRepository;
import Model.Client;
import Model.Volunteering;
import Utilites.TestBase;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemTestVolunteering extends TestBase {
    private static VolunteeringRepository _model;

    static {
        try {
            _model = new VolunteeringRepository(_db);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static int numberOfVolunteering = 1000;

    @Test
    public void test1_insertVolunteeringData() throws Exception {
        Random random = new Random();
        boolean flag = false;
        int numberOfCurrentVolunteering = _model._volunteerings.size();
        int randomAge = random.nextInt(50);
        int randonVolunteering = random.nextInt(numberOfVolunteering);
        String phoneNumber = "1111111111";

        for (int i = 1; i <= numberOfVolunteering; i++) {
            int phoneNumberValue = Integer.parseInt(phoneNumber) + i;
            _model.add(new Volunteering(i, String.format("Admin%s", i), randomAge, "Male"
                    , String.valueOf(phoneNumberValue), "Akko", "stam..."));
            randomAge = random.nextInt(50);
        }

        Assert.assertTrue("Not all clients entered to DB",numberOfCurrentVolunteering + numberOfVolunteering == _model._volunteerings.size());

        for (Volunteering volunteering : _model._volunteerings) {
            if (volunteering.getVolunteerId() == randonVolunteering) {
                flag = true;
                break;
            }
        }

        Assert.assertTrue("Random volunteering are not entered to DB", flag);
        _testResult.setTestResult(true);
    }

    @Test
    public void test2_deleteVolunteeringData() throws Exception {
        Random random = new Random();
        boolean flag = true;
        int numberOfCurrentVolunteering = _model._volunteerings.size();
        int randonVolunteering = random.nextInt(numberOfVolunteering);

        for (int i = 1; i <= numberOfVolunteering; i++) {
            _model.delete(i);
        }

        Assert.assertTrue("Not all volunteering deleted", numberOfCurrentVolunteering - numberOfVolunteering == _model._volunteerings.size());

        for (Volunteering volunteering : _model._volunteerings) {
            if (volunteering.getVolunteerId() == randonVolunteering) {
                flag = false;
                break;
            }
        }

        Assert.assertTrue("Random volunteering are not entered to DB", flag);
        _testResult.setTestResult(true);
    }
}
