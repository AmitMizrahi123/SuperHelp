package Tests.DBTests;

import DB.ClientDB;
import DB.ClientRepository;
import Model.Client;
import Utilites.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

public class TestingClient extends TestBase {
    private static String _selectAllDb = "select * from dbso.client";
    private static Statement _stmt;
    static {
        try {
            _stmt = _db.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private static ResultSet _rs;
    static {
        try {
            _rs = _stmt.executeQuery(_selectAllDb);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private static ResultSetMetaData _rsmd;
    static {
        try {
            _rsmd = _rs.getMetaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private Client _admin = new Client("admin@gmail.com", "Aa123456!", "Admin",
            "Admin", "Tel Aviv", "0000000001", "Admin");
    private Client _volunteer = new Client("volunteer@gmail.com", "Vv123456!", "Volunteer",
            "Volunteer", "Tel Aviv", "0000000002", "Volunteer");

    @Test
    public void checkNumberOfColumns() throws Exception {
        int numberOfColumns = _rsmd.getColumnCount();
        assert numberOfColumns == 7 : "Number of coulums need to be 7";
        _testResult.setTestResult(true);
    }

    @Test
    public void checkNamesOfColumns() throws Exception {
        String col1Name = _rsmd.getColumnLabel(1); String col2Name = _rsmd.getColumnLabel(2);
        String col3Name = _rsmd.getColumnLabel(3); String col4Name = _rsmd.getColumnLabel(4);
        String col5Name = _rsmd.getColumnLabel(5); String col6Name = _rsmd.getColumnLabel(6);
        String col7Name = _rsmd.getColumnLabel(7);
        assert col1Name != "Email"; assert col2Name != "Password"; assert col3Name != "Fi_rstName";
        assert col4Name != "LastName"; assert col5Name != "Address"; assert col6Name != "PhoneNumber";
        assert col7Name != "Permission";
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidEmail() {
        String invalidEmail1 = "yovel";
        String invalidEmail2 = "yovel@";
        String invalidEmail3 = "yovel@stam";

        Assert.assertFalse("Invalid Email", _clientRepository.isValidEmail(invalidEmail1));
        Assert.assertFalse("Invalid Email", _clientRepository.isValidEmail(invalidEmail2));
        Assert.assertFalse("Invalid Email", _clientRepository.isValidEmail(invalidEmail3));
        Assert.assertTrue("Valid Email", _clientRepository.isValidEmail(_admin.getEmail()));
        Assert.assertTrue("Valid Email", _clientRepository.isValidEmail(_volunteer.getEmail()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidPassword() {
        String invalidPassword1 = "xxxxxxx";
        String invalidPassword2 = "xxxxxxxx";
        String invalidPassword3 = "xxxxxxxxxxxxxxx";
        String invalidPassword4 = "xxxxxxxxxxxxxxxx";
        String invalidPassword5 = "xxxxxxxxxxxxxx1";
        String invalidPassword6 = "Xxxxxxxxxxxxxxx";
        String invalidPassword7 = "!xxxxxxxxxxxxxx";
        String invalidPassword8 = "!xxxxxxxxxxxxx1";
        String invalidPassword9 = "!xxxxxxxxxxxxxX";
        String invalidPassword10 = "1xxxxxxxxxxxxxX";
        String invalidPassword11 = "1xxxxxxxxxxxxxX!";

        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword1));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword2));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword3));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword4));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword5));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword6));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword7));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword8));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword9));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword10));
        Assert.assertFalse("Invalid Password", _clientRepository.isValidPassword(invalidPassword11));
        Assert.assertTrue("Valid Password", _clientRepository.isValidPassword(_admin.getPassword()));
        Assert.assertTrue("Valid Password", _clientRepository.isValidPassword(_volunteer.getPassword()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidName() {
        String invalidName1 = "111a";
        String invalidName2 = "";
        String invalidName3 = "111!";

        Assert.assertFalse("Invalid Name", _clientRepository.isValidName(invalidName1));
        Assert.assertFalse("Invalid Name", _clientRepository.isValidName(invalidName2));
        Assert.assertFalse("Invalid Name", _clientRepository.isValidName(invalidName3));
        Assert.assertTrue("Valid Name", _clientRepository.isValidName(_admin.getFirstName()));
        Assert.assertTrue("Valid Name", _clientRepository.isValidName(_volunteer.getFirstName()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidAddress() {
        String invalidAddress1 = "";
        String invalidAddress2 = "modiin!";
        String invalidAddress3 = "!modiin";
        String invalidAddress4 = "mod!iin";

        Assert.assertFalse("Invalid Address", _clientRepository.isValidAddress(invalidAddress1));
        Assert.assertFalse("Invalid Address", _clientRepository.isValidAddress(invalidAddress2));
        Assert.assertFalse("Invalid Address", _clientRepository.isValidAddress(invalidAddress3));
        Assert.assertFalse("Invalid Address", _clientRepository.isValidAddress(invalidAddress4));
        Assert.assertTrue("Valid Address", _clientRepository.isValidAddress(_admin.getAddress()));
        Assert.assertTrue("Valid Address", _clientRepository.isValidAddress(_volunteer.getAddress()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidPhoneNumber() {
        String invalidNumber1 = "050111111";
        String invalidNumber2 = "05011111111";
        String invalidNumber3 = "0501111111!";
        String invalidNumber4 = "0501111111a";
        String invalidNumber5 = "0501111111A";

        Assert.assertFalse("Invalid Phone Number", _clientRepository.isValidPhoneNumber(invalidNumber1));
        Assert.assertFalse("Invalid Phone Number", _clientRepository.isValidPhoneNumber(invalidNumber2));
        Assert.assertFalse("Invalid Phone Number", _clientRepository.isValidPhoneNumber(invalidNumber3));
        Assert.assertFalse("Invalid Phone Number", _clientRepository.isValidPhoneNumber(invalidNumber4));
        Assert.assertFalse("Invalid Phone Number", _clientRepository.isValidPhoneNumber(invalidNumber5));
        Assert.assertTrue("Valid Phone Number", _clientRepository.isValidPhoneNumber(_admin.getPhoneNumber()));
        Assert.assertTrue("Valid Phone Number", _clientRepository.isValidPhoneNumber(_volunteer.getPhoneNumber()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkInsertData() throws Exception {
        int flag = 0;

        ClientDB.insertData(_db, _admin);
        ClientDB.insertData(_db, _volunteer);

        ResultSet newRs = _stmt.executeQuery(_selectAllDb);

        while (newRs.next()) {
            String email = newRs.getString("Email");

            if (email.equals("admin@gmail.com")) {
                flag = 1;
                break;
            }

            if (email.equals("volunteer@gmail.com")) {
                flag = 1;
                break;
            }
        }

        assert flag != 0;
        _testResult.setTestResult(true);
    }

    @Test
    public void checkDeleteData() throws Exception {
        int flag = 1;

        ClientDB.deleteData(_db, _admin);
        ClientDB.deleteData(_db, _volunteer);

        ResultSet newRs = _stmt.executeQuery(_selectAllDb);

        while (newRs.next()) {
            String email = newRs.getString("Email");

            if (email.equals("admin@gmail.com")) {
                flag = 1;
                break;
            }

            if (email.equals("volunteer@gmail.com")) {
                flag = 1;
                break;
            }
        }

        assert flag != 0;
        _testResult.setTestResult(true);
    }
}
