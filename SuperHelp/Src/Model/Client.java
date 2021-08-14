package Model;

import java.io.Serial;

public class Client extends User {
    @Serial
    private static final long serialVersionUID = 1L;

    private String FirstName;
    private String LastName;
    private String Address;
    private String Permission;
    private String PhoneNumber;

    public Client(String email, String password, String firstName, String lastName,
                  String address, String phoneNumber, String permission) {
        super(email, password);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setPermission(permission);
        setPhoneNumber(phoneNumber);
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getFirstName()
    {
        return FirstName;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public void setFirstName(String name) {
        FirstName = name;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAddress() {
        return Address;
    }

    public String getPermission() {
        return Permission;
    }

    public void setPermission(String permission) {
        Permission = permission;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    @Override
    public String toString() {
        return "FirstName: " + getFirstName() + ", LastName: " + getLastName() + ", Address: " + getAddress()
                + ", PhoneNumber: " + getPhoneNumber() + ", Permission: " + getPermission();
    }

}
