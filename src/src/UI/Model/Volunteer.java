package Model;

import javax.validation.constraints.*;
import java.io.Serial;
import java.io.Serializable;

public class Volunteer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @NotNull
    @NotBlank
    private String _name = null;

    @NotNull
    @NotBlank
    private String _summary = null;

    @NotNull
    @NotBlank
    private String _location = null;

    @NotNull
    @NotBlank
    private String _phoneNumber = null;

    @NotNull
    @NotBlank
    private String _time = null;

    @NotNull
    @NotBlank
    private boolean _hasBeenDone;

    private int _coins;

    public Volunteer(String name, String summary, String location, String phoneNumber, String time, int coins) {
        setName(name);
        setSummary(summary);
        setLocation(location);
        setPhoneNumber(phoneNumber);
        setTime(time);
        setHasBeenDone(false);
        setCoins(coins);
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = _name;
    }

    public String getSummary() {
        return _summary;
    }

    public void setSummary(String summary) {
        _summary = _summary;
    }

    public String getLocation() {
        return _location;
    }

    public void setLocation(String location) {
        _location = _location;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        _phoneNumber = _phoneNumber;
    }

    public String getTime() {
        return _time;
    }

    public void setTime(String time) {
        _time = _time;
    }

    public boolean isHasBeenDone() {
        return _hasBeenDone;
    }

    public void setHasBeenDone(boolean hasBeenDone) {
        _hasBeenDone = _hasBeenDone;
    }

    public int getCoins() {
        return _coins;
    }

    public void setCoins(int coins) {
        _coins = coins;
    }

    @Override
    public String toString() {
        return "Volunteer{" +
                "name='" + getName() + '\'' +
                ", summary='" + getSummary() + '\'' +
                ", location='" + getLocation() + '\'' +
                ", phoneNumber='" + getPhoneNumber() + '\'' +
                ", time='" + getTime() + '\'' +
                ", hasBeenDone=" + isHasBeenDone() +
                ", coins=" + getCoins() +
                '}';
    }
}
