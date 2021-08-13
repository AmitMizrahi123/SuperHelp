package Model;

public class Volunteering {
    private int volunteerId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String problem;
    private String time;

    public Volunteering(int volunteerId, String phoneNumber, String address,
                        String firstName, String lastName, String problem, String time) {
        this.volunteerId = volunteerId;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.problem = problem;
        this.time = time;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
