package Model;

import javax.validation.constraints.*;

public class User {
    private static final long serialVersionUID = 1L;

    @NotNull
    @NotBlank
    @Email
    protected String _email = null;

    @NotNull
    @NotBlank
    @Size(min=2, max=20)
    protected String _userName = null;

    @NotNull
    @NotBlank
    @Size(min=2, max=20)
    protected String _password = null;

    public User(String email, String userName, String password) {
        setEmail(email);
        setUserName(userName);
        setPassword(password);
    }

    public String getEmail() {
        return _email;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public String getPassword() {
        return _password;
    }

    public void setPassword(String password) {
        _password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + getEmail() + '\'' +
                ", userName='" + getUserName() + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}
