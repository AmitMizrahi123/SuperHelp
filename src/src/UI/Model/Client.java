package Model;

import org.jetbrains.annotations.NotNull;

public class Client extends User {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String _firstName = null;

    @NotNull
    private String _lastName = null;

    @NotNull
    private String _address = null;

    @NotNull
    private String _phoneNumber = null;

    @NotNull
    private String _permission = null;

    private int _coins;

    public Client(String email, String userName, String password, String firstName, String lastName, String address, String phoneNumber, String permission) {
        super(email, userName, password);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setPhoneNumber(phoneNumber);
        setPermission(permission);
        setCoins(0);
    }

    public String getFirstName() {
        return _firstName;
    }

    public void setFirstName(String firstName) {
        _firstName = firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        _address = address;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }

    public String getPermission() {
        return _permission;
    }

    public void setPermission(String permission) {
        _permission = permission;
    }

    public void setCoins(int coins) { _coins += coins; }

    public int getCoins() { return _coins; }

    @Override
    public String toString() {
        return "Volunteer{" +
                "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", permission=" + getPermission() + '\'' +
                ", coins=" + getCoins() + '\'' +
                '}';
    }
}
