package DB;

import Model.Client;

import java.sql.*;
import java.util.ArrayList;

public class UtilitiesClient {
    // Connect to MySql
    public static Connection connectToMySql() {
        String USER = "root";
        String PASS = "pass123";
        String DB_URL = "jdbc:mysql://localhost:3306/dbso?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver for connection
            String unicode= "useSSL=false&autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";
            return DriverManager.getConnection(DB_URL + unicode, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void createTable(Connection conn) throws SQLException {
        String sql = "create table client (Email varchar(40) primary key not null unique, Password varchar(40) not null, FirstName varchar(40) not null, LastName varchar(40) not null, Address varchar(40) not null, PhoneNumber varchar(40) unique not null, Permission varchar(40) not null)";

        Statement statement = conn.createStatement();
        statement.executeUpdate(sql);
        System.out.println("Create tabled");
    }

    public static boolean checkIfTableExists(Connection conn, String tableName) throws SQLException {
        DatabaseMetaData dbm = conn.getMetaData();
        ResultSet tables = dbm.getTables(null, null, tableName, null);
        return tables.next();
    }

    public static ArrayList<Client> getAllData(Connection conn) throws SQLException {
        String sql = "SELECT * FROM dbso.client;";

        ArrayList<Client> clients = new ArrayList<Client>();

        PreparedStatement ps = conn.prepareStatement(sql);
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

    public static void insertData(Connection conn, Client client) throws SQLException {
        String sql = "INSERT INTO client (Email, Password, FirstName, LastName, Address, PhoneNumber, Permission) VALUES (?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, client.getEmail());
        ps.setString(2, client.getPassword());
        ps.setString(3, client.getFirstName());
        ps.setString(4, client.getLastName());
        ps.setString(5, client.getAddress());
        ps.setString(6, client.getPhoneNumber());
        ps.setString(7, client.getPermission());
        ps.executeUpdate();
    }

    public static void deleteData(Connection conn, Client client) throws SQLException {
        String sql = "DELETE FROM client WHERE email=?;";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, client.getEmail());
        ps.executeUpdate();
    }
}
