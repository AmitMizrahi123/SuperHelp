package Tests.SystemTests;

import DB.VolunteeringRepository;
import Model.Volunteering;
import Utilites.InterfaceVolunteeringData;
import Utilites.TestBase;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.SQLException;
import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SystemTestVolunteering extends TestBase implements InterfaceVolunteeringData {
    @Test
    public void test1_insertVolunteeringData() throws Exception {
        Random random = new Random();
        boolean flag = false;
        int numberOfCurrentVolunteering = _volunteeringRepository._volunteerings.size();
        int randomAge = 16 + random.nextInt(50);
        int randonVolunteering = random.nextInt(_numberOfVolunteering);
        String phoneNumber = "1111111111";

        for (int i = 1; i <= _numberOfVolunteering; i++) {
            int phoneNumberValue = Integer.parseInt(phoneNumber) + i;
            _volunteeringRepository.add(new Volunteering(i, String.format("Admin%s", i), randomAge, "Male"
                    , String.valueOf(phoneNumberValue), "Akko", "stam..."));
            randomAge = 16 + random.nextInt(50);
        }

        Assert.assertTrue("Not all clients entered to DB",numberOfCurrentVolunteering + _numberOfVolunteering == _volunteeringRepository._volunteerings.size());

        for (Volunteering volunteering : _volunteeringRepository._volunteerings) {
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
        int numberOfCurrentVolunteering = _volunteeringRepository._volunteerings.size();
        int randonVolunteering = random.nextInt(_numberOfVolunteering);

        for (int i = 1; i <= _numberOfVolunteering; i++) {
            _volunteeringRepository.delete(i);
        }

        Assert.assertTrue("Not all volunteering deleted", numberOfCurrentVolunteering - _numberOfVolunteering == _volunteeringRepository._volunteerings.size());

        for (Volunteering volunteering : _volunteeringRepository._volunteerings) {
            if (volunteering.getVolunteerId() == randonVolunteering) {
                flag = false;
                break;
            }
        }

        Assert.assertTrue("Random volunteering are not entered to DB", flag);
        _testResult.setTestResult(true);
    }
}
