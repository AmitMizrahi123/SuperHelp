package Model;

import com.google.inject.AbstractModule;

public class User extends AbstractModule {
    private String Email;
    private String Password;

    public User(String email, String password) {
        setEmail(email);
        setPassword(password);
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        User other = (User) obj;

        return getEmail().equals(other.getEmail());
    }

    public String toString() {
        return "User [Email=" + getEmail() + ", password=" + getPassword() + "]";
    }
}
