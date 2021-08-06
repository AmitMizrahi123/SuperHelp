package DB;

import Model.Client;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientRepository implements ClientRepositoryInterface {
    public Connection conn;
    public ArrayList<Client> clients;

    public ClientRepository(Connection conn) throws Exception {
        this.conn = conn;
        clients = UtilitiesClient.getAllData(conn);
    }

    @Override
    public ArrayList<Client> getAllClients() {
        return clients;
    }

    @Override
    public Client findClientByEmail(String email) {
        for (Client client : clients) {
            if (email.equals(client.getEmail())) {
                return client;
            }
        }

        return null;
    }

    @Override
    public boolean isEmailExist(String email) {
        return findClientByEmail(email) == null;
    }

    @Override
    public void add(Client client) throws Exception {
        if (client == null)
            throw new Exception("Must have a value");

        if (!isEmailExist(client.getEmail()))
            throw new Exception("Email exists");

        clients.add(client);
        UtilitiesClient.insertData(this.conn, client);
    }

    @Override
    public void delete(String email) throws Exception {
        Client client = findClientByEmail(email);

        if (client == null) {
            throw new Exception("The client that you want to delete is not in clients");
        }

        clients.remove(client);
        UtilitiesClient.deleteData(this.conn, client);
    }

    @Override
    public String getClientPermission(String email) throws Exception {
        Client client = findClientByEmail(email);
        return client.getPermission();
    }

    @Override
    public boolean validateUserLogin(String email, String password) {
        Client client = findClientByEmail(email);

        if (client == null)
            return false;

        return client.getPassword().equals(new String(password));
    }

    @Override
    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    @Override
    public boolean isValidPassword(String password) {
        /*
        Password should not contain any space.
        Password should contain at least one digit(0-9).
        Password length should be between 8 to 15 characters.
        Password should contain at least one lowercase letter(a-z).
        Password should contain at least one uppercase letter(A-Z).
        Password should contain at least one special character ( @, #, %, &, !, $, etc….).
        */
        if (password.length() < 8 && password.length() > 15) {
            return false;
        }

        if (password.contains(" ")) {
            return false;
        }

        if (true) {
            int count = 0;
            for (int i = 0; i <= 9; i++) {
                String str1 = Integer.toString(i);
                if (password.contains(str1)) {
                    count = 1;
                    break;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        if (!(password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|"))) {
            return false;
        }

        if (true) {
            int count = 0;
            for (int i = 65; i <= 90; i++) {
                char c = (char)i;
                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                    break;
                }
            }
            if (count == 0) {
                return false;
            }
        }

        if (true) {
            int count = 0;
            for (int i = 90; i <= 122; i++) {
                char c = (char)i;
                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    count = 1;
                    break;
                }
            }
            if (count == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isValidName(String name) {
        String namRegExpVar = "[A-Z][A-Za-z ]{1,}";
        Pattern pVar = Pattern.compile(namRegExpVar);
        Matcher mVar = pVar.matcher(name);
        return mVar.matches();
    }

    @Override
    public boolean isValidAddress(String address) {
        return address != null;
    }

    @Override
    public boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("\\d{10}");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
