package Model;

import java.util.Date;

public class Volunteering {
    private int _volunteerId;
    private String _firstName;
    private String _lastName;
    private String _phoneNumber;
    private String _address;
    private String _problem;
    private Date _time;

    public Volunteering(int volunteerId, String firstName, String lastName, String address,
                        String phoneNumber,String problem, Date time) {
        _volunteerId = volunteerId;
        _phoneNumber = phoneNumber;
        _address = address;
        _firstName = firstName;
        _lastName = lastName;
        _problem = problem;
        _time = time;
    }

    public int getVolunteerId() {
        return _volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        _volunteerId = volunteerId;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        _address = address;
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

    public String getProblem() {
        return _problem;
    }

    public void setProblem(String problem) {
        _problem = problem;
    }

    public Date getTime() {
        return _time;
    }

    public void setTime(Date time) {
        _time = time;
    }

    @Override
    public String toString() {
        return  "firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", problem='" + getProblem();
    }
}
