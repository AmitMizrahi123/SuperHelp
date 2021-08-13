package DB;

import Model.Client;

import java.sql.*;
import java.util.ArrayList;

public class ClientDB {
    public static ArrayList<Client> getAllData(Connection db) throws SQLException {
        String sql = "SELECT * FROM dbso.client;";

        ArrayList<Client> clients = new ArrayList<Client>();

        PreparedStatement ps = db.prepareStatement(sql);
        ResultSet resultSet = ps.executeQuery();

        while (resultSet.next()) {
            Client client = new Client(resultSet.getString("Email"),
                    resultSet.getString("Password"),  resultSet.getString("FirstName"),
                    resultSet.getString("LastName"), resultSet.getString("Address"),
                    resultSet.getString("PhoneNumber"), resultSet.getString("Permission"));

            clients.add(client);
        }

        return clients;
    }

    public static void insertData(Connection db, Client client) throws SQLException {
        String sql = "INSERT INTO client (Email, Password, FirstName, LastName, Address, PhoneNumber, Permission) VALUES (?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = db.prepareStatement(sql);
        ps.setString(1, client.getEmail());
        ps.setString(2, client.getPassword());
        ps.setString(3, client.getFirstName());
        ps.setString(4, client.getLastName());
        ps.setString(5, client.getAddress());
        ps.setString(6, client.getPhoneNumber());
        ps.setString(7, client.getPermission());
        ps.executeUpdate();
    }

    public static void deleteData(Connection db, Client client) throws SQLException {
        String sql = "DELETE FROM client WHERE email=?;";

        PreparedStatement ps = db.prepareStatement(sql);
        ps.setString(1, client.getEmail());
        ps.executeUpdate();
    }
}
