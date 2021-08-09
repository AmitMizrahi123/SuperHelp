package DB;

import Model.Client;

import java.util.ArrayList;

public interface ClientRepositoryInterface {
    ArrayList<Client> getAllClients();

    Client findClientByEmail(String email);

    boolean isEmailExist(String email);

    void add(Client client) throws Exception;

    void delete(String email) throws Exception;

    String getClientPermission(String email) throws Exception;

    boolean validateUserPassword(String email, String password);

    boolean isValidEmail(String email);

    boolean isValidPassword(String password);

    boolean isValidName(String name);

    boolean isValidAddress(String address);

    boolean isValidPhoneNumber(String phoneNumber);
}
