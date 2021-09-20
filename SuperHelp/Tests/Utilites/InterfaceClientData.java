package Utilites;

import Model.Client;

public interface InterfaceClientData {
    String _selectAllClientsInDb = "select * from dbso.client";

    Client _admin = new Client("admin@gmail.com", "Aa123456!", "Admin",
            "Admin", "Tel Aviv", "0000000001", "Admin");

    Client _volunteer = new Client("volunteer@gmail.com", "Vv123456!", "Volunteer",
            "Volunteer", "Tel Aviv", "0000000002", "Volunteer");

    // Filed text
    String _email = "Email";
    String _password = "Password";
    String _firstName = "FirstName";
    String _lastName = "LastName";
    String _address = "Address";
    String _phoneNumber = "PhoneNumber";
    String _permission = "Permission";

    // Invalid Data
    String _invalidEmail1 = "stam";
    String _invalidEmail2 = "stam@";
    String _invalidEmail3 = "stam@stam";
    String _invalidPassword1 = "xxxxxxx";
    String _invalidPassword2 = "xxxxxxxx";
    String _invalidPassword3 = "xxxxxxxxxxxxxxx";
    String _invalidPassword4 = "xxxxxxxxxxxxxxxx";
    String _invalidPassword5 = "xxxxxxxxxxxxxx1";
    String _invalidPassword6 = "Xxxxxxxxxxxxxxx";
    String _invalidPassword7 = "!xxxxxxxxxxxxxx";
    String _invalidPassword8 = "!xxxxxxxxxxxxx1";
    String _invalidPassword9 = "!xxxxxxxxxxxxxX";
    String _invalidPassword10 = "1xxxxxxxxxxxxxX";
    String _invalidPassword11 = "1xxxxxxxxxxxxxX!";
    String _invalidName1 = "111a";
    String _invalidName2 = "";
    String _invalidName3 = "111!";
    String _invalidAddress1 = "";
    String _invalidAddress2 = "modiin!";
    String _invalidAddress3 = "!modiin";
    String _invalidAddress4 = "mod!iin";
    String _invalidNumber1 = "050111111";
    String _invalidNumber2 = "05011111111";
    String _invalidNumber3 = "0501111111!";
    String _invalidNumber4 = "0501111111a";
    String _invalidNumber5 = "0501111111A";

    // Invalid Messages
    String _numberOfColumnsInvlaidMessage = "Number of coulums need to be 7";
    String _invlaidEmailMessage = "Invalid Email";
    String _invlaidPasswordMessage = "Invalid Password";
    String _invlaidNameMessage = "Invalid Name";
    String _invlaidAddressMessage = "Invalid Address";
    String _invlaidPhoneNumberMessage = "Invalid Phone Number";

    // Valid Messages
    String _validEmailMessage = "Valid Email";
    String _validPasswordMessage = "Valid Password";
    String _validNameMessage = "Valid Name";
    String _validAddressMessage = "Valid Address";
    String _validPhoneNumberMessage = "Valid Phone Number";

    int _numberOfUsers = 1000;
}
