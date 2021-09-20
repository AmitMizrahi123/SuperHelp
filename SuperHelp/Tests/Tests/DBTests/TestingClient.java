package Tests.DBTests;

import DB.ClientDB;
import DB.ClientRepository;
import Model.Client;
import Utilites.InterfaceClientData;
import Utilites.TestBase;
import org.junit.Assert;
import org.junit.Test;

import java.sql.*;

public class TestingClient extends TestBase implements InterfaceClientData {
    private Statement _stmt;
    {
        try {
            _stmt = _db.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ResultSet _rs;
    {
        try {
            _rs = _stmt.executeQuery(_selectAllClientsInDb);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ResultSetMetaData _rsmd;
    {
        try {
            _rsmd = _rs.getMetaData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void checkNumberOfColumns() throws Exception {
        int numberOfColumns = _rsmd.getColumnCount();
        assert numberOfColumns == 7 : _numberOfColumnsInvlaidMessage;
        _testResult.setTestResult(true);
    }

    @Test
    public void checkNamesOfColumns() throws Exception {
        String col1Name = _rsmd.getColumnLabel(1); String col2Name = _rsmd.getColumnLabel(2);
        String col3Name = _rsmd.getColumnLabel(3); String col4Name = _rsmd.getColumnLabel(4);
        String col5Name = _rsmd.getColumnLabel(5); String col6Name = _rsmd.getColumnLabel(6);
        String col7Name = _rsmd.getColumnLabel(7);
        assert col1Name != _email; assert col2Name != _password; assert col3Name != _firstName;
        assert col4Name != _lastName; assert col5Name != _address; assert col6Name != _phoneNumber;
        assert col7Name != _permission;
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidEmail() {
        Assert.assertFalse(_invlaidEmailMessage, _clientRepository.isValidEmail(_invalidEmail1));
        Assert.assertFalse(_invlaidEmailMessage, _clientRepository.isValidEmail(_invalidEmail2));
        Assert.assertFalse(_invlaidEmailMessage, _clientRepository.isValidEmail(_invalidEmail3));
        Assert.assertTrue(_validEmailMessage, _clientRepository.isValidEmail(_admin.getEmail()));
        Assert.assertTrue(_validEmailMessage, _clientRepository.isValidEmail(_volunteer.getEmail()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidPassword() {
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword1));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword2));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword3));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword4));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword5));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword6));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword7));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword8));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword9));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword10));
        Assert.assertFalse(_invlaidPasswordMessage, _clientRepository.isValidPassword(_invalidPassword11));
        Assert.assertTrue(_validPasswordMessage, _clientRepository.isValidPassword(_admin.getPassword()));
        Assert.assertTrue(_validPasswordMessage, _clientRepository.isValidPassword(_volunteer.getPassword()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidName() {
        Assert.assertFalse(_invlaidNameMessage, _clientRepository.isValidName(_invalidName1));
        Assert.assertFalse(_invlaidNameMessage, _clientRepository.isValidName(_invalidName2));
        Assert.assertFalse(_invlaidNameMessage, _clientRepository.isValidName(_invalidName3));
        Assert.assertTrue(_validNameMessage, _clientRepository.isValidName(_admin.getFirstName()));
        Assert.assertTrue(_validNameMessage, _clientRepository.isValidName(_volunteer.getFirstName()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidAddress() {
        Assert.assertFalse(_invlaidAddressMessage, _clientRepository.isValidAddress(_invalidAddress1));
        Assert.assertFalse(_invlaidAddressMessage, _clientRepository.isValidAddress(_invalidAddress2));
        Assert.assertFalse(_invlaidAddressMessage, _clientRepository.isValidAddress(_invalidAddress3));
        Assert.assertFalse(_invlaidAddressMessage, _clientRepository.isValidAddress(_invalidAddress4));
        Assert.assertTrue(_validAddressMessage, _clientRepository.isValidAddress(_admin.getAddress()));
        Assert.assertTrue(_validAddressMessage, _clientRepository.isValidAddress(_volunteer.getAddress()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkValidPhoneNumber() {
        Assert.assertFalse(_invlaidPhoneNumberMessage, _clientRepository.isValidPhoneNumber(_invalidNumber1));
        Assert.assertFalse(_invlaidPhoneNumberMessage, _clientRepository.isValidPhoneNumber(_invalidNumber2));
        Assert.assertFalse(_invlaidPhoneNumberMessage, _clientRepository.isValidPhoneNumber(_invalidNumber3));
        Assert.assertFalse(_invlaidPhoneNumberMessage, _clientRepository.isValidPhoneNumber(_invalidNumber4));
        Assert.assertFalse(_invlaidPhoneNumberMessage, _clientRepository.isValidPhoneNumber(_invalidNumber5));
        Assert.assertTrue(_validPhoneNumberMessage, _clientRepository.isValidPhoneNumber(_admin.getPhoneNumber()));
        Assert.assertTrue(_validPhoneNumberMessage, _clientRepository.isValidPhoneNumber(_volunteer.getPhoneNumber()));
        _testResult.setTestResult(true);
    }

    @Test
    public void checkInsertData() throws Exception {
        int flag = 0;

        ClientDB.insertData(_db, _admin);
        ClientDB.insertData(_db, _volunteer);

        ResultSet newRs = _stmt.executeQuery(_selectAllClientsInDb);

        while (newRs.next()) {
            String email = newRs.getString(_email);

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

        ResultSet newRs = _stmt.executeQuery(_selectAllClientsInDb);

        while (newRs.next()) {
            String email = newRs.getString(_email);

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
