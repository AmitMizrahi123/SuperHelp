import DB.ClientRepository;
import DB.Utilities;
import Model.Client;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

public class TestingClient {
    private static String selectAllDb = "select * from dbso.client";
    private static Connection conn;
    private static ClientRepository model;

    private Client yuvalZoosman = new Client("yuval@gmail.com", "Yy123456!", "Yuval",
            "Zoosman", "Tel Aviv", "0541112222", "Volunteer");
    private Client romanSorken = new Client("roman@gmail.com", "Rr123456!", "Roman",
            "Sorken", "Tel Aviv", "0541113333", "Admin");

    @BeforeClass
    public static void setUp() {
        conn = Utilities.connectToMySql();

        try {
            model = new ClientRepository(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkValidEmail() {
        String invalidEmail1 = "yovel";
        String invalidEmail2 = "yovel@";
        String invalidEmail3 = "yovel@stam";

        Assert.assertFalse("Invalid Email", model.isValidEmail(invalidEmail1));
        Assert.assertFalse("Invalid Email", model.isValidEmail(invalidEmail2));
        Assert.assertFalse("Invalid Email", model.isValidEmail(invalidEmail3));
        Assert.assertTrue("Valid Email", model.isValidEmail(yuvalZoosman.getEmail()));
        Assert.assertTrue("Valid Email", model.isValidEmail(romanSorken.getEmail()));
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

        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword1));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword2));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword3));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword4));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword5));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword6));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword7));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword8));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword9));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword10));
        Assert.assertFalse("Invalid Password", model.isValidPassword(invalidPassword11));
        Assert.assertTrue("Valid Password", model.isValidPassword(yuvalZoosman.getPassword()));
        Assert.assertTrue("Valid Password", model.isValidPassword(romanSorken.getPassword()));
    }

    @Test
    public void checkValidName() {
        String invalidName1 = "111a";
        String invalidName2 = "";
        String invalidName3 = "111!";

        Assert.assertFalse("Invalid Name", model.isValidName(invalidName1));
        Assert.assertFalse("Invalid Name", model.isValidName(invalidName2));
        Assert.assertFalse("Invalid Name", model.isValidName(invalidName3));
        Assert.assertTrue("Valid Name", model.isValidName(yuvalZoosman.getFirstName()));
        Assert.assertTrue("Valid Name", model.isValidName(romanSorken.getFirstName()));
    }

    @Test
    public void checkValidAddress() {
        String invalidAddress1 = "";
        String invalidAddress2 = "modiin!";
        String invalidAddress3 = "!modiin";
        String invalidAddress4 = "mod!iin";

        Assert.assertFalse("Invalid Address", model.isValidAddress(invalidAddress1));
        Assert.assertFalse("Invalid Address", model.isValidAddress(invalidAddress2));
        Assert.assertFalse("Invalid Address", model.isValidAddress(invalidAddress3));
        Assert.assertFalse("Invalid Address", model.isValidAddress(invalidAddress4));
        Assert.assertTrue("Valid Address", model.isValidAddress(yuvalZoosman.getAddress()));
        Assert.assertTrue("Valid Address", model.isValidAddress(romanSorken.getAddress()));
    }

    @Test
    public void checkValidPhoneNumber() {
        String invalidNumber = "05011";

        Assert.assertFalse("Invalid Phone Number", model.isValidPhoneNumber(invalidNumber));
        Assert.assertTrue("Valid Phone Number", model.isValidPhoneNumber(yuvalZoosman.getPhoneNumber()));
        Assert.assertTrue("Valid Phone Number", model.isValidPhoneNumber(romanSorken.getPhoneNumber()));
    }
}
